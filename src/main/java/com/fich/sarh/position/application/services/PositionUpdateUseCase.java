package com.fich.sarh.position.application.services;

import com.fich.sarh.common.UseCase;
import com.fich.sarh.common.exceptions.BusinessRuleViolationException;
import com.fich.sarh.organizationalunit.application.ports.persistence.OrganizationalUnitRetrieveSpiPort;
import com.fich.sarh.organizationalunit.domain.model.OrganizationalUnit;
import com.fich.sarh.point.application.ports.persistence.PointRetrievePort;
import com.fich.sarh.point.domain.model.Point;
import com.fich.sarh.position.application.ports.entrypoint.api.PositionUpdateServicePort;
import com.fich.sarh.position.application.ports.persistence.PositionRetrievePort;
import com.fich.sarh.position.application.ports.persistence.PositionSavePort;
import com.fich.sarh.position.domain.model.Position;
import com.fich.sarh.position.domain.model.PositionCommand;
import com.fich.sarh.transformation.application.ports.persistence.TransformationRetrieveSpiPort;
import com.fich.sarh.transformation.domain.model.Transformation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Optional;

@UseCase
public class PositionUpdateUseCase implements PositionUpdateServicePort {

    Logger logger = LoggerFactory.getLogger(PositionSaveUseCase.class);
    private final PositionRetrievePort positionRetrievePort;

    private final PositionSavePort positionSavePort;
    private final TransformationRetrieveSpiPort transformationRetrieveSpiPort;
    private final OrganizationalUnitRetrieveSpiPort organizationalUnitRetrieveSpiPort;

    private final PointRetrievePort pointRetrievePort;
    public PositionUpdateUseCase(PositionRetrievePort positionRetrievePort, PositionSavePort positionSavePort, TransformationRetrieveSpiPort transformationRetrieveSpiPort, OrganizationalUnitRetrieveSpiPort organizationalUnitRetrieveSpiPort, PointRetrievePort pointRetrievePort) {
        this.positionRetrievePort = positionRetrievePort;
        this.positionSavePort = positionSavePort;
        this.transformationRetrieveSpiPort = transformationRetrieveSpiPort;

        this.organizationalUnitRetrieveSpiPort = organizationalUnitRetrieveSpiPort;
        this.pointRetrievePort = pointRetrievePort;
    }

    @Override
    public Position updatePosition(Long id, PositionCommand command) {

        if(positionRetrievePort.existsOriginPositionId(id)) throw new BusinessRuleViolationException("El cargo ya fue ocupado para crear nuevos cargos");

       Optional<Position> optionalPosition = positionRetrievePort.findPositionById(id);

        if (!optionalPosition.isPresent()) {
            throw new RuntimeException("No se encontro el cargo");
        }

        // logger.info("PUNTOS PUNTOS PUNTOS "+ command.getPointId());
        Optional<Point> point = pointRetrievePort.findById(command.getPointId());


        if(!point.isPresent()){
            throw new RuntimeException("No se encuentra el tipo de cargo");
        }

        Optional<Transformation> transformation = transformationRetrieveSpiPort.findById(command.getResolutionTransformationId());

        if (!transformation.isPresent()) {
            throw new RuntimeException("No existe la transformación indicada");
        }

        Position position = optionalPosition.get();

        Optional<OrganizationalUnit> organizationalUnit = organizationalUnitRetrieveSpiPort.findById(command.getOrganizationalId());

        if (!organizationalUnit.isPresent()) {
            throw new RuntimeException("No existe la Unidad Organizativa");
        }

        position.setPositionStatus(command.getPositionStatus());
        position.setPointID(point.get());
        position.setOrganizationalUnitID(organizationalUnit.get());
        //  position.setNewPosition(command.getNewPosition());
        //  position.setPointsAvailable(command.getPointsAvailable());
        position.setCreationResolutionID(transformation.get());
        // position.setResolutionSuppressionID(command.getResolutionSuppressionID());


        return positionSavePort.savePosition(position);

    }

    @Override
    public Position updatePositionByAvailablePoint(Long id, Position command) {

        Optional<Position> optionalPosition = positionRetrievePort.findPositionById(id);

        if (!optionalPosition.isPresent()) {
            throw new RuntimeException("No se encontro el cargo");
        }

        Position position = optionalPosition.get();

        position.setPointsAvailable(command.getPointsAvailable());
        position.setPositionStatus(command.getPositionStatus());
        position.setNewPosition(command.getNewPosition());
        position.setResolutionSuppressionID(command.getResolutionSuppressionID());

        logger.error("NUEVO ESTADO CARGO ", command.getPositionStatus());
        return positionSavePort.savePosition(position);
    }

    @Override
    public Position updatePositionByOriginator(Long id, Position command) {

        Optional<Position> optionalPosition = positionRetrievePort.findPositionById(id);

        if (!optionalPosition.isPresent()) {
            throw new RuntimeException("No se encontro el cargo");
        }

        Position position = optionalPosition.get();

        position.setNewPosition(command.getNewPosition());

        return positionSavePort.savePosition(position);
    }
}
