package com.fich.sarh.planthistory.application.ports.entrypoint.api;

import com.fich.sarh.planthistory.domain.model.PlantHistory;

public interface PlantHistoryUpdateServicePort {

    PlantHistory updatePlantHistory(Long id, PlantHistory command);
}
