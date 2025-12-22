package com.fich.sarh.point.application.ports.entrypoint.api;

import com.fich.sarh.point.domain.model.Point;

public interface PointSaveServicePort {

    Point savePoint(Point point);
}
