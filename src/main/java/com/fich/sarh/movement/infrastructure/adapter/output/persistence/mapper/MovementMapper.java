package com.fich.sarh.movement.infrastructure.adapter.output.persistence.mapper;

import com.fich.sarh.movement.domain.model.Movement;
import com.fich.sarh.movement.infrastructure.adapter.output.persistence.entity.MovementEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MovementMapper {

    //MovementMapper INSTANCE = Mappers.getMapper(MovementMapper.class);


    @Mappings({
            @Mapping(source = "plant.id", target = "plantId"),
            @Mapping(source = "position.id", target = "positionId")
    })
    Movement    toMovement(MovementEntity entity);

    @Mappings({
            @Mapping(target = "plant", ignore = true),
            @Mapping(target = "position", ignore = true),
    })
    MovementEntity toMovementEntity(Movement movement);

  /*  @Mappings({
            @Mapping(source = "id.plantId", target = "plantId"),
            @Mapping(source = "id.positionId", target = "positionId")
    })
    List<Movement> toMovementList(List<MovementEntity> entityList);*/

}


/*
@Mapper(componentModel = "spring")
public interface MovementMapper {

    MovementMapper INSTANCE = Mappers.getMapper(MovementMapper.class);


    @Mappings({
            @Mapping(source = "id.plantId", target = "plantId"),
            @Mapping(source = "id.positionId", target = "positionId")
    })
    Movement    toMovement(MovementEntity entity);

    @Mappings({
            @Mapping(target = "id.plantId", source = "plantId"),
            @Mapping(target = "id.positionId", source = "positionId")
    })
    MovementEntity toMovementEntity(Movement movement);

    @Mappings({
            @Mapping(source = "id.plantId", target = "plantId"),
            @Mapping(source = "id.positionId", target = "positionId")
    })
    List<Movement> toMovementList(List<MovementEntity> entityList);

}
*/
