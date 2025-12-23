package com.fich.sarh.position.infrastructure.adapter.output.persistence.mapper;

import com.fich.sarh.agent.domain.model.Agent;
import com.fich.sarh.agent.infrastructure.adapter.output.persistence.entity.AgentEntity;
import com.fich.sarh.organizationalunit.domain.model.OrganizationalUnit;
import com.fich.sarh.organizationalunit.infrastructure.adapter.output.persistence.entity.OrganizationalUnitEntity;
import com.fich.sarh.point.domain.model.Point;
import com.fich.sarh.point.infrastructure.adapter.output.persistence.entity.PointEntity;
import com.fich.sarh.position.domain.model.Position;
import com.fich.sarh.position.infrastructure.adapter.output.persistence.entity.PositionEntity;
import com.fich.sarh.transformation.domain.model.Transformation;
import com.fich.sarh.transformation.infrastructure.adapter.output.persistence.entity.TransformationEntity;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-12-23T10:34:00-0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.9 (Microsoft)"
)
@Component
public class PositionMapperImpl implements PositionMapper {

    @Override
    public Position toDto(PositionEntity entity) {
        if ( entity == null ) {
            return null;
        }

        Position.PositionBuilder position = Position.builder();

        position.id( entity.getId() );
        position.pointID( pointEntityToPoint( entity.getPointID() ) );
        position.organizationalUnitID( organizationalUnitEntityToOrganizationalUnit( entity.getOrganizationalUnitID() ) );
        position.positionStatus( entity.getPositionStatus() );
        position.newPosition( toDto( entity.getNewPosition() ) );
        position.pointsAvailable( entity.getPointsAvailable() );
        position.creationResolutionID( transformationEntityToTransformation( entity.getCreationResolutionID() ) );
        position.resolutionSuppressionID( transformationEntityToTransformation( entity.getResolutionSuppressionID() ) );

        return position.build();
    }

    @Override
    public PositionEntity toEntity(Position dto) {
        if ( dto == null ) {
            return null;
        }

        PositionEntity.PositionEntityBuilder positionEntity = PositionEntity.builder();

        positionEntity.id( dto.getId() );
        positionEntity.pointID( pointToPointEntity( dto.getPointID() ) );
        positionEntity.organizationalUnitID( organizationalUnitToOrganizationalUnitEntity( dto.getOrganizationalUnitID() ) );
        positionEntity.positionStatus( dto.getPositionStatus() );
        positionEntity.newPosition( toEntity( dto.getNewPosition() ) );
        positionEntity.pointsAvailable( dto.getPointsAvailable() );
        positionEntity.creationResolutionID( transformationToTransformationEntity( dto.getCreationResolutionID() ) );
        positionEntity.resolutionSuppressionID( transformationToTransformationEntity( dto.getResolutionSuppressionID() ) );

        return positionEntity.build();
    }

    @Override
    public List<Position> toDtoList(List<PositionEntity> entityList) {
        if ( entityList == null ) {
            return null;
        }

        List<Position> list = new ArrayList<Position>( entityList.size() );
        for ( PositionEntity positionEntity : entityList ) {
            list.add( toDto( positionEntity ) );
        }

        return list;
    }

    @Override
    public List<PositionEntity> toEntityList(List<Position> dtoList) {
        if ( dtoList == null ) {
            return null;
        }

        List<PositionEntity> list = new ArrayList<PositionEntity>( dtoList.size() );
        for ( Position position : dtoList ) {
            list.add( toEntity( position ) );
        }

        return list;
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
}
