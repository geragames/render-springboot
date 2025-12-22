package com.fich.sarh.position.domain.model;

import com.fich.sarh.common.StatusOfPositions;
import lombok.*;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PositionCommand {

    Long pointId;
    Long organizationalId;
    StatusOfPositions positionStatus;
    List<Long> originPositionIds;
    Long resolutionTransformationId;

    @Override
    public String toString() {
        return "PositionCommand{" +
                "pointId=" + pointId +
                ", organizationalId=" + organizationalId +
                ", originPositionId=" + originPositionIds +
                ", resolutionTransformationId=" + resolutionTransformationId +
                '}';
    }
}
