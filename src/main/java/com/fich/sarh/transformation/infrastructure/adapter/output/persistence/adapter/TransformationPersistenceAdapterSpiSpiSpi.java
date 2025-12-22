package com.fich.sarh.transformation.infrastructure.adapter.output.persistence.adapter;

import com.fich.sarh.common.PersistenceAdapter;
import com.fich.sarh.transformation.application.ports.persistence.TransformationLoadSpiPort;
import com.fich.sarh.transformation.application.ports.persistence.TransformationRetrieveSpiPort;
import com.fich.sarh.transformation.application.ports.persistence.TransformationSaveSpiPort;
import com.fich.sarh.transformation.domain.model.Transformation;
import com.fich.sarh.transformation.infrastructure.adapter.output.persistence.mapper.TransformationMapper;
import com.fich.sarh.transformation.infrastructure.adapter.output.persistence.repository.TransformationRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@PersistenceAdapter
public class TransformationPersistenceAdapterSpiSpiSpi implements
        TransformationRetrieveSpiPort, TransformationSaveSpiPort, TransformationLoadSpiPort {

    private final TransformationRepository transformationRepository;

    public TransformationPersistenceAdapterSpiSpiSpi(TransformationRepository transformationRepository) {
        this.transformationRepository = transformationRepository;
    }


    @Override
    public Optional<Transformation> loadTransformation(Long id) {
        return transformationRepository.findById(id).map(
                TransformationMapper.INSTANCE::toDto);
    }

    @Override
    public List<Transformation> findAll() {
        return transformationRepository.findAll().stream()
                .map( TransformationMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<Transformation> findById(Long id) {
        return transformationRepository.findById(id).map(
                TransformationMapper.INSTANCE::toDto
        );
    }

    @Override
    public Optional<Transformation>  findByResolutionNumber(String resolution) {
        return  transformationRepository.findByResolutionNumber(resolution).map(
                TransformationMapper.INSTANCE::toDto
        );
    }

    @Override
    public Optional<Transformation> findFirstByOrderDesc() {
        return transformationRepository.findFirstByOrderByIdDesc().map(TransformationMapper.INSTANCE::toDto);
    }

    @Override
    public Transformation saveTransformation(Transformation transformation) {
        return TransformationMapper.INSTANCE.toDto(transformationRepository.save(
                TransformationMapper.INSTANCE.toEntity(transformation)
        ));

    }
}
