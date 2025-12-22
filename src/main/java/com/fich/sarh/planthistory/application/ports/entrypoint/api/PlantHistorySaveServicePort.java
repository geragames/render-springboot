package com.fich.sarh.planthistory.application.ports.entrypoint.api;

import com.fich.sarh.planthistory.domain.model.PlantHistory;

public interface PlantHistorySaveServicePort {

    PlantHistory savePlantHistory(PlantHistory plantHistory);
}
