package com.fich.sarh.position.infrastructure.adapter.output.persistence.adapter;

import com.fich.sarh.common.PersistenceAdapter;
import com.fich.sarh.common.StatusOfPositions;
import com.fich.sarh.common.exceptions.BusinessRuleViolationException;
import com.fich.sarh.position.application.ports.persistence.PositionLoadPort;
import com.fich.sarh.position.application.ports.persistence.PositionRetrievePort;
import com.fich.sarh.position.application.ports.persistence.PositionSavePort;
import com.fich.sarh.position.domain.model.Position;
import com.fich.sarh.position.domain.model.PositionDto;
import com.fich.sarh.position.infrastructure.adapter.output.persistence.entity.PositionEntity;
import com.fich.sarh.position.infrastructure.adapter.output.persistence.mapper.PositionMapper;
import com.fich.sarh.position.infrastructure.adapter.output.persistence.repository.PositionRepository;
import jakarta.transaction.Transactional;

import java.util.List;
import java.util.Optional;

@PersistenceAdapter
public class PositionPersistenceAdapter implements PositionRetrievePort, PositionSavePort,
        PositionLoadPort {

    private final PositionRepository positionRepository;

    private final PositionMapper positionMapper;

    public PositionPersistenceAdapter(PositionRepository positionRepository, PositionMapper positionMapper) {
        this.positionRepository = positionRepository;
        this.positionMapper = positionMapper;
    }

    @Override
    public Optional<Position> loadPosition(Long id) {
        return positionRepository.findById(id).map(
                positionMapper::toDto
        );
    }

    @Override
    public List<PositionDto> findOriginPositions(Long id_generatePosition) {

        return positionRepository.findOriginPosition(id_generatePosition);
    }

    @Override
    public boolean existsOriginPositionId(Long id_generatePosition) {
        return positionRepository.existsByOriginPositionId(id_generatePosition);
    }

    @Override
    public List<PositionDto> findVacantPositions() {
        return positionRepository.findVacantPositions();
    }

    @Override
    public List<PositionDto> findAllPositions() {

        return  positionRepository.findAllPosition() ;
    }

    @Override
    public List<Position> findAllPosition() {
        return positionRepository.findAll().stream().map(positionMapper::toDto).toList();
    }

    @Override
    public List<PositionDto> findFreePositions() {
        return positionRepository.findFreePosition();
    }

    @Override
    public Optional<Position> findPositionById(Long id) {
        Optional<PositionEntity> position = positionRepository.findById(id);
        if(!position.isPresent()){
            throw new BusinessRuleViolationException("No existe el elemento con el ID %s");
        }

        return Optional.of(PositionMapper.INSTANCE.toDto(positionRepository.findById(id).get())) ;

    }

    @Override
    public List<Position> findAllByIdIn(List<Long> ids) {

        return positionMapper.toDtoList(positionRepository.findAllByIdIn(ids));
    }

    @Override
    public List<Position> findAvailablePosition(StatusOfPositions status) {
        return positionMapper.toDtoList(positionRepository.findAvailablePosition(status));
    }

    @Override
    @Transactional
    public Position savePosition(Position position) {


        return positionMapper.toDto(
                positionRepository.save(positionMapper.toEntity(position))
        );
    }
}
