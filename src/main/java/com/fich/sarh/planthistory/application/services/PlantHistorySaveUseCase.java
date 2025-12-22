package com.fich.sarh.planthistory.application.services;

import com.fich.sarh.planthistory.application.ports.entrypoint.api.PlantHistorySaveServicePort;
import com.fich.sarh.planthistory.application.ports.persistence.PlantHistorySavePort;
import com.fich.sarh.planthistory.domain.model.PlantHistory;
import com.fich.sarh.common.UseCase;

@UseCase
public class PlantHistorySaveUseCase implements PlantHistorySaveServicePort {

    private final PlantHistorySavePort savePort;

    public PlantHistorySaveUseCase(PlantHistorySavePort savePort) {
        this.savePort = savePort;
    }

    @Override
    public PlantHistory savePlantHistory(PlantHistory plantHistory) {
        return savePort.savePlantHistory(plantHistory);
    }
}
