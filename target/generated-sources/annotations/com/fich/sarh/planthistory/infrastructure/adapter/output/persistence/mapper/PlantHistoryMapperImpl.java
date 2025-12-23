package com.fich.sarh.planthistory.infrastructure.adapter.output.persistence.mapper;

import com.fich.sarh.agent.domain.model.Agent;
import com.fich.sarh.agent.infrastructure.adapter.output.persistence.entity.AgentEntity;
import com.fich.sarh.organizationalsubunit.domain.model.OrganizationalSubUnit;
import com.fich.sarh.organizationalsubunit.infrastructure.adapter.output.persistence.entity.OrganizationalSubUnitEntity;
import com.fich.sarh.organizationalunit.domain.model.OrganizationalUnit;
import com.fich.sarh.organizationalunit.infrastructure.adapter.output.persistence.entity.OrganizationalUnitEntity;
import com.fich.sarh.planthistory.domain.model.PlantHistory;
import com.fich.sarh.planthistory.infrastructure.adapter.output.persistence.entity.PlantHistoryEntity;
import com.fich.sarh.plantofpositions.domain.model.PlantOfPosition;
import com.fich.sarh.plantofpositions.infrastructure.adapter.output.persistence.entity.PlantOfPositionEntity;
import com.fich.sarh.point.domain.model.Point;
import com.fich.sarh.point.infrastructure.adapter.output.persistence.entity.PointEntity;
import com.fich.sarh.position.domain.model.Position;
import com.fich.sarh.position.infrastructure.adapter.output.persistence.entity.PositionEntity;
import com.fich.sarh.transformation.domain.model.Transformation;
import com.fich.sarh.transformation.infrastructure.adapter.output.persistence.entity.TransformationEntity;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-12-23T10:33:59-0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.9 (Microsoft)"
)
public class PlantHistoryMapperImpl implements PlantHistoryMapper {

    @Override
    public PlantHistoryEntity toPlantHistoryEntity(PlantHistory plantHistory) {
        if ( plantHistory == null ) {
            return null;
        }

        PlantHistoryEntity.PlantHistoryEntityBuilder plantHistoryEntity = PlantHistoryEntity.builder();

        plantHistoryEntity.id( plantHistory.getId() );
        plantHistoryEntity.plantOfPosition( plantOfPositionToPlantOfPositionEntity( plantHistory.getPlantOfPosition() ) );
        plantHistoryEntity.plantStatus( plantHistory.getPlantStatus() );
        plantHistoryEntity.dateFrom( plantHistory.getDateFrom() );
        plantHistoryEntity.dateTo( plantHistory.getDateTo() );

        return plantHistoryEntity.build();
    }

    @Override
    public PlantHistory toPlantHistory(PlantHistoryEntity entity) {
        if ( entity == null ) {
            return null;
        }

        PlantHistory.PlantHistoryBuilder plantHistory = PlantHistory.builder();

        plantHistory.id( entity.getId() );
        plantHistory.plantOfPosition( plantOfPositionEntityToPlantOfPosition( entity.getPlantOfPosition() ) );
        plantHistory.plantStatus( entity.getPlantStatus() );
        plantHistory.dateFrom( entity.getDateFrom() );
        plantHistory.dateTo( entity.getDateTo() );

        return plantHistory.build();
    }

    @Override
    public List<PlantHistory> toPlantHistoryList(List<PlantHistoryEntity> plantHistoryEntityList) {
        if ( plantHistoryEntityList == null ) {
            return null;
        }

        List<PlantHistory> list = new ArrayList<PlantHistory>( plantHistoryEntityList.size() );
        for ( PlantHistoryEntity plantHistoryEntity : plantHistoryEntityList ) {
            list.add( toPlantHistory( plantHistoryEntity ) );
        }

        return list;
    }

    protected PointEntity pointToPointEntity(Point point) {
        if ( point == null ) {
            return null;
        }

        PointEntity.PointEntityBuilder pointEntity = PointEntity.builder();

        pointEntity.id( point.getId() );
        pointEntity.positionCode( point.getPositionCode() );
        pointEntity.namePosition( point.getNamePosition() );
        pointEntity.dedication( point.getDedication() );
        pointEntity.amountPoint( point.getAmountPoint() );
        pointEntity.date( point.getDate() );

        return pointEntity.build();
    }

    protected AgentEntity agentToAgentEntity(Agent agent) {
        if ( agent == null ) {
            return null;
        }

        AgentEntity.AgentEntityBuilder agentEntity = AgentEntity.builder();

        agentEntity.id( agent.getId() );
        agentEntity.firstname( agent.getFirstname() );
        agentEntity.lastname( agent.getLastname() );
        agentEntity.documenttype( agent.getDocumenttype() );
        agentEntity.document( agent.getDocument() );
        agentEntity.birthdate( agent.getBirthdate() );
        agentEntity.leavingdate( agent.getLeavingdate() );
        agentEntity.deceased( agent.isDeceased() );
        agentEntity.file( agent.getFile() );
        agentEntity.address( agent.getAddress() );
        agentEntity.phone( agent.getPhone() );
        agentEntity.email( agent.getEmail() );

        return agentEntity.build();
    }

    protected OrganizationalUnitEntity organizationalUnitToOrganizationalUnitEntity(OrganizationalUnit organizationalUnit) {
        if ( organizationalUnit == null ) {
            return null;
        }

        OrganizationalUnitEntity.OrganizationalUnitEntityBuilder organizationalUnitEntity = OrganizationalUnitEntity.builder();

        organizationalUnitEntity.id( organizationalUnit.getId() );
        organizationalUnitEntity.nameUnit( organizationalUnit.getNameUnit() );
        organizationalUnitEntity.director( agentToAgentEntity( organizationalUnit.getDirector() ) );
        organizationalUnitEntity.viceDirector( agentToAgentEntity( organizationalUnit.getViceDirector() ) );

        return organizationalUnitEntity.build();
    }

    protected TransformationEntity transformationToTransformationEntity(Transformation transformation) {
        if ( transformation == null ) {
            return null;
        }

        TransformationEntity.TransformationEntityBuilder transformationEntity = TransformationEntity.builder();

        transformationEntity.id( transformation.getId() );
        transformationEntity.date( transformation.getDate() );
        transformationEntity.resolutionNumber( transformation.getResolutionNumber() );
        transformationEntity.reason( transformation.getReason() );

        return transformationEntity.build();
    }

    protected PositionEntity positionToPositionEntity(Position position) {
        if ( position == null ) {
            return null;
        }

        PositionEntity.PositionEntityBuilder positionEntity = PositionEntity.builder();

        positionEntity.id( position.getId() );
        positionEntity.pointID( pointToPointEntity( position.getPointID() ) );
        positionEntity.organizationalUnitID( organizationalUnitToOrganizationalUnitEntity( position.getOrganizationalUnitID() ) );
        positionEntity.positionStatus( position.getPositionStatus() );
        positionEntity.newPosition( positionToPositionEntity( position.getNewPosition() ) );
        positionEntity.pointsAvailable( position.getPointsAvailable() );
        positionEntity.creationResolutionID( transformationToTransformationEntity( position.getCreationResolutionID() ) );
        positionEntity.resolutionSuppressionID( transformationToTransformationEntity( position.getResolutionSuppressionID() ) );

        return positionEntity.build();
    }

    protected OrganizationalSubUnitEntity organizationalSubUnitToOrganizationalSubUnitEntity(OrganizationalSubUnit organizationalSubUnit) {
        if ( organizationalSubUnit == null ) {
            return null;
        }

        OrganizationalSubUnitEntity.OrganizationalSubUnitEntityBuilder organizationalSubUnitEntity = OrganizationalSubUnitEntity.builder();

        organizationalSubUnitEntity.id( organizationalSubUnit.getId() );
        organizationalSubUnitEntity.nameSubUnit( organizationalSubUnit.getNameSubUnit() );
        organizationalSubUnitEntity.guaraniCode( organizationalSubUnit.getGuaraniCode() );
        organizationalSubUnitEntity.organizationalUnit( organizationalUnitToOrganizationalUnitEntity( organizationalSubUnit.getOrganizationalUnit() ) );

        return organizationalSubUnitEntity.build();
    }

    protected PlantOfPositionEntity plantOfPositionToPlantOfPositionEntity(PlantOfPosition plantOfPosition) {
        if ( plantOfPosition == null ) {
            return null;
        }

        PlantOfPositionEntity.PlantOfPositionEntityBuilder plantOfPositionEntity = PlantOfPositionEntity.builder();

        plantOfPositionEntity.id( plantOfPosition.getId() );
        plantOfPositionEntity.position( positionToPositionEntity( plantOfPosition.getPosition() ) );
        plantOfPositionEntity.agent( agentToAgentEntity( plantOfPosition.getAgent() ) );
        plantOfPositionEntity.organizationalSubUnit( organizationalSubUnitToOrganizationalSubUnitEntity( plantOfPosition.getOrganizationalSubUnit() ) );
        plantOfPositionEntity.characterplantID( plantOfPosition.getCharacterplantID() );
        plantOfPositionEntity.currentStatusID( plantOfPosition.getCurrentStatusID() );

        return plantOfPositionEntity.build();
    }

    protected Point pointEntityToPoint(PointEntity pointEntity) {
        if ( pointEntity == null ) {
            return null;
        }

        Point.PointBuilder point = Point.builder();

        point.id( pointEntity.getId() );
        point.positionCode( pointEntity.getPositionCode() );
        point.namePosition( pointEntity.getNamePosition() );
        point.dedication( pointEntity.getDedication() );
        point.amountPoint( pointEntity.getAmountPoint() );
        point.date( pointEntity.getDate() );

        return point.build();
    }

    protected Agent agentEntityToAgent(AgentEntity agentEntity) {
        if ( agentEntity == null ) {
            return null;
        }

        Agent.AgentBuilder agent = Agent.builder();

        agent.id( agentEntity.getId() );
        agent.firstname( agentEntity.getFirstname() );
        agent.lastname( agentEntity.getLastname() );
        agent.documenttype( agentEntity.getDocumenttype() );
        agent.document( agentEntity.getDocument() );
        agent.birthdate( agentEntity.getBirthdate() );
        agent.leavingdate( agentEntity.getLeavingdate() );
        agent.deceased( agentEntity.isDeceased() );
        agent.file( agentEntity.getFile() );
        agent.address( agentEntity.getAddress() );
        agent.phone( agentEntity.getPhone() );
        agent.email( agentEntity.getEmail() );

        return agent.build();
    }

    protected OrganizationalUnit organizationalUnitEntityToOrganizationalUnit(OrganizationalUnitEntity organizationalUnitEntity) {
        if ( organizationalUnitEntity == null ) {
            return null;
        }

        OrganizationalUnit.OrganizationalUnitBuilder organizationalUnit = OrganizationalUnit.builder();

        organizationalUnit.id( organizationalUnitEntity.getId() );
        organizationalUnit.nameUnit( organizationalUnitEntity.getNameUnit() );
        organizationalUnit.director( agentEntityToAgent( organizationalUnitEntity.getDirector() ) );
        organizationalUnit.viceDirector( agentEntityToAgent( organizationalUnitEntity.getViceDirector() ) );

        return organizationalUnit.build();
    }

    protected Transformation transformationEntityToTransformation(TransformationEntity transformationEntity) {
        if ( transformationEntity == null ) {
            return null;
        }

        Transformation.TransformationBuilder transformation = Transformation.builder();

        transformation.id( transformationEntity.getId() );
        transformation.date( transformationEntity.getDate() );
        transformation.resolutionNumber( transformationEntity.getResolutionNumber() );
        transformation.reason( transformationEntity.getReason() );

        return transformation.build();
    }

    protected Position positionEntityToPosition(PositionEntity positionEntity) {
        if ( positionEntity == null ) {
            return null;
        }

        Position.PositionBuilder position = Position.builder();

        position.id( positionEntity.getId() );
        position.pointID( pointEntityToPoint( positionEntity.getPointID() ) );
        position.organizationalUnitID( organizationalUnitEntityToOrganizationalUnit( positionEntity.getOrganizationalUnitID() ) );
        position.positionStatus( positionEntity.getPositionStatus() );
        position.newPosition( positionEntityToPosition( positionEntity.getNewPosition() ) );
        position.pointsAvailable( positionEntity.getPointsAvailable() );
        position.creationResolutionID( transformationEntityToTransformation( positionEntity.getCreationResolutionID() ) );
        position.resolutionSuppressionID( transformationEntityToTransformation( positionEntity.getResolutionSuppressionID() ) );

        return position.build();
    }

    protected OrganizationalSubUnit organizationalSubUnitEntityToOrganizationalSubUnit(OrganizationalSubUnitEntity organizationalSubUnitEntity) {
        if ( organizationalSubUnitEntity == null ) {
            return null;
        }

        OrganizationalSubUnit.OrganizationalSubUnitBuilder organizationalSubUnit = OrganizationalSubUnit.builder();

        organizationalSubUnit.id( organizationalSubUnitEntity.getId() );
        organizationalSubUnit.nameSubUnit( organizationalSubUnitEntity.getNameSubUnit() );
        organizationalSubUnit.guaraniCode( organizationalSubUnitEntity.getGuaraniCode() );
        organizationalSubUnit.organizationalUnit( organizationalUnitEntityToOrganizationalUnit( organizationalSubUnitEntity.getOrganizationalUnit() ) );

        return organizationalSubUnit.build();
    }

    protected PlantOfPosition plantOfPositionEntityToPlantOfPosition(PlantOfPositionEntity plantOfPositionEntity) {
        if ( plantOfPositionEntity == null ) {
            return null;
        }

        PlantOfPosition.PlantOfPositionBuilder plantOfPosition = PlantOfPosition.builder();

        plantOfPosition.id( plantOfPositionEntity.getId() );
        plantOfPosition.position( positionEntityToPosition( plantOfPositionEntity.getPosition() ) );
        plantOfPosition.agent( agentEntityToAgent( plantOfPositionEntity.getAgent() ) );
        plantOfPosition.organizationalSubUnit( organizationalSubUnitEntityToOrganizationalSubUnit( plantOfPositionEntity.getOrganizationalSubUnit() ) );
        plantOfPosition.characterplantID( plantOfPositionEntity.getCharacterplantID() );
        plantOfPosition.currentStatusID( plantOfPositionEntity.getCurrentStatusID() );

        return plantOfPosition.build();
    }
}
