package com.fich.sarh.planthistory.infrastructure.adapter.output.persistence.adapter;

import com.fich.sarh.common.PersistenceAdapter;
import com.fich.sarh.common.exceptions.BusinessRuleViolationException;
import com.fich.sarh.planthistory.application.ports.persistence.PlantHistoryRetrievePort;
import com.fich.sarh.planthistory.application.ports.persistence.PlantHistorySavePort;
import com.fich.sarh.planthistory.domain.model.PlantHistory;
import com.fich.sarh.planthistory.infrastructure.adapter.output.persistence.entity.PlantHistoryEntity;
import com.fich.sarh.planthistory.infrastructure.adapter.output.persistence.mapper.PlantHistoryMapper;
import com.fich.sarh.planthistory.infrastructure.adapter.output.persistence.repository.PlantHistoryRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@PersistenceAdapter
public class PlantHistoryPersistenceAdapter implements PlantHistoryRetrievePort, PlantHistorySavePort {

    private final PlantHistoryRepository historyRepository;


    public PlantHistoryPersistenceAdapter(PlantHistoryRepository historyRepository) {
        this.historyRepository = historyRepository;
    }

    @Override
    public List<PlantHistory> findAllPlantHistory() {
        return historyRepository.findAll().stream().map(
                PlantHistoryMapper.INSTANCE::toPlantHistory
        ).collect(Collectors.toList());
    }

    @Override
    public List<PlantHistory> findPlantHistoryByPlantId(Long id) {
        return historyRepository.findPlantHistoryByPlantId(id).stream().map(
                PlantHistoryMapper.INSTANCE::toPlantHistory
        ).collect(Collectors.toList());
    }

    @Override
    public Optional<PlantHistory> findById(Long id) {
        return Optional.of(historyRepository.findById(id).map(PlantHistoryMapper.INSTANCE::toPlantHistory).get());
    }

    @Override
    public Optional<PlantHistory> findPlantByIdActive(Long plantId) {
        return Optional.of(historyRepository.findPlantByIdActive(plantId).map(PlantHistoryMapper.INSTANCE::toPlantHistory).get());
    }


    @Override
    public PlantHistory fetchTopByPlantIdOrderHistoryIdDesc(Long plantId) {
        PlantHistory plantHistory = PlantHistoryMapper.INSTANCE.toPlantHistory(historyRepository
                .findFirstByPlantOfPosition_IdOrderByIdDesc(plantId));

        if (plantHistory == null) {
            throw new BusinessRuleViolationException("No existe el elemento con el ID %s");
        }
        return plantHistory;
    }

    @Override
    public PlantHistory savePlantHistory(PlantHistory plantHistory) {
        return PlantHistoryMapper.INSTANCE.toPlantHistory(
                historyRepository.save(PlantHistoryMapper.INSTANCE.toPlantHistoryEntity(plantHistory)));

    }
}
