package com.fich.sarh.plantofpositions.domain.model;

import com.fich.sarh.common.CharacterPlant;
import com.fich.sarh.common.PlantStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDate;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class PlantOfPositionCommand {

    Long agentId;

    Long positionId;

    CharacterPlant characterplantID;

    PlantStatus currentStatusID;

    Long organizationalSubUnit;

    LocalDate dateFrom;

    LocalDate dateTo;

    String reasonForMovement;

}
