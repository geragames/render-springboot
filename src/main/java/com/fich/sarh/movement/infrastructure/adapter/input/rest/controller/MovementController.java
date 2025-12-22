package com.fich.sarh.movement.infrastructure.adapter.input.rest.controller;

import com.fich.sarh.common.WebAdapter;
import com.fich.sarh.movement.application.ports.entrypoint.api.MovementRetrieveServicePort;
import com.fich.sarh.movement.application.ports.entrypoint.api.MovementSaveServicePort;
import com.fich.sarh.movement.application.ports.entrypoint.api.MovementUpdateServicePort;
import com.fich.sarh.movement.infrastructure.adapter.input.rest.mapper.MovementRestMapper;
import com.fich.sarh.movement.infrastructure.adapter.input.rest.model.response.MovementResponse;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@WebAdapter
@RestController
@RequestMapping("/movement")
public class MovementController {

    private final MovementRetrieveServicePort retrieveMovementService;

    private final MovementSaveServicePort saveMovementService;

    private final MovementUpdateServicePort updateMovementService;

    public MovementController(MovementRetrieveServicePort retrieveMovementService,
                              MovementSaveServicePort saveMovementService,
                              MovementUpdateServicePort updateMovementService) {
        this.retrieveMovementService = retrieveMovementService;
        this.saveMovementService = saveMovementService;
        this.updateMovementService = updateMovementService;
    }
    @GetMapping("all")
    @PreAuthorize("hasRole('USER')")
    public List<MovementResponse> findAll(){
        return MovementRestMapper.INSTANCE.toMovementList(retrieveMovementService.getAllMovements());
    }
}
