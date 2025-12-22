package com.fich.sarh.position.infrastructure.adapter.output.persistence.mapper;

import com.fich.sarh.position.domain.model.Position;
import com.fich.sarh.position.infrastructure.adapter.output.persistence.entity.PositionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface PositionMapper {

    PositionMapper INSTANCE = Mappers.getMapper(PositionMapper.class);
    Position toDto(PositionEntity entity);


    PositionEntity toEntity(Position dto);

    List<Position> toDtoList(List<PositionEntity> entityList);

    List<PositionEntity> toEntityList(List<Position> dtoList);
}




/*
@Mapper(componentModel = "spring", uses = {PointMapper.class, OrganizationalUnitMapper.class, TransformationMapper.class, PositionMapper.class})
public interface PositionMapper {

    // Ya no necesitas INSTANCE = Mappers.getMapper(PositionMapper.class);
    // Spring manejará la inyección de esta interfaz.

    @Mapping(source = "pointID", target = "pointID")
    @Mapping(source = "organizationalUnitID", target = "organizationalUnitID")
    @Mapping(source = "creationResolutionID", target = "creationResolutionID")
    @Mapping(source = "resolutionSuppressionID", target = "resolutionSuppressionID")
    @Mapping(source = "newPosition", target = "newPosition")
    @Mapping(source = "originPosition", target = "originPosition")
    Position toDto(PositionEntity entity);

    @Mapping(source = "pointID", target = "pointID")
    @Mapping(source = "organizationalUnitID", target = "organizationalUnitID")
    @Mapping(source = "creationResolutionID", target = "creationResolutionID")
    @Mapping(source = "resolutionSuppressionID", target = "resolutionSuppressionID")
    @Mapping(source = "newPosition", target = "newPosition")
    @Mapping(source = "originPosition", target = "originPosition")
    PositionEntity toEntity(Position dto);

    List<Position> toDtoList(List<PositionEntity> entityList);

    List<PositionEntity> toEntityList(List<Position> dtoList);
}
*/