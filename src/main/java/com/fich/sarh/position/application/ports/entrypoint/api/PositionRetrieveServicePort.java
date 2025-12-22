package com.fich.sarh.position.application.ports.entrypoint.api;

import com.fich.sarh.common.StatusOfPositions;
import com.fich.sarh.position.domain.model.Position;
import com.fich.sarh.position.domain.model.PositionDto;
import org.springframework.security.core.parameters.P;

import java.util.List;
import java.util.Optional;

public interface PositionRetrieveServicePort {

    List<PositionDto> getOriginPositions(Long id_generatePosition);
    List<PositionDto> getAllPositions();

    List<Position> getAllPosition();

    List<PositionDto> getVacantPositions();

    List<PositionDto>getFreePositions();

    Optional<Position> findPositionById(Long id);

    List<Position> findAvailablePosition(StatusOfPositions status);

}
