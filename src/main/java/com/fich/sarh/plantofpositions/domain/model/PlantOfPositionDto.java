package com.fich.sarh.plantofpositions.domain.model;

import com.fich.sarh.agent.domain.model.Agent;
import com.fich.sarh.position.domain.model.Position;

import java.time.LocalDate;

public interface PlantOfPositionDto {

    Long getId();
    String getCurrentStatusID();
    String getCharacterplantID();
    String getNameSubUnit();
    String getFirstname();
    String getLastname();
    String getDocument();
    String getNamePosition();

}
