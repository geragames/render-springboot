package com.fich.sarh.positiontype.application.services;

import com.fich.sarh.common.UseCase;
import com.fich.sarh.positiontype.application.ports.entrypoint.api.PositionTypeUpdateServicePort;
import com.fich.sarh.positiontype.application.ports.persistence.PositionTypeRetrievePort;
import com.fich.sarh.positiontype.application.ports.persistence.PositionTypeSavePort;
import com.fich.sarh.positiontype.domain.model.PositionType;

import java.util.Optional;

@UseCase
public class PositionTypeUpdateUseCase implements PositionTypeUpdateServicePort {

    private final PositionTypeRetrievePort positionTypeRetrievePort;

    private final PositionTypeSavePort positionTypeSavePort;

    public PositionTypeUpdateUseCase(PositionTypeRetrievePort positionTypeRetrievePort, PositionTypeSavePort positionTypeSavePort) {
        this.positionTypeRetrievePort = positionTypeRetrievePort;
        this.positionTypeSavePort = positionTypeSavePort;
    }

    @Override
    public PositionType updatePositionType(Long id, PositionType command) {
        return Optional.of(positionTypeRetrievePort.findById(id))
                .map(positionType -> positionType.get())
                .map(savedPositionType -> {
                    savedPositionType.setNamePosition(command.getNamePosition());
                    savedPositionType.setBasicSalary(command.getBasicSalary());
                    savedPositionType.setAmountOfPointPerPosition(command.getAmountOfPointPerPosition());
                    return positionTypeSavePort.savePositionType(savedPositionType);
                }).get();
    }
}
