package com.fich.sarh.positiontype.application.ports.persistence;

import com.fich.sarh.positiontype.domain.model.PositionType;

import java.util.Optional;

public interface PositionTypeLoadPort {

    Optional<PositionType> loadPositionType(Long id);

}
