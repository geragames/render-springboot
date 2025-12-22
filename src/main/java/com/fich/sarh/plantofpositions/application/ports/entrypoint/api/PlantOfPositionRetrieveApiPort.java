package com.fich.sarh.plantofpositions.application.ports.entrypoint.api;

import com.fich.sarh.plantofpositions.domain.model.PlantOfPosition;
import com.fich.sarh.plantofpositions.domain.model.PlantOfPositionDto;
import com.fich.sarh.plantofpositions.domain.model.PlantProjectionDTO;

import java.util.List;
import java.util.Optional;

public interface PlantOfPositionRetrieveApiPort {

    List<PlantOfPositionDto> getAllPlantOfPositions();

    Optional<PlantOfPosition> findById(Long id);

}
