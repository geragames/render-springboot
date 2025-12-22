package com.fich.sarh.planthistory.application.services;

import com.fich.sarh.planthistory.application.ports.entrypoint.api.PlantHistoryRetrieveServicePort;
import com.fich.sarh.planthistory.application.ports.persistence.PlantHistoryRetrievePort;
import com.fich.sarh.planthistory.domain.model.PlantHistory;
import com.fich.sarh.common.UseCase;

import java.util.List;
import java.util.Optional;

@UseCase
public class PlantHistoryRetrieveUseCase implements PlantHistoryRetrieveServicePort {

    private final PlantHistoryRetrievePort retrievePort;


    public PlantHistoryRetrieveUseCase(PlantHistoryRetrievePort retrievePort) {
        this.retrievePort = retrievePort;

    }

    @Override
    public List<PlantHistory> getAllPlantHistory() {
        return retrievePort.findAllPlantHistory();
    }

    @Override
    public List<PlantHistory> getPlantHistoryByPlantId(Long id) {
        return retrievePort.findPlantHistoryByPlantId(id);
    }

    @Override
    public Optional<PlantHistory> findById(Long id) {
        return retrievePort.findById(id);
    }

    @Override
    public Optional<PlantHistory> findPlantByIdActive(Long plantId) {
        return retrievePort.findPlantByIdActive(plantId);
    }

    @Override
    public PlantHistory fetchTopByPlantIdOrderHistoryIdDesc(Long plantId) {
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                        
        PlantHistory plantHistory = retrievePort.fetchTopByPlantIdOrderHistoryIdDesc(plantId);

        return plantHistory;
    }
}
