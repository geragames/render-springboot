package com.fich.sarh.movement.infrastructure.adapter.input.rest.model.request;

import com.fich.sarh.plantofpositions.domain.model.PlantOfPosition;
import com.fich.sarh.point.domain.model.Point;
import com.fich.sarh.position.domain.model.Position;
import com.fich.sarh.position.infrastructure.adapter.output.persistence.entity.PositionEntity;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MovementRequest {

    Date movementDate;

    String reasonForMovement;

    PlantOfPosition plant;

    Position position;

}
