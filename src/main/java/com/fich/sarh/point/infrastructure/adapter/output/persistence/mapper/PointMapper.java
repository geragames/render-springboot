package com.fich.sarh.point.infrastructure.adapter.output.persistence.mapper;

import com.fich.sarh.point.domain.model.Point;
import com.fich.sarh.point.infrastructure.adapter.output.persistence.entity.PointEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring" , unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface PointMapper {
    PointMapper INSTANCE = Mappers.getMapper(PointMapper.class);
    Point toDto(PointEntity entity);
    PointEntity toEntity(Point dto);

    List<Point> toDtoList(List<PointEntity> entityList);
}
