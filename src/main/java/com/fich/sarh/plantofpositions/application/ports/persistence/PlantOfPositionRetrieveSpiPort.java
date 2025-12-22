package com.fich.sarh.plantofpositions.application.ports.persistence;

import com.fich.sarh.plantofpositions.domain.model.PlantOfPosition;
import com.fich.sarh.plantofpositions.domain.model.PlantOfPositionDto;
import com.fich.sarh.plantofpositions.domain.model.PlantProjectionDTO;

import java.util.List;
import java.util.Optional;

public interface PlantOfPositionRetrieveSpiPort {

    boolean existsByPositionAndAgent(Long positionId, Long agentId);
    List<PlantOfPositionDto> findAllPlantOfPosition();

    Optional<PlantOfPosition> findById(Long id);



}
