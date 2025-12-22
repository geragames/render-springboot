package com.fich.sarh.movement.application.ports.persistence;

import com.fich.sarh.movement.domain.model.Movement;

public interface MovementSavePort {

    Movement saveMovement(Movement movement);
}
