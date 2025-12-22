package com.fich.sarh.plantofpositions.infrastructure.adapter.output.persistence.adapter;

import com.fich.sarh.common.PersistenceAdapter;
import com.fich.sarh.common.PlantStatus;
import com.fich.sarh.common.exceptions.BusinessRuleViolationException;
import com.fich.sarh.plantofpositions.application.ports.persistence.PlantOfPositionLoadSpiPort;
import com.fich.sarh.plantofpositions.application.ports.persistence.PlantOfPositionRetrieveSpiPort;
import com.fich.sarh.plantofpositions.application.ports.persistence.PlantOfPositionSaveSpiPort;
import com.fich.sarh.plantofpositions.domain.model.PlantOfPosition;
import com.fich.sarh.plantofpositions.domain.model.PlantOfPositionDto;
import com.fich.sarh.plantofpositions.domain.model.PlantProjectionDTO;
import com.fich.sarh.plantofpositions.infrastructure.adapter.output.persistence.entity.PlantOfPositionEntity;
import com.fich.sarh.plantofpositions.infrastructure.adapter.output.persistence.mapper.PlantOfPositionMapper;
import com.fich.sarh.plantofpositions.infrastructure.adapter.output.persistence.repository.PlantOfPositionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

@PersistenceAdapter
public class PlantOfPositionPersistenceAdapter implements PlantOfPositionRetrieveSpiPort,
        PlantOfPositionSaveSpiPort, PlantOfPositionLoadSpiPort {

    private final PlantOfPositionRepository plantRepository;

    private PlantOfPositionMapper mapper;

    Logger logger = LoggerFactory.getLogger(PlantOfPositionPersistenceAdapter.class);
    public PlantOfPositionPersistenceAdapter(PlantOfPositionRepository plantRepository) {
        this.plantRepository = plantRepository;
    }

    @Override
    public Optional<PlantOfPosition> loadPlantOfPosition(Long id) {
        return plantRepository.findById(id).map(
                mapper::toPlantOfPosition
        );
    }

    @Override
    public boolean existsByPositionAndAgent(Long positionId, Long agentId) {
        return  plantRepository.existsPlantPositionByAgentAndPosition(positionId, agentId,
                PlantStatus.OCUPADO_TRANSITORIAMENTE,
                PlantStatus.ACTIVO);
    }

    @Override
    public List<PlantOfPositionDto> findAllPlantOfPosition() {

        logger.error("CANTIDAD DE REGISTROS  " + String.valueOf(plantRepository.findAll().size()) );

        return plantRepository.findAllPlantOfPosition();

    }

    @Override
    public Optional<PlantOfPosition> findById(Long id) {

       Optional<PlantOfPositionEntity> plantOfPosition =  plantRepository.findById(id);
       if(!plantOfPosition.isPresent()){
           throw new BusinessRuleViolationException("No existe el elemento con el ID %s");
       }

       return Optional.of(PlantOfPositionMapper.INSTANCE.toPlantOfPosition(plantOfPosition.get()));
    }


    @Override
    public PlantOfPosition savePlantOfPosition(PlantOfPosition plantposition) {
        return PlantOfPositionMapper.INSTANCE
                .toPlantOfPosition(plantRepository
                .save(PlantOfPositionMapper.INSTANCE.toPlantOfPositionEntity(plantposition)) ) ;
    }
}
