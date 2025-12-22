package com.fich.sarh.point.application.ports.entrypoint.api;

import com.fich.sarh.point.domain.model.Point;

import java.util.List;
import java.util.Optional;

public interface PointRetrieveServicePort {

    List<Point> getAllPoints();

    Optional<Point> findById(Long id);
}
