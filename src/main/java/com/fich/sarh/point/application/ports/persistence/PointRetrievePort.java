package com.fich.sarh.point.application.ports.persistence;

import com.fich.sarh.point.domain.model.Point;

import java.util.List;
import java.util.Optional;

public interface PointRetrievePort {
    List<Point> findAllPoints();

    Optional<Point> findById(Long id);

}
