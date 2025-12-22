package com.fich.sarh.plantofpositions.infrastructure.adapter.input.rest.mapper;

import com.fich.sarh.plantofpositions.domain.model.PlantOfPosition;
import com.fich.sarh.plantofpositions.infrastructure.adapter.input.rest.model.request.PlantOfPositionRequest;
import com.fich.sarh.plantofpositions.infrastructure.adapter.input.rest.model.response.PlantOfPositionResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PlantOfPositionRestMapper {

    PlantOfPositionRestMapper INSTANCE = Mappers.getMapper(PlantOfPositionRestMapper.class);

    PlantOfPositionResponse toPlantOfPositionResponse(PlantOfPosition plantposition);

    PlantOfPosition toPlantOfPosition(PlantOfPositionRequest request);

    List<PlantOfPositionResponse> toPlantOfPositionResponseList(List<PlantOfPosition> plantList);
}
