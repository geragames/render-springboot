package com.fich.sarh.movement.infrastructure.adapter.input.rest.model.response;

import com.fich.sarh.plantofpositions.domain.model.PlantOfPosition;
import com.fich.sarh.point.domain.model.Point;
import com.fich.sarh.position.domain.model.Position;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.Date;
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class MovementResponse {

    Date movementDate;

    String reasonForMovement;

    PlantOfPosition plantID;

    Position positionID;
}
