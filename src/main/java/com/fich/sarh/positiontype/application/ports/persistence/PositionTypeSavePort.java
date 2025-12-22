package com.fich.sarh.positiontype.application.ports.persistence;

import com.fich.sarh.positiontype.domain.model.PositionType;

public interface PositionTypeSavePort {

    PositionType savePositionType(PositionType position);
}
