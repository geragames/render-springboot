package com.fich.sarh.movement.infrastructure.adapter.input.rest.mapper;

import com.fich.sarh.movement.domain.model.Movement;
import com.fich.sarh.movement.infrastructure.adapter.input.rest.model.request.MovementRequest;
import com.fich.sarh.movement.infrastructure.adapter.input.rest.model.response.MovementResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface MovementRestMapper {

    MovementRestMapper INSTANCE = Mappers.getMapper(MovementRestMapper.class);

    MovementResponse toMovementResponse(Movement movement);

    Movement toMovement(MovementRequest request);

    List<MovementResponse> toMovementList(List<Movement> movementList);
}
