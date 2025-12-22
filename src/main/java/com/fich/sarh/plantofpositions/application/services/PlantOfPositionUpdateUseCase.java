package com.fich.sarh.plantofpositions.application.services;

import com.fich.sarh.common.exceptions.BusinessRuleViolationException;
import com.fich.sarh.planthistory.application.ports.entrypoint.api.PlantHistoryUpdateServicePort;
import com.fich.sarh.planthistory.application.ports.persistence.PlantHistoryRetrievePort;
import com.fich.sarh.planthistory.application.ports.persistence.PlantHistorySavePort;
import com.fich.sarh.planthistory.domain.model.PlantHistory;
import com.fich.sarh.common.PlantStatus;
import com.fich.sarh.common.StatusOfPositions;
import com.fich.sarh.common.UseCase;
import com.fich.sarh.plantofpositions.application.ports.entrypoint.api.PlantOfPositionUpdateApiPort;
import com.fich.sarh.plantofpositions.application.ports.persistence.PlantOfPositionRetrieveSpiPort;
import com.fich.sarh.plantofpositions.application.ports.persistence.PlantOfPositionSaveSpiPort;
import com.fich.sarh.plantofpositions.domain.model.PlantOfPosition;
import com.fich.sarh.plantofpositions.infrastructure.adapter.input.rest.model.request.PlantOfPositionRequest;
import com.fich.sarh.position.application.ports.entrypoint.api.PositionUpdateServicePort;
import com.fich.sarh.position.domain.model.Position;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

@UseCase
public class PlantOfPositionUpdateUseCase implements PlantOfPositionUpdateApiPort {

    Logger logger = LoggerFactory.getLogger(PlantOfPositionSaveUseCase.class);
    private final PlantOfPositionRetrieveSpiPort retrievePort;
    private final PlantOfPositionSaveSpiPort savePort;
    private final PositionUpdateServicePort positionUpdateServicePort;
    private final PlantHistoryRetrievePort plantHistoryRetrievePort;
    private final PlantHistorySavePort plantHistorySavePort;
    private final PlantHistoryUpdateServicePort plantHistoryUpdatePort;

    public PlantOfPositionUpdateUseCase(PlantOfPositionRetrieveSpiPort retrievePort, PlantOfPositionSaveSpiPort savePort,
                                        PositionUpdateServicePort positionUpdateServicePort, PlantHistoryRetrievePort plantHistoryRetrievePort, PlantHistorySavePort plantHistorySavePort, PlantHistoryUpdateServicePort plantHistoryUpdatePort) {
        this.retrievePort = retrievePort;
        this.savePort = savePort;
        this.positionUpdateServicePort = positionUpdateServicePort;
        this.plantHistoryRetrievePort = plantHistoryRetrievePort;
        this.plantHistorySavePort = plantHistorySavePort;
        this.plantHistoryUpdatePort = plantHistoryUpdatePort;
    }


    @Override
    public PlantOfPosition updatePlantOfPosition(Long id, PlantOfPositionRequest command) {


        PlantOfPosition plantOfPosition = retrievePort.findById(id).orElseThrow(()->
                           new BusinessRuleViolationException("No existe la planta de cargo"));

        plantOfPosition.setCurrentStatusID(command.getCurrentStatusID());

        // Actualiza EL ESTADO  DEL CARGO EN FUNCION AL ESTADO DE LA PLANTA.
        updateRegisterPosition(plantOfPosition.getPosition(), command.getCurrentStatusID());

        PlantHistory plantHistory = plantHistoryRetrievePort.fetchTopByPlantIdOrderHistoryIdDesc(plantOfPosition.getId());


        if (plantHistory != null && plantHistory.getDateTo() == null) {

            plantHistory.setDateTo(command.getDateTo());
            plantHistory.setPlantStatus(command.getCurrentStatusID());
            plantHistoryUpdatePort.updatePlantHistory(plantHistory.getId(), plantHistory);
        }
        if (plantHistory != null && command.getDateTo() != null) {

            PlantHistory plantHistoriaNew = PlantHistory.builder().plantStatus(command.getCurrentStatusID())

                    .plantOfPosition(plantOfPosition)
                    .dateFrom(command.getDateFrom())

                     .build();

            plantHistoriaNew = plantHistorySavePort.savePlantHistory(plantHistoriaNew);

        }


        return plantOfPosition;


    }

    private void updateRegisterPosition(Position position, PlantStatus plantStatus) {

        if (plantStatus.equals(PlantStatus.FINALIZADO)) {
            position.setPositionStatus(StatusOfPositions.VACANTE_DEFINITIVA);
        }
        if (plantStatus.equals(PlantStatus.LICENCIA_TRANSITORIA)) {
            position.setPositionStatus(StatusOfPositions.VACANTE_TRANSITORIA);
        }
        if (plantStatus.equals(PlantStatus.OCUPADO_TRANSITORIAMENTE)) {
            position.setPositionStatus(StatusOfPositions.ACTIVO);
        }

        positionUpdateServicePort.updatePositionByOriginator(position.getId(), position);
    }
}

/*retrievePort.findById(id).map(
                plantposition -> {
                       plantposition.setAgentID(command.getAgentId());
                       plantposition.setPositionID(command.getPositionId());
                       plantposition.setCharacterplantID(command.getCharacterplantID());
                       plantposition.setCurrentStatusID(command.getCurrentStatusID());

                       return savePort.savePlantOfPosition(plantposition);
                }
        ).get(); */