package com.fich.sarh.positiontype.application.ports.entrypoint.api;

import com.fich.sarh.positiontype.domain.model.PositionType;

public interface PositionTypeUpdateServicePort {

    PositionType updatePositionType(Long id, PositionType command);
}
