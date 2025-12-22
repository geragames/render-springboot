package com.fich.sarh.plantofpositions.application.ports.persistence;

import com.fich.sarh.plantofpositions.domain.model.PlantOfPosition;

import java.util.Optional;

public interface PlantOfPositionLoadSpiPort {

    Optional<PlantOfPosition> loadPlantOfPosition(Long id);
}
