package com.fich.sarh.plantofpositions.application.services;

import com.fich.sarh.common.UseCase;
import com.fich.sarh.plantofpositions.application.ports.entrypoint.api.PlantOfPositionSearchApiPort;
import com.fich.sarh.plantofpositions.application.ports.persistence.PlantOfPositionSearchSpiPort;
import com.fich.sarh.plantofpositions.domain.model.PlantFilter;
import com.fich.sarh.plantofpositions.domain.model.PlantOfPosition;
import com.fich.sarh.plantofpositions.domain.model.PlantOfPositionDto;
import com.fich.sarh.plantofpositions.domain.model.PlantProjectionDTO;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;


@UseCase
public class PlantOfPositionSearchUseCase implements PlantOfPositionSearchApiPort {

    private final PlantOfPositionSearchSpiPort plantSearchSpiPort;

    public PlantOfPositionSearchUseCase(PlantOfPositionSearchSpiPort plantSearchSpiPort) {
        this.plantSearchSpiPort = plantSearchSpiPort;
    }

    @Override
    public List<PlantProjectionDTO> search(PlantFilter filter) {
        return plantSearchSpiPort.search(filter);
    }
}
