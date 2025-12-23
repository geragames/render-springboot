package com.fich.sarh.movement.infrastructure.adapter.output.persistence.mapper;

import com.fich.sarh.movement.domain.model.Movement;
import com.fich.sarh.movement.infrastructure.adapter.output.persistence.entity.MovementEntity;
import com.fich.sarh.plantofpositions.infrastructure.adapter.output.persistence.entity.PlantOfPositionEntity;
import com.fich.sarh.position.infrastructure.adapter.output.persistence.entity.PositionEntity;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-12-23T10:33:59-0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.9 (Microsoft)"
)
@Component
public class MovementMapperImpl implements MovementMapper {

    @Override
    public Movement toMovement(MovementEntity entity) {
        if ( entity == null ) {
            return null;
        }

        Movement.MovementBuilder movement = Movement.builder();

        movement.plantId( entityPlantId( entity ) );
        movement.positionId( entityPositionId( entity ) );
        movement.id( entity.getId() );
        movement.movementDate( entity.getMovementDate() );
        movement.reasonForMovement( entity.getReasonForMovement() );

        return movement.build();
    }

    @Override
    public MovementEntity toMovementEntity(Movement movement) {
        if ( movement == null ) {
            return null;
        }

        MovementEntity.MovementEntityBuilder movementEntity = MovementEntity.builder();

        movementEntity.id( movement.getId() );
        movementEntity.movementDate( movement.getMovementDate() );
        movementEntity.reasonForMovement( movement.getReasonForMovement() );

        return movementEntity.build();
    }

    private Long entityPlantId(MovementEntity movementEntity) {
        PlantOfPositionEntity plant = movementEntity.getPlant();
        if ( plant == null ) {
            return null;
        }
        return plant.getId();
    }

    private Long entityPositionId(MovementEntity movementEntity) {
        PositionEntity position = movementEntity.getPosition();
        if ( position == null ) {
            return null;
        }
        return position.getId();
    }
}
