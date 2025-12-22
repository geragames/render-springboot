package com.fich.sarh.transformation.infrastructure.adapter.output.persistence.mapper;

import com.fich.sarh.transformation.domain.model.Transformation;
import com.fich.sarh.transformation.infrastructure.adapter.output.persistence.entity.TransformationEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TransformationMapper {
    TransformationMapper INSTANCE = Mappers.getMapper(TransformationMapper.class);
    Transformation toDto(TransformationEntity entity);
    TransformationEntity toEntity(Transformation dto);
}
