package com.fich.sarh.point.application.services;

import com.fich.sarh.common.UseCase;
import com.fich.sarh.point.application.ports.entrypoint.api.PointRetrieveServicePort;
import com.fich.sarh.point.application.ports.persistence.PointRetrievePort;
import com.fich.sarh.point.domain.model.Point;

import java.util.List;
import java.util.Optional;
@UseCase
public class PointRetrieveUseCase implements PointRetrieveServicePort {

    private final PointRetrievePort pointRetrievePort;

    public PointRetrieveUseCase(PointRetrievePort pointRetrievePort) {
        this.pointRetrievePort = pointRetrievePort;
    }

    @Override
    public List<Point> getAllPoints() {

        return pointRetrievePort.findAllPoints();
    }

    @Override
    public Optional<Point> findById(Long id) {

        return pointRetrievePort.findById(id);
    }
}
