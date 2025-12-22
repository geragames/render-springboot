package com.fich.sarh.plantofpositions.application.services;

import com.fich.sarh.common.CharacterPlant;
import com.fich.sarh.common.PlantStatus;
import com.fich.sarh.organizationalsubunit.application.ports.persistence.OrganizationalSubUnitRetrieveSpiPort;
import com.fich.sarh.organizationalsubunit.domain.model.OrganizationalSubUnit;
import com.fich.sarh.planthistory.application.ports.persistence.PlantHistorySavePort;
import com.fich.sarh.planthistory.domain.model.PlantHistory;
import com.fich.sarh.agent.application.ports.persistence.AgentRetrievePort;
import com.fich.sarh.agent.domain.model.Agent;
import com.fich.sarh.common.UseCase;
import com.fich.sarh.common.exceptions.BusinessRuleViolationException;
import com.fich.sarh.movement.application.ports.persistence.MovementSavePort;
import com.fich.sarh.movement.domain.model.Movement;
import com.fich.sarh.movement.infrastructure.adapter.output.persistence.mapper.MovementMapper;
import com.fich.sarh.plantofpositions.application.ports.entrypoint.api.PlantOfPositionSaveApiPort;
import com.fich.sarh.plantofpositions.application.ports.persistence.PlantOfPositionRetrieveSpiPort;
import com.fich.sarh.plantofpositions.application.ports.persistence.PlantOfPositionSaveSpiPort;
import com.fich.sarh.plantofpositions.domain.model.PlantOfPosition;
import com.fich.sarh.plantofpositions.domain.model.PlantOfPositionCommand;
import com.fich.sarh.position.application.ports.persistence.PositionRetrievePort;
import com.fich.sarh.position.domain.model.Position;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;

@UseCase
public class PlantOfPositionSaveUseCase implements PlantOfPositionSaveApiPort {

    Logger logger = LoggerFactory.getLogger(PlantOfPositionSaveUseCase.class);
    private final PlantOfPositionRetrieveSpiPort plantRetrieve;
    private final PlantOfPositionSaveSpiPort savePort;
    private final PositionRetrievePort positionRetrievePort;
    private final AgentRetrievePort agentRetrievePort;
    private final MovementSavePort movementSavePort;

    private final OrganizationalSubUnitRetrieveSpiPort subUnitRetrievePort;
    private final PlantHistorySavePort plantHistorySavePort;

    private final MovementMapper mapper;

    public PlantOfPositionSaveUseCase(PlantOfPositionRetrieveSpiPort plantRetrieve, PlantOfPositionSaveSpiPort savePort,
                                      PositionRetrievePort positionRetrievePort,
                                      AgentRetrievePort agentRetrievePort,
                                      MovementSavePort movementSavePort,
                                      OrganizationalSubUnitRetrieveSpiPort subUnitRetrievePort, PlantHistorySavePort plantHistorySavePort,
                                      MovementMapper mapper) {
        this.plantRetrieve = plantRetrieve;
        this.savePort = savePort;
        this.positionRetrievePort = positionRetrievePort;
        this.agentRetrievePort = agentRetrievePort;
        this.movementSavePort = movementSavePort;
        this.subUnitRetrievePort = subUnitRetrievePort;
        this.plantHistorySavePort = plantHistorySavePort;
        this.mapper = mapper;
    }



/*    @Override
    public PlantOfPosition savePlantOfPosition(PlantOfPositionCommand plantpositionCommand) {

        if(plantRetrieve.existsByPositionAndAgent(plantpositionCommand.getPositionId(), plantpositionCommand.getAgentId())){
            throw  new BusinessRuleViolationException("Ya existe la sociación cargo agente");
        }

        Optional<Position> position = positionRetrievePort.findPositionById(plantpositionCommand.getPositionId());

        if(!position.isPresent()) {
            throw new RuntimeException("No existe el cargo seleccionado");
        }

        PlantOfPosition plant = registerPlantPosition(plantpositionCommand, position.get());

        Movement movement = registerMovement(plantpositionCommand, plant,
                                             position.get());
        PlantHistory plantHistory = registerPlantHistory(plantpositionCommand, plant);


        return  plant;
    }*/

    @Override
    public PlantOfPosition savePlantOfPosition(PlantOfPositionCommand plantpositionCommand) {

        boolean active_agent_position = plantRetrieve.existsByPositionAndAgent(plantpositionCommand.getPositionId(),
                plantpositionCommand.getAgentId());

        if (active_agent_position) {
            throw new BusinessRuleViolationException("Ya hay un agente activo en el cargo");
        }

        Position position = positionRetrievePort.findPositionById(plantpositionCommand.getPositionId())
                .orElseThrow(() -> new BusinessRuleViolationException("No existe el cargo seleccionado"));

        Agent agent = agentRetrievePort.findById(plantpositionCommand.getAgentId()).orElseThrow(() ->
                new BusinessRuleViolationException("No existe el agente"));

        OrganizationalSubUnit subUnit = subUnitRetrievePort
                .findById(plantpositionCommand.getOrganizationalSubUnit())
                .orElseThrow(()-> new BusinessRuleViolationException("No existe la materia"));

        PlantOfPosition plantposition = registerPlantPosition(agent, position, subUnit,
                plantpositionCommand.getCurrentStatusID(),
                plantpositionCommand.getCharacterplantID());

        Movement movement = registerMovement(plantposition, position,
                plantpositionCommand.getDateFrom(),
                plantpositionCommand.getReasonForMovement());

        PlantHistory plantHistory = registerPlantHistory(plantposition, position,
                                  plantpositionCommand.getDateFrom(), plantpositionCommand.getCurrentStatusID());

        return plantposition;
    }

    private PlantOfPosition registerPlantPosition(Agent agent, Position position,OrganizationalSubUnit subUnit,
                                                  PlantStatus currentStatus, CharacterPlant characterPlant) {
        PlantOfPosition plant = PlantOfPosition
                .builder().agent(agent)
                .position(position)
                .organizationalSubUnit(subUnit)
                .currentStatusID(currentStatus)
                .characterplantID(characterPlant).build();

        return savePort.savePlantOfPosition(plant);
    }

   /* private PlantOfPosition registerPlantPosition(PlantOfPositionCommand request, Position position) {

        Optional<Agent> agent = agentRetrievePort.findById(request.getAgentId());

        if (!agent.isPresent()) {
            throw new RuntimeException("No existe el agente");
        }

        PlantOfPosition plant = PlantOfPosition.builder()
                .agent(agent.get())
                .position(position)
                .currentStatusID(request.getCurrentStatusID())
                .characterplantID(request.getCharacterplantID()).build();

        return savePort.savePlantOfPosition(plant);

    }*/


    private PlantHistory registerPlantHistory(PlantOfPosition plant, Position position,
                                              LocalDate dateFrom, PlantStatus currentStatus) {
        PlantHistory plantHistory = PlantHistory.builder()
                .plantOfPosition(plant)
                .plantStatus(currentStatus)
                .dateFrom(dateFrom).build();
        return plantHistorySavePort.savePlantHistory(plantHistory);

    }

    private Movement registerMovement(PlantOfPosition plant, Position position, LocalDate dateFrom, String reason) {

        Movement movement = Movement.builder().positionId(position.getId())
                .plantId(plant.getId())
                .movementDate(dateFrom)
                .reasonForMovement(reason).build();
        return movementSavePort.saveMovement(movement);

    }

   /* private Movement registerMovement(PlantOfPositionCommand request,
                                      PlantOfPosition plantPosition, Position position) {
        Movement movement = Movement.builder()
                .positionId(position.getId())
                .plantId(plantPosition.getId())
                .movementDate(request.getDateFrom())
                .reasonForMovement(request.getReasonForMovement()).build();

        return movementSavePort.saveMovement(movement);
    }*/

   /* private PlantHistory registerPlantHistory(PlantOfPositionCommand request, PlantOfPosition plant) {
        PlantHistory plantHistory = PlantHistory.builder()
                .plantOfPosition(plant)
                .plantStatus(request.getCurrentStatusID())
                .dateFrom(request.getDateFrom()).build();
        return plantHistorySavePort.savePlantHistory(plantHistory);
    }*/


}


  /*  Movement movement = Movement.builder()

                .positionId(position.get().getId())
                .plantId(saveplantPosition.getId())
                .movementDate(plantpositionCommand.getDateFrom())
                .reasonForMovement(plantpositionCommand.getReasonForMovement())
                .build();


        movement = movementSavePort.saveMovement(movement);*/

       /* PlantHistory plantHistory = PlantHistory.builder()
                .plantOfPosition(saveplantPosition)
                .plantStatus(plantpositionCommand.getCurrentStatusID())
                .dateFrom(plantpositionCommand.getDateFrom())
                .build();

        plantHistorySavePort.savePlantHistory(plantHistory);
*/

       /*  Optional<Agent> agent = agentRetrievePort.findById(plantpositionCommand.getAgentId());

        if(!position.isPresent()) {
            throw new RuntimeException("No existe el cargo seleccionado");
        }
        if(!agent.isPresent()) {
            throw new RuntimeException("No existe el agente");
        }

        PlantOfPosition plant = PlantOfPosition.builder()
                .agentID(agent.get())
                .positionID(position.get())
                .currentStatusID(plantpositionCommand.getCurrentStatusID())
                .characterplantID(plantpositionCommand.getCharacterplantID()).build();

        PlantOfPosition saveplantPosition =savePort.savePlantOfPosition(plant);*/