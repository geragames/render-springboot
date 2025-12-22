package com.fich.sarh.movement.infrastructure.adapter.output.persistence.adapter;

import com.fich.sarh.common.PersistenceAdapter;
import com.fich.sarh.movement.application.ports.persistence.MovementLoadPort;
import com.fich.sarh.movement.application.ports.persistence.MovementRetrievePort;
import com.fich.sarh.movement.application.ports.persistence.MovementSavePort;
import com.fich.sarh.movement.domain.model.Movement;
import com.fich.sarh.movement.infrastructure.adapter.output.persistence.entity.MovementEntity;
import com.fich.sarh.movement.infrastructure.adapter.output.persistence.mapper.MovementMapper;
import com.fich.sarh.movement.infrastructure.adapter.output.persistence.repository.MovementRepository;
import com.fich.sarh.plantofpositions.application.ports.persistence.PlantOfPositionRetrieveSpiPort;
import com.fich.sarh.plantofpositions.domain.model.PlantOfPosition;
import com.fich.sarh.plantofpositions.infrastructure.adapter.output.persistence.entity.PlantOfPositionEntity;
import com.fich.sarh.plantofpositions.infrastructure.adapter.output.persistence.mapper.PlantOfPositionMapper;
import com.fich.sarh.position.application.ports.persistence.PositionRetrievePort;
import com.fich.sarh.position.domain.model.Position;
import com.fich.sarh.position.infrastructure.adapter.output.persistence.entity.PositionEntity;
import com.fich.sarh.position.infrastructure.adapter.output.persistence.mapper.PositionMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Optional;

@PersistenceAdapter
public class MovementPersistenceAdapter implements MovementLoadPort, MovementRetrievePort,
        MovementSavePort {

    Logger logger = LoggerFactory.getLogger(MovementPersistenceAdapter.class);
    private final MovementRepository movementRepository;
    private final PlantOfPositionRetrieveSpiPort plantRetrieve;
    private final PositionRetrievePort positionRetrieve;

    private final MovementMapper mapper;

    public MovementPersistenceAdapter(MovementRepository movementRepository, PlantOfPositionRetrieveSpiPort plantRetrieve, PositionRetrievePort positionRetrieve, MovementMapper mapper) {
        this.movementRepository = movementRepository;
        this.plantRetrieve = plantRetrieve;
        this.positionRetrieve = positionRetrieve;
        this.mapper = mapper;
    }

    @Override
    public Optional<Movement> loadMovement(Long id) {

        return Optional.of(
                mapper.toMovement(movementRepository.findById(id).get())
        );
    }

    @Override
    public List<Movement> findAllMovements() {
        return  null;
    }

    @Override
    public Optional<Movement> findById(Long id) {

        return Optional.of(
                mapper.toMovement(movementRepository.findById(id).get())
        );
    }

    @Override
    public Movement findByPlant(PlantOfPosition plant) {
        return mapper.toMovement(movementRepository.fetchMovementByPlant(
                PlantOfPositionMapper.INSTANCE.toPlantOfPositionEntity(plant)));
    }

/*    @Override
    public List<Movement> fetchMovementWithInactiveAgent(List<PlantStatus> status) {
        return  null; //mapper.toMovementList(movementRepository.fetchMovementWithInactiveAgent(status));
    }*/

    @Override
    public Movement saveMovement(Movement movement) {

        MovementEntity entity = mapper.toMovementEntity(movement);
        Position position = positionRetrieve.findPositionById(movement.getPositionId()).orElseThrow(()-> new RuntimeException("No existe el cargo"));
        PlantOfPosition plant = plantRetrieve.findById(movement.getPlantId()).orElseThrow(()-> new RuntimeException("No existe la planta de cargo"));

        PositionEntity positionEntity = PositionMapper.INSTANCE.toEntity(position);
        PlantOfPositionEntity plantEntity = PlantOfPositionMapper.INSTANCE.toPlantOfPositionEntity(plant);
        entity.setPlant(plantEntity);
        entity.setPosition(positionEntity);

        logger.info("MOVIMIENTO PERSISTENCIA " + entity);

        return
             mapper.toMovement(movementRepository.save(entity));


    }
}
