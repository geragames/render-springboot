package com.fich.sarh.positiontype.infrastructure.adapter.output.persistence.adapter;

import com.fich.sarh.common.PersistenceAdapter;
import com.fich.sarh.positiontype.application.ports.persistence.PositionTypeLoadPort;
import com.fich.sarh.positiontype.application.ports.persistence.PositionTypeRetrievePort;
import com.fich.sarh.positiontype.application.ports.persistence.PositionTypeSavePort;
import com.fich.sarh.positiontype.domain.model.PositionType;
import com.fich.sarh.positiontype.infrastructure.adapter.output.persistence.mapper.PositionTypeMapper;
import com.fich.sarh.positiontype.infrastructure.adapter.output.persistence.repository.PositionTypeRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@PersistenceAdapter
public class PositionTypePersistenceAdapter implements
        PositionTypeLoadPort, PositionTypeSavePort, PositionTypeRetrievePort {

    private final PositionTypeRepository positionTypeRepository;

    public PositionTypePersistenceAdapter(PositionTypeRepository positionTypeRepository) {
        this.positionTypeRepository = positionTypeRepository;
    }

    @Override
    public Optional<PositionType> loadPositionType(Long id) {
        return  Optional.of(positionTypeRepository.findById(id)).map(position -> PositionTypeMapper.INSTANCE.toPositionType(position.get()));
    }

    @Override
    public List<PositionType> findAll() {
        return positionTypeRepository.findAll().stream().map(
                PositionTypeMapper.INSTANCE::toPositionType
        ).collect(Collectors.toList());
    }

    @Override
    public Optional<PositionType> findById(Long id) {
        return
                positionTypeRepository.findById(id)
                        .map( PositionTypeMapper.INSTANCE::toPositionType );
    }

    @Override
    public PositionType savePositionType(PositionType position) {
        return PositionTypeMapper.INSTANCE.toPositionType(positionTypeRepository.save(PositionTypeMapper.INSTANCE
                .toPositionTypeEntity(position)));
    }



}
