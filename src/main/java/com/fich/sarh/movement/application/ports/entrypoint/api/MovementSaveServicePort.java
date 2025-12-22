package com.fich.sarh.movement.application.ports.entrypoint.api;

import com.fich.sarh.movement.domain.model.Movement;

public interface MovementSaveServicePort {

    Movement saveMovement(Movement movement);
}
