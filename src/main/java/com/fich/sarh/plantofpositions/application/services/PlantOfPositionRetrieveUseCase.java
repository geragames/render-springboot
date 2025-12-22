package com.fich.sarh.plantofpositions.application.services;

import com.fich.sarh.common.UseCase;
import com.fich.sarh.plantofpositions.application.ports.entrypoint.api.PlantOfPositionRetrieveApiPort;
import com.fich.sarh.plantofpositions.application.ports.persistence.PlantOfPositionRetrieveSpiPort;
import com.fich.sarh.plantofpositions.domain.model.PlantOfPosition;
import com.fich.sarh.plantofpositions.domain.model.PlantOfPositionDto;
import com.fich.sarh.plantofpositions.domain.model.PlantProjectionDTO;

import java.util.List;
import java.util.Optional;

@UseCase
public class PlantOfPositionRetrieveUseCase implements PlantOfPositionRetrieveApiPort {

    private final PlantOfPositionRetrieveSpiPort retrievePort;

    public PlantOfPositionRetrieveUseCase(PlantOfPositionRetrieveSpiPort retrievePort) {
        this.retrievePort = retrievePort;
    }


    @Override
    public List<PlantOfPositionDto> getAllPlantOfPositions() {

        return retrievePort.findAllPlantOfPosition();
    }

    @Override
    public Optional<PlantOfPosition> findById(Long id) {

        return retrievePort.findById(id);
    }
}
