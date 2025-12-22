package com.fich.sarh.position.domain.model;

import com.fich.sarh.common.StatusOfPositions;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.logging.Level;


public interface PositionDto {

    Long getId();
    String getNamePosition();
    String getNameUnit();
    Long getPointsAvailable();
    Long getAmountPoint();
    StatusOfPositions getPositionStatus();
    String getResolutionNumber();


}
