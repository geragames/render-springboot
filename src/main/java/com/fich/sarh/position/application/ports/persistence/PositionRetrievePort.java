package com.fich.sarh.position.application.ports.persistence;

import com.fich.sarh.common.StatusOfPositions;
import com.fich.sarh.position.domain.model.Position;
import com.fich.sarh.position.domain.model.PositionDto;

import java.util.List;
import java.util.Optional;

public interface PositionRetrievePort {

    List<PositionDto> findOriginPositions(Long id_generatePosition);

    boolean existsOriginPositionId(Long id_generatePosition);
    List<PositionDto> findVacantPositions();
    List<PositionDto> findAllPositions();

    List<Position> findAllPosition();

    List<PositionDto>findFreePositions();

    Optional<Position> findPositionById(Long id);

    List<Position> findAllByIdIn(List<Long> ids);

    List<Position> findAvailablePosition(StatusOfPositions status);
}
