package com.fich.sarh.positiontype.infrastructure.adapter.input.rest.controller;

import com.fich.sarh.common.WebAdapter;
import com.fich.sarh.positiontype.application.ports.entrypoint.api.PositionTypeRetrieveServicePort;
import com.fich.sarh.positiontype.application.ports.entrypoint.api.PositionTypeSaveServicePort;
import com.fich.sarh.positiontype.application.ports.entrypoint.api.PositionTypeUpdateServicePort;
import com.fich.sarh.positiontype.infrastructure.adapter.input.rest.model.request.PositionTypeRequest;
import com.fich.sarh.positiontype.infrastructure.adapter.input.rest.model.response.PositionTypeResponse;
import com.fich.sarh.positiontype.infrastructure.adapter.input.rest.mapper.PositionTypeRestMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@WebAdapter
@RestController
@RequestMapping("/positiontype")
public class PositionTypeController {

    private final PositionTypeSaveServicePort positionTypeSaveServicePort;
    private final PositionTypeRetrieveServicePort positionTypeRetrieveServicePort;
    private final PositionTypeUpdateServicePort positionTypeUpdateServicePort;

    private final PositionTypeRestMapper restMapper;
    public PositionTypeController(PositionTypeSaveServicePort positionTypeSaveServicePort, PositionTypeRetrieveServicePort positionTypeRetrieveServicePort, PositionTypeUpdateServicePort positionTypeUpdateServicePort, PositionTypeRestMapper restMapper) {
        this.positionTypeSaveServicePort = positionTypeSaveServicePort;
        this.positionTypeRetrieveServicePort = positionTypeRetrieveServicePort;
        this.positionTypeUpdateServicePort = positionTypeUpdateServicePort;
        this.restMapper = restMapper;
    }

    @GetMapping("all")
    public List<PositionTypeResponse> findAll(){
        return    positionTypeRetrieveServicePort.getAllPositionType().stream().map(
                restMapper::toPositionTypeResponse
        ).collect(Collectors.toList());
    }

    @PostMapping("create")
    public ResponseEntity<PositionTypeResponse> save(@RequestBody PositionTypeRequest request){
        return ResponseEntity.status(HttpStatus.CREATED).body(
                restMapper.toPositionTypeResponse(
                        positionTypeSaveServicePort.savePositionType(
                                restMapper.toPositionType(request)
                        )
                )
        );
    }

    @PutMapping("update/{id}")
    public PositionTypeResponse update(@PathVariable Long id, @RequestBody PositionTypeRequest request ){
        return restMapper.toPositionTypeResponse(
                positionTypeUpdateServicePort.updatePositionType(id,
                        restMapper.toPositionType(request))
        );
    }

}
