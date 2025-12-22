package com.fich.sarh.movement.application.services;

import com.fich.sarh.common.UseCase;
import com.fich.sarh.movement.application.ports.entrypoint.api.MovementUpdateServicePort;
import com.fich.sarh.movement.application.ports.persistence.MovementRetrievePort;
import com.fich.sarh.movement.application.ports.persistence.MovementSavePort;
import com.fich.sarh.movement.domain.model.Movement;
import com.fich.sarh.movement.infrastructure.adapter.output.persistence.entity.MovementId;

@UseCase
public class MovementUpdateUseCase implements MovementUpdateServicePort {

    private final MovementRetrievePort retrievePort;

    private final MovementSavePort savePort;

    public MovementUpdateUseCase(MovementRetrievePort retrievePort, MovementSavePort savePort) {
        this.retrievePort = retrievePort;
        this.savePort = savePort;
    }

    @Override
    public Movement updateMovement(Long id, Movement command) {

        return  retrievePort.findById(id).map(
            movement -> {
                movement.setReasonForMovement(command.getReasonForMovement());
                movement.setMovementDate(command.getMovementDate());
                movement.setPlantId(command.getPlantId());
                movement.setPositionId(command.getPositionId());

                return savePort.saveMovement(movement);
            }
        ).get();
    }
}
