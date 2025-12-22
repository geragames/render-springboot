package com.fich.sarh.position.application.ports.entrypoint.api;

import com.fich.sarh.position.domain.model.Position;
import com.fich.sarh.position.domain.model.PositionCommand;

public interface PositionSaveServicePort {

    Position savePosition(PositionCommand command);
}
