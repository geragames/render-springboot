package com.fich.sarh.positiontype.application.services;

import com.fich.sarh.common.UseCase;
import com.fich.sarh.positiontype.application.ports.entrypoint.api.PositionTypeRetrieveServicePort;
import com.fich.sarh.positiontype.application.ports.persistence.PositionTypeRetrievePort;
import com.fich.sarh.positiontype.domain.model.PositionType;

import java.util.List;
import java.util.Optional;

@UseCase
public class PositionTypeRetrieveUseCase implements PositionTypeRetrieveServicePort {

    private final PositionTypeRetrievePort positionTypeRetrievePort;

    public PositionTypeRetrieveUseCase(PositionTypeRetrievePort positionTypeRetrievePort) {
        this.positionTypeRetrievePort = positionTypeRetrievePort;}

    @Override
    public List<PositionType> getAllPositionType() {
        return positionTypeRetrievePort.findAll();
    }

    @Override
    public Optional<PositionType> findById(Long id) {
        return positionTypeRetrievePort.findById(id);
    }
}
