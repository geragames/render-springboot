package com.fich.sarh.planthistory.application.ports.persistence;

import com.fich.sarh.planthistory.domain.model.PlantHistory;

public interface PlantHistorySavePort {

    PlantHistory savePlantHistory(PlantHistory plantHistory);
}
