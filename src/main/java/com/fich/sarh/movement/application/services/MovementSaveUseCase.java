package com.fich.sarh.movement.application.services;

import com.fich.sarh.common.UseCase;
import com.fich.sarh.movement.application.ports.entrypoint.api.MovementSaveServicePort;
import com.fich.sarh.movement.application.ports.persistence.MovementSavePort;
import com.fich.sarh.movement.domain.model.Movement;

@UseCase
public class
MovementSaveUseCase implements MovementSaveServicePort {

    private final MovementSavePort savePort;

    public MovementSaveUseCase(MovementSavePort savePort) {
        this.savePort = savePort;
    }

    @Override
    public Movement saveMovement(Movement movement) {

        return savePort.saveMovement(movement);
    }
}
