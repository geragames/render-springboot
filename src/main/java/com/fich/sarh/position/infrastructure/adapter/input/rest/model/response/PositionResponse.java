package com.fich.sarh.position.infrastructure.adapter.input.rest.model.response;

import com.fich.sarh.common.StatusOfPositions;
import com.fich.sarh.organizationalunit.domain.model.OrganizationalUnit;
import com.fich.sarh.point.domain.model.Point;
import com.fich.sarh.position.domain.model.Position;
import com.fich.sarh.transformation.domain.model.Transformation;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.math.BigInteger;
import java.util.List;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PositionResponse {

    Point pointID;
    OrganizationalUnit organizationalUnitID;
    StatusOfPositions positionStatus;
    Position newPosition;
    // List<Position> originPosition;
    Long pointsAvailable;
    Transformation creationResolutionID;
    Transformation resolutionSuppressionID;
}
