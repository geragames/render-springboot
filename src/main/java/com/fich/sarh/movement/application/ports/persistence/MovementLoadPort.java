package com.fich.sarh.movement.application.ports.persistence;

import com.fich.sarh.movement.domain.model.Movement;

import java.util.Optional;

public interface MovementLoadPort {

    Optional<Movement> loadMovement(Long id);
}
