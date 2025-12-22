package com.fich.sarh.planthistory.application.services;

import com.fich.sarh.planthistory.application.ports.entrypoint.api.PlantHistoryUpdateServicePort;
import com.fich.sarh.planthistory.application.ports.persistence.PlantHistoryRetrievePort;
import com.fich.sarh.planthistory.application.ports.persistence.PlantHistorySavePort;
import com.fich.sarh.planthistory.domain.model.PlantHistory;
import com.fich.sarh.common.UseCase;
import com.fich.sarh.common.exceptions.BusinessRuleViolationException;

import java.util.Optional;

@UseCase
public class PlantHistoryUpdateUseCase implements PlantHistoryUpdateServicePort {

    private final PlantHistoryRetrievePort plantHistoryRetrievePort;
    private final PlantHistorySavePort plantHistorySavePort;

    public PlantHistoryUpdateUseCase(PlantHistoryRetrievePort plantHistoryRetrievePort, PlantHistorySavePort plantHistorySavePort) {
        this.plantHistoryRetrievePort = plantHistoryRetrievePort;

        this.plantHistorySavePort = plantHistorySavePort;
    }

    @Override

    public PlantHistory updatePlantHistory(Long id, PlantHistory command) {
        Optional<PlantHistory> plantHistory = plantHistoryRetrievePort.findById(id);

        if(!plantHistory.isPresent()){
            throw new BusinessRuleViolationException("No existe el historial de esta planta");
        }

        plantHistory.get().setPlantStatus(command.getPlantStatus());
        plantHistory.get().setPlantOfPosition(command.getPlantOfPosition());
        plantHistory.get().setDateFrom(command.getDateFrom());
        plantHistory.get().setDateTo(command.getDateTo());

        return plantHistorySavePort.savePlantHistory(plantHistory.get());
    }
}
