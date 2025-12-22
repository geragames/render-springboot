package com.fich.sarh.positiontype.application.services;

import com.fich.sarh.common.UseCase;
import com.fich.sarh.positiontype.application.ports.entrypoint.api.PositionTypeSaveServicePort;
import com.fich.sarh.positiontype.application.ports.persistence.PositionTypeSavePort;
import com.fich.sarh.positiontype.domain.model.PositionType;

@UseCase
public class PositionTypeSaveUseCase implements PositionTypeSaveServicePort {

    private final PositionTypeSavePort positionTypeSavePort;

    public PositionTypeSaveUseCase(PositionTypeSavePort positionTypeSavePort) {
        this.positionTypeSavePort = positionTypeSavePort;
    }

    @Override
    public PositionType savePositionType(PositionType command)
    {
        return  positionTypeSavePort.savePositionType(command);
    }
}
