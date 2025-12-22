package com.fich.sarh.planthistory.infrastructure.adapter.input.rest.mapper;

import com.fich.sarh.planthistory.domain.model.PlantHistory;
import com.fich.sarh.planthistory.infrastructure.adapter.input.rest.model.request.PlantHistoryRequest;
import com.fich.sarh.planthistory.infrastructure.adapter.input.rest.model.response.PlantHistoryResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;


@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PlantHistoryRestMapper {

    PlantHistoryRestMapper INSTANCE = Mappers.getMapper(PlantHistoryRestMapper.class);
    PlantHistoryResponse toPlantHistoryResponse(PlantHistory plantHistory);

    PlantHistory toPlantHistory(PlantHistoryRequest plantHistoryRequest);

    List<PlantHistoryResponse> toPlantHistoryResponseList(List<PlantHistory> plantHistories);
}
