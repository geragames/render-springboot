package com.fich.sarh.position.application.ports.entrypoint.api;

import com.fich.sarh.position.domain.model.Position;
import com.fich.sarh.position.domain.model.PositionCommand;

public interface PositionUpdateServicePort {

    Position updatePosition(Long id, PositionCommand command);

    Position updatePositionByAvailablePoint(Long id, Position command);


    Position updatePositionByOriginator(Long id, Position command);
}
