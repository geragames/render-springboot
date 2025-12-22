package com.fich.sarh.movement.application.ports.entrypoint.api;

import com.fich.sarh.common.PlantStatus;
import com.fich.sarh.movement.domain.model.Movement;
import com.fich.sarh.movement.infrastructure.adapter.output.persistence.entity.MovementId;
import com.fich.sarh.plantofpositions.domain.model.PlantOfPosition;

import java.util.List;
import java.util.Optional;

public interface MovementRetrieveServicePort {

    List<Movement> getAllMovements();

    Optional<Movement> findById(Long id);

    Movement findByPlant(PlantOfPosition plant);

    //List<Movement> fetchMovementWithInactiveAgent(List<PlantStatus> status);
}
