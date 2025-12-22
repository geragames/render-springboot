package com.fich.sarh.point.infrastructure.adapter.input.rest.controller;

import com.fich.sarh.common.WebAdapter;
import com.fich.sarh.point.application.ports.entrypoint.api.PointRetrieveServicePort;
import com.fich.sarh.point.application.ports.entrypoint.api.PointSaveServicePort;
import com.fich.sarh.point.application.ports.entrypoint.api.PointUpdateServicePort;
import com.fich.sarh.point.domain.model.ParityByPosition;
import com.fich.sarh.point.domain.model.ParityPercentage;
import com.fich.sarh.point.infrastructure.adapter.input.rest.model.request.PointRequest;
import com.fich.sarh.point.infrastructure.adapter.input.rest.model.response.PointResponse;
import com.fich.sarh.point.infrastructure.adapter.output.persistence.mapper.PointRestMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@WebAdapter
@RestController
@RequestMapping("/point")
public class PointController {

    private final PointSaveServicePort pointSaveServicePort;

    private final PointRetrieveServicePort pointRetrieveServicePort;

    private final PointUpdateServicePort pointUpdateServicePort;

    public PointController(PointSaveServicePort pointSaveServicePort, PointRetrieveServicePort pointRetrieveServicePort, PointUpdateServicePort pointUpdateServicePort) {
        this.pointSaveServicePort = pointSaveServicePort;
        this.pointRetrieveServicePort = pointRetrieveServicePort;
        this.pointUpdateServicePort = pointUpdateServicePort;
    }

    @GetMapping("all")
    @PreAuthorize("hasAnyRole('ADMIN','USER')")
    public List<PointResponse> findAll(){
        return PointRestMapper.INSTANCE.toPointResponseList(pointRetrieveServicePort.getAllPoints());
    }

    @PostMapping("create")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<PointResponse> save(@RequestBody PointRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(
                 PointRestMapper.INSTANCE.PointToPointResponse(pointSaveServicePort.savePoint(
                         PointRestMapper.INSTANCE.PointRequestToPoint(request)
                 ))
        );
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("parity")
    public void updatePoint(@RequestBody ParityPercentage parity) {
        pointUpdateServicePort.applyGlobalParity(parity.getPercentage());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("parity/{id}")
    public void updatePointByType(@PathVariable Long id, @RequestBody ParityByPosition parity){
        pointUpdateServicePort.applyParityByPositionType(id, parity.getAmountPositionNew());
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("{id}")
    public PointResponse fetchPointById(@PathVariable Long id){

        return PointRestMapper.INSTANCE.PointToPointResponse(pointRetrieveServicePort.findById(id).get() );
    }


}
