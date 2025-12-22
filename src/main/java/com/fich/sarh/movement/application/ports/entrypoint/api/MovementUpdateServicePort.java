package com.fich.sarh.movement.application.ports.entrypoint.api;

import com.fich.sarh.movement.domain.model.Movement;
import com.fich.sarh.movement.infrastructure.adapter.output.persistence.entity.MovementId;

public interface MovementUpdateServicePort {

    Movement updateMovement(Long id, Movement command);
}
