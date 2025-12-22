package com.fich.sarh.point.application.ports.persistence;

import com.fich.sarh.point.domain.model.Point;

import java.util.Optional;

public interface PointLoadPort {

    Optional<Point> loadPoint(Long id);
}
