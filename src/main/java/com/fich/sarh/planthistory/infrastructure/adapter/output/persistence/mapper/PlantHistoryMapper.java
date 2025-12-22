package com.fich.sarh.planthistory.infrastructure.adapter.output.persistence.mapper;

import com.fich.sarh.planthistory.domain.model.PlantHistory;
import com.fich.sarh.planthistory.infrastructure.adapter.output.persistence.entity.PlantHistoryEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "string", unmappedSourcePolicy = ReportingPolicy.IGNORE)
public interface PlantHistoryMapper {

    PlantHistoryMapper INSTANCE = Mappers.getMapper(PlantHistoryMapper.class);
    PlantHistoryEntity toPlantHistoryEntity(PlantHistory plantHistory);
    PlantHistory toPlantHistory(PlantHistoryEntity entity);
    List<PlantHistory> toPlantHistoryList(List<PlantHistoryEntity> plantHistoryEntityList);
}
