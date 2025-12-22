package com.fich.sarh.position.infrastructure.adapter.output.persistence.mapper;

import com.fich.sarh.position.domain.model.Position;
import com.fich.sarh.position.infrastructure.adapter.input.rest.model.request.PositionRequest;
import com.fich.sarh.position.infrastructure.adapter.input.rest.model.response.PositionResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PositionRestMapper {

    PositionRestMapper INSTANCE = Mappers.getMapper(PositionRestMapper.class);


    PositionResponse toPositionResponse(Position position);

    Position toPosition(PositionRequest request);


    List<PositionResponse> toPositionResponseList(List<Position> positionList);


}
