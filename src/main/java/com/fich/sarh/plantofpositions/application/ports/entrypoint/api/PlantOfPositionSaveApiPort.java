package com.fich.sarh.plantofpositions.application.ports.entrypoint.api;

import com.fich.sarh.plantofpositions.domain.model.PlantOfPosition;
import com.fich.sarh.plantofpositions.domain.model.PlantOfPositionCommand;
import com.fich.sarh.plantofpositions.infrastructure.adapter.input.rest.model.request.PlantOfPositionRequest;

public interface PlantOfPositionSaveApiPort {

    PlantOfPosition savePlantOfPosition(PlantOfPositionCommand plantpositionCommand);
}
