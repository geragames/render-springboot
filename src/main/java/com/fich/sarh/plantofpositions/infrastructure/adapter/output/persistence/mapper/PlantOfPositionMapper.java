package com.fich.sarh.plantofpositions.infrastructure.adapter.output.persistence.mapper;

import com.fich.sarh.plantofpositions.domain.model.PlantOfPosition;
import com.fich.sarh.plantofpositions.infrastructure.adapter.output.persistence.entity.PlantOfPositionEntity;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper
public interface PlantOfPositionMapper {

   PlantOfPositionMapper INSTANCE = Mappers.getMapper(PlantOfPositionMapper.class);

   PlantOfPosition toPlantOfPosition(PlantOfPositionEntity entity);

   PlantOfPositionEntity toPlantOfPositionEntity(PlantOfPosition plantposition);

   List<PlantOfPosition> toPlantOfPositionList(List<PlantOfPositionEntity> entityList);
}
