package com.fich.sarh.position.application.ports.persistence;

import com.fich.sarh.position.domain.model.Position;

import java.util.Optional;

public interface PositionLoadPort {

    Optional<Position> loadPosition(Long id);
}
