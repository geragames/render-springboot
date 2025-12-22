package com.fich.sarh.position.domain.model;

import com.fich.sarh.common.StatusOfPositions;
import com.fich.sarh.organizationalunit.domain.model.OrganizationalUnit;
import com.fich.sarh.point.domain.model.Point;
import com.fich.sarh.position.infrastructure.adapter.output.persistence.entity.PositionEntity;
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
@ToString
public class Position {

    Long id;
    Point pointID;
    OrganizationalUnit organizationalUnitID;
    StatusOfPositions positionStatus;
    Position newPosition;
   // List<Position> originPosition;
    Long pointsAvailable;
    Transformation creationResolutionID;
    Transformation resolutionSuppressionID;

    @Override
    public String toString() {
        return "Position{" +
                "pointID=" + pointID +
                ", organizationalUnitID=" + organizationalUnitID +
                ", positionStatus=" + positionStatus +
                ", newPosition=" + newPosition +
              //  ", originPosition=" + originPosition +
                ", pointsAvailable=" + pointsAvailable +
                ", creationResolutionID=" + creationResolutionID +
                ", resolutionSuppressionID=" + resolutionSuppressionID +
                '}';
    }
}
