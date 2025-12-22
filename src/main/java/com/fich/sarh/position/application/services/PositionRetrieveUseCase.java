package com.fich.sarh.position.application.services;

import com.fich.sarh.common.StatusOfPositions;
import com.fich.sarh.common.UseCase;
import com.fich.sarh.position.application.ports.entrypoint.api.PositionRetrieveServicePort;
import com.fich.sarh.position.application.ports.persistence.PositionRetrievePort;
import com.fich.sarh.position.domain.model.Position;
import com.fich.sarh.position.domain.model.PositionDto;

import java.util.List;
import java.util.Optional;

@UseCase
public class PositionRetrieveUseCase implements PositionRetrieveServicePort {

    private final PositionRetrievePort positionRetrievePort;

    public PositionRetrieveUseCase(PositionRetrievePort positionRetrievePort) {
        this.positionRetrievePort = positionRetrievePort;
    }

    @Override
    public List<PositionDto> getOriginPositions(Long id_generatePosition) {
        return positionRetrievePort.findOriginPositions(id_generatePosition);
    }

    @Override
    public List<PositionDto> getAllPositions() {

        return positionRetrievePort.findAllPositions();
    }

    @Override
    public List<Position> getAllPosition() {
        return positionRetrievePort.findAllPosition();
    }

    @Override
    public List<PositionDto> getVacantPositions() {
        return positionRetrievePort.findVacantPositions();
    }

    @Override
    public List<PositionDto> getFreePositions() {
        return positionRetrievePort.findFreePositions();
    }

    @Override
    public Optional<Position> findPositionById(Long id) {

        return positionRetrievePort.findPositionById(id);
    }

    @Override
    public List<Position> findAvailablePosition(StatusOfPositions status)
    {
        return positionRetrievePort.findAvailablePosition(StatusOfPositions.SUPRIMIDO);
    }
}
