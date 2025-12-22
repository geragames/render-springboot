package com.fich.sarh.planthistory.application.ports.persistence;

import com.fich.sarh.planthistory.domain.model.PlantHistory;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

public interface PlantHistoryRetrievePort {

    List<PlantHistory> findAllPlantHistory();

    List<PlantHistory> findPlantHistoryByPlantId(Long id);
    Optional<PlantHistory> findById(Long id);

    Optional<PlantHistory> findPlantByIdActive(Long plantId);

    PlantHistory fetchTopByPlantIdOrderHistoryIdDesc(Long plantId);

}
