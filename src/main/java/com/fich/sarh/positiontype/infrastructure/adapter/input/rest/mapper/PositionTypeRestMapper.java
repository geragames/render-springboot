package com.fich.sarh.positiontype.infrastructure.adapter.input.rest.mapper;

import com.fich.sarh.positiontype.domain.model.PositionType;
import com.fich.sarh.positiontype.infrastructure.adapter.input.rest.model.request.PositionTypeRequest;
import com.fich.sarh.positiontype.infrastructure.adapter.input.rest.model.response.PositionTypeResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface PositionTypeRestMapper {

    PositionTypeRestMapper INSTANCE = Mappers.getMapper(PositionTypeRestMapper.class);

    PositionType toPositionType(PositionTypeRequest request);

    PositionTypeResponse toPositionTypeResponse(PositionType position);


}
