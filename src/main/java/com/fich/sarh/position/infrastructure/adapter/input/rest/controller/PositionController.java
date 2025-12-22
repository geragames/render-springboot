package com.fich.sarh.position.infrastructure.adapter.input.rest.controller;

import com.fich.sarh.common.WebAdapter;
import com.fich.sarh.plantofpositions.infrastructure.adapter.input.rest.model.request.PlantOfPositionRequest;
import com.fich.sarh.position.application.ports.entrypoint.api.PositionRetrieveServicePort;
import com.fich.sarh.position.application.ports.entrypoint.api.PositionSaveServicePort;
import com.fich.sarh.position.application.ports.entrypoint.api.PositionUpdateServicePort;
import com.fich.sarh.position.domain.model.Position;
import com.fich.sarh.position.domain.model.PositionCommand;
import com.fich.sarh.position.domain.model.PositionDto;
import com.fich.sarh.position.infrastructure.adapter.input.rest.model.response.PositionResponse;
import com.fich.sarh.position.infrastructure.adapter.output.persistence.mapper.PositionRestMapper;
import jakarta.websocket.server.PathParam;
import org.aspectj.bridge.ICommand;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@WebAdapter
@RestController
@RequestMapping("/position")
public class PositionController {

    Logger infoLogger = LoggerFactory.getLogger(PositionController.class);
    private final PositionRetrieveServicePort retrieveService;
    private final PositionSaveServicePort saveService;
    private final PositionUpdateServicePort updateService;

    public PositionController(PositionRetrieveServicePort retrieveService, PositionSaveServicePort saveService, PositionUpdateServicePort updateService) {
        this.retrieveService = retrieveService;
        this.saveService = saveService;
        this.updateService = updateService;
    }

    @GetMapping("origin/{id_generatePosition}")
    @PreAuthorize("hasRole('USER')")
    public List<PositionDto> findOriginPosition(@PathVariable Long id_generatePosition) {

        return retrieveService.getOriginPositions(id_generatePosition);
    }

    @GetMapping("available")
    @PreAuthorize("hasRole('USER')")
    public List<PositionDto> findAvailablePositions(){
        return retrieveService.getFreePositions();
    }

    @GetMapping("allposition")
    @PreAuthorize("hasRole('USER')")
    public List<PositionResponse> findAll(){
        infoLogger.info("CANTIDAD DE CARGOS " + retrieveService.getAllPosition().size());
        return PositionRestMapper.INSTANCE.toPositionResponseList(retrieveService.getAllPosition());
    }

    @GetMapping("vacant")
    @PreAuthorize("hasRole('USER')")
    public List<PositionDto> findVacantPositions() {
        return retrieveService.getVacantPositions();
    }
    @GetMapping("all")
    @PreAuthorize("hasRole('USER')")
    public List<PositionDto> findAllPosition() {
        return retrieveService.getAllPositions();
    }

    @GetMapping("{id}")
    @PreAuthorize("hasRole('USER')")
    public Position getPositionById(@PathVariable Long id){
        Optional<Position> positionFound = retrieveService.findPositionById(id);
        infoLogger.info("CARGOS ENCONTRADO ????" + positionFound.get());
        if(!positionFound.isPresent()) {
            return null;
        }
        return positionFound.get();
    }

    @PostMapping("create")
    @PreAuthorize("hasRole('USER')")
    public Position save(@RequestBody PositionCommand command){
        //infoLogger.info("SOLICITUD de CARGO " + command.getOriginPositionIds());
        return saveService.savePosition(command);
    }

    @PutMapping("update/{id}")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> update(@PathVariable Long id,@RequestBody PositionCommand request){
        infoLogger.info("VALOR ID CARGO " + request);
        return  new ResponseEntity<>(updateService.updatePosition(id, request), HttpStatus.OK);
    }

   /* @PostMapping("create")
    public ResponseEntity<PositionResponse> save(@RequestBody PositionRequest request){

        return ResponseEntity.status(HttpStatus.CREATED).body(
                PositionRestMapper.INSTANCE.toPositionResponse(
                        saveService.savePosition(PositionRestMapper.INSTANCE.toPosition(request))
                )
        );
    }*/
}
