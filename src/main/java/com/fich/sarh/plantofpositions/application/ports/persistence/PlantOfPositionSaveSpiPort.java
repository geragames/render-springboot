package com.fich.sarh.plantofpositions.application.ports.persistence;

import com.fich.sarh.plantofpositions.domain.model.PlantOfPosition;

public interface PlantOfPositionSaveSpiPort {

    PlantOfPosition savePlantOfPosition(PlantOfPosition plantpositionCommand);
}
