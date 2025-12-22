package com.fich.sarh.position.application.services;

import com.fich.sarh.common.StatusOfPositions;
import com.fich.sarh.common.UseCase;
import com.fich.sarh.organizationalunit.application.ports.persistence.OrganizationalUnitRetrieveSpiPort;
import com.fich.sarh.organizationalunit.domain.model.OrganizationalUnit;
import com.fich.sarh.plantofpositions.application.ports.persistence.PlantOfPositionRetrieveSpiPort;
import com.fich.sarh.point.application.ports.persistence.PointRetrievePort;
import com.fich.sarh.point.domain.model.Point;
import com.fich.sarh.position.application.ports.entrypoint.api.PositionSaveServicePort;
import com.fich.sarh.position.application.ports.entrypoint.api.PositionUpdateServicePort;
import com.fich.sarh.position.application.ports.persistence.PositionRetrievePort;
import com.fich.sarh.position.application.ports.persistence.PositionSavePort;
import com.fich.sarh.position.domain.model.Position;
import com.fich.sarh.position.domain.model.PositionCommand;
import com.fich.sarh.transformation.application.ports.persistence.TransformationRetrieveSpiPort;
import com.fich.sarh.transformation.domain.model.Transformation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

@UseCase
public class PositionSaveUseCase implements PositionSaveServicePort {

    Logger logger = LoggerFactory.getLogger(PositionSaveUseCase.class);

    private final PointRetrievePort pointRetrievePort;
    private final PositionSavePort positionSavePort;

    private final PositionRetrievePort positionRetrievePort;
    private final OrganizationalUnitRetrieveSpiPort organizationalUnitRetrieveSpiPort;
    private final TransformationRetrieveSpiPort transformationRetrievePort;
    private final PlantOfPositionRetrieveSpiPort plantRetrieve;

    private final PositionUpdateServicePort updateServicePort;

    public PositionSaveUseCase(PointRetrievePort pointRetrievePort, PositionSavePort positionSavePort, PositionRetrievePort positionRetrievePort, OrganizationalUnitRetrieveSpiPort organizationalUnitRetrieveSpiPort, TransformationRetrieveSpiPort transformationRetrievePort, PlantOfPositionRetrieveSpiPort plantRetrieve, PositionUpdateServicePort updateServicePort) {
        this.pointRetrievePort = pointRetrievePort;
        this.positionSavePort = positionSavePort;
        this.positionRetrievePort = positionRetrievePort;
        this.organizationalUnitRetrieveSpiPort = organizationalUnitRetrieveSpiPort;
        this.transformationRetrievePort = transformationRetrievePort;
        this.plantRetrieve = plantRetrieve;
        this.updateServicePort = updateServicePort;
    }

    @Override
    public Position savePosition(PositionCommand command) {

        logger.info(" LISTA DE CARGOS ORIGEN " + command.getOriginPositionIds());
        Optional<Point> pointFound = pointRetrievePort.findById(command.getPointId());


        if (!pointFound.isPresent()) {
            throw new RuntimeException("No encontrado");
        }

        Optional<Transformation> transformation = transformationRetrievePort.findById(command.getResolutionTransformationId());

        if (!transformation.isPresent()) {
            throw new RuntimeException("Transformación no encontrada");
        }

        Optional<OrganizationalUnit> organizationalUnit = organizationalUnitRetrieveSpiPort.findById(command.getOrganizationalId());

        if (!organizationalUnit.isPresent()) {
            throw new RuntimeException("Unidad Organizacional no encontrada");
        }
        List<Position> originPositions = positionRetrievePort.findAllByIdIn(command.getOriginPositionIds());

        logger.info("CARGO CREADO " + pointFound.get() + " Origen Cargo " + originPositions);
        Position position = Position.builder()
                .pointID(pointFound.get())
                .organizationalUnitID(organizationalUnit.get())
                .positionStatus(command.getPositionStatus())
                .pointsAvailable(100L)
                .newPosition(null)
                .creationResolutionID(transformation.get())
//                .originPosition(originPositions)
                .build();

        // logger.info("CARGO CREADO " + position);
        if (!originPositions.isEmpty()) {
            List<Position> positionsCalculate = calculatePosition(originPositions, pointFound.get().getAmountPoint());

            logger.info("CARGO CALCULADO " + positionsCalculate);

            for (Position originator : positionsCalculate) {
                if (originator.getPositionStatus() != StatusOfPositions.SUPRIMIDO) {
                    originator.setPositionStatus(StatusOfPositions.SUPRIMIDO);
                    originator.setResolutionSuppressionID(transformation.get());
                }

                updateServicePort.updatePositionByAvailablePoint(originator.getId(), originator);
            }
            Position positionToUpdate = positionSavePort.savePosition(position);
            // logger.info("CARGO COMPLETO " + positionToUpdate);
            for (Position originator : positionsCalculate) {
                originator.setNewPosition(positionToUpdate);

                updateServicePort.updatePositionByOriginator(originator.getId(), originator);
            }

            return positionToUpdate;
        }

        Position positionToUpdate = positionSavePort.savePosition(position);


        return positionToUpdate;

    }

    public List<Position> calculatePosition(List<Position> positions, Long amountPoint) {

        if (positions == null || positions.isEmpty()) {
            return positions;
        }


        for (int i = 0; i < positions.size(); i++) {
            Position originator = positions.get(i);
            long itemPoints = originator.getPointID().getAmountPoint();
            long amount_remainder = itemPoints * originator.getPointsAvailable()/100;


            long remainder = amountPoint - amount_remainder;

            logger.info("CANTIDAD DE PUNTOS REMANENTES " + remainder);

            if (remainder >= 0 && i == positions.size() -1) {

                int percent = (int) (((double)remainder / itemPoints)* 100);
                logger.info("ENTRO PRIMER IF" + percent);
                originator.setPointsAvailable(-(long)percent);
                break;
            }
            if(remainder >= 0 ){
                logger.info("ENTRO SEGUNDO IF");
                originator.setPointsAvailable(0L);
                amountPoint = remainder;
                continue;
            }


                long deficit = Math.abs(remainder);
                int percent = (int) ((double) deficit / itemPoints * 100);
                originator.setPointsAvailable((long) percent);
                break;


        }

        return positions;
    }


}



    /*public Position calculatePosition(Position position) {

        Position newPosition;

        Long amountByPoint = position.getPointID().getAmountPoint();

        if (!position.getOriginPosition().isEmpty()) {
            List<Position> originators = position.getOriginPosition();

            List<Position> newListOriginators = new ArrayList<>();

            for (Position originator : originators) {

                Long remainder = amountByPoint - originator.getPointsAvailable();
                amountByPoint = remainder;
                Long newAvailable = Math.min(0, remainder);

                originator.setPointsAvailable(newAvailable);

                if (originators.indexOf(originator) == (originators.size() - 1)) {
                    newAvailable = remainder >= 0? Math.min(0, -remainder):-remainder;
                    originator.setPointsAvailable(newAvailable);
                }

            }
            return position;
        }

        return position;

    }*/
