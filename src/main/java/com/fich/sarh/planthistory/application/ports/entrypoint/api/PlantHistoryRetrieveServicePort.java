package com.fich.sarh.planthistory.application.ports.entrypoint.api;

import com.fich.sarh.planthistory.domain.model.PlantHistory;

import java.util.List;
import java.util.Optional;

public interface PlantHistoryRetrieveServicePort {

    List<PlantHistory> getAllPlantHistory();
    List<PlantHistory> getPlantHistoryByPlantId(Long id);
    Optional<PlantHistory> findById(Long id);
    Optional<PlantHistory> findPlantByIdActive(Long plantId);
    PlantHistory  fetchTopByPlantIdOrderHistoryIdDesc(Long plantId);
}
