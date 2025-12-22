package com.fich.sarh.movement.application.services;

import com.fich.sarh.common.PlantStatus;
import com.fich.sarh.common.UseCase;
import com.fich.sarh.movement.application.ports.entrypoint.api.MovementRetrieveServicePort;
import com.fich.sarh.movement.application.ports.persistence.MovementRetrievePort;
import com.fich.sarh.movement.domain.model.Movement;
import com.fich.sarh.movement.infrastructure.adapter.output.persistence.entity.MovementId;
import com.fich.sarh.plantofpositions.domain.model.PlantOfPosition;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@UseCase
public class MovementRetrieveUseCase implements MovementRetrieveServicePort {

    private final MovementRetrievePort retrievePort;

    public MovementRetrieveUseCase(MovementRetrievePort retrievePort) {
        this.retrievePort = retrievePort;
    }

    @Override
    public List<Movement> getAllMovements() {

        return retrievePort.findAllMovements();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Movement> findById(Long id) {

        return retrievePort.findById(id);
    }

    @Override
    public Movement findByPlant(PlantOfPosition plant) {

        return retrievePort.findByPlant(plant);
    }

  /*  @Override
    public List<Movement> fetchMovementWithInactiveAgent(List<PlantStatus> status) {
        return null; // retrievePort.fetchMovementWithInactiveAgent(status);
    }*/
}
