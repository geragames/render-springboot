package com.fich.sarh.point.application.services;

import com.fich.sarh.common.UseCase;
import com.fich.sarh.common.exceptions.BusinessRuleViolationException;
import com.fich.sarh.point.application.ports.entrypoint.api.PointUpdateServicePort;
import com.fich.sarh.point.application.ports.persistence.PointRetrievePort;
import com.fich.sarh.point.application.ports.persistence.PointSavePort;
import com.fich.sarh.point.domain.model.Point;

import java.util.Optional;

@UseCase
public class PointUpdateUseCase implements PointUpdateServicePort {

    private final PointRetrievePort pointRetrievePort;

    private final PointSavePort pointSavePort;

    public PointUpdateUseCase(PointRetrievePort pointRetrievePort, PointSavePort pointSavePort) {
        this.pointRetrievePort = pointRetrievePort;
        this.pointSavePort = pointSavePort;
    }

    @Override
    public Point updatePoint(Long id, Point command) {
        return pointRetrievePort.findById(id).map(
                point -> {
                    point.setAmountPoint(command.getAmountPoint());
                    point.setPositionCode(command.getPositionCode());
                    point.setDedication(command.getDedication());
                    point.setNamePosition(command.getNamePosition());
                    point.setDate(command.getDate());
                    return pointSavePort.savePoint(point);
                }
        ).get() ;
    }

    @Override
    public void applyGlobalParity(double percentage) {
        double percent = percentage/100;

        pointRetrievePort.findAllPoints().forEach(point -> {
            double prev_point = point.getAmountPoint();
            double new_point = prev_point * (1 + percent);

            point.setAmountPoint((long)new_point);

            pointSavePort.savePoint(point);
        });

    }

    @Override
    public void applyParityByPositionType(Long id, Long amount_point) {

        Optional<Point> point = pointRetrievePort.findById(id);

        if(!point.isPresent()){
           throw new BusinessRuleViolationException("No existe el tipo de cargo");
        }


            point.get().setAmountPoint(amount_point);

            pointSavePort.savePoint(point.get());

    }
}
