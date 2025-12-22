package com.fich.sarh.point.application.ports.persistence;

import com.fich.sarh.point.domain.model.Point;

public interface PointSavePort {

    Point savePoint(Point point);
}
