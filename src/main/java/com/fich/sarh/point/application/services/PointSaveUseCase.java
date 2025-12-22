package com.fich.sarh.point.application.services;

import com.fich.sarh.common.UseCase;
import com.fich.sarh.point.application.ports.entrypoint.api.PointSaveServicePort;
import com.fich.sarh.point.application.ports.persistence.PointSavePort;
import com.fich.sarh.point.domain.model.Point;

@UseCase
public class PointSaveUseCase implements PointSaveServicePort {

    private final PointSavePort pointSavePort;

    public PointSaveUseCase(PointSavePort pointSavePort) {
        this.pointSavePort = pointSavePort;
    }

    @Override
    public Point savePoint(Point point) {

        return pointSavePort.savePoint(point);
    }
}
