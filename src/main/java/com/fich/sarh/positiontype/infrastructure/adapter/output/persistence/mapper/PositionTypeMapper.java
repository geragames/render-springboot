package com.fich.sarh.positiontype.infrastructure.adapter.output.persistence.mapper;

import com.fich.sarh.positiontype.domain.model.PositionType;
import com.fich.sarh.positiontype.infrastructure.adapter.output.persistence.entity.PositionTypeEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface PositionTypeMapper {

    PositionTypeMapper INSTANCE = Mappers.getMapper(PositionTypeMapper.class);

    PositionType toPositionType(PositionTypeEntity entity);

    PositionTypeEntity toPositionTypeEntity(PositionType position);
}
