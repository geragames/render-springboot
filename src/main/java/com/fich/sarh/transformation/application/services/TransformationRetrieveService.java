package com.fich.sarh.transformation.application.services;

import com.fich.sarh.common.UseCase;
import com.fich.sarh.transformation.application.ports.entrypoint.api.TransformationRetrieveApiPort;
import com.fich.sarh.transformation.application.ports.persistence.TransformationRetrieveSpiPort;
import com.fich.sarh.transformation.domain.model.Transformation;

import java.util.List;
import java.util.Optional;

@UseCase
public class TransformationRetrieveService implements TransformationRetrieveApiPort {

    private final TransformationRetrieveSpiPort transformationRetrievePort;

    public TransformationRetrieveService(TransformationRetrieveSpiPort transformationRetrievePort) {
        this.transformationRetrievePort = transformationRetrievePort;
    }

    @Override
    public List<Transformation> getAllTransformations() {
        return transformationRetrievePort.findAll();
    }

    @Override
    public Optional<Transformation> findById(Long id) {
        return  transformationRetrievePort.findById(id);
    }

    @Override
    public Optional<Transformation> findByResolutionNumber(String resolutionNumber) {
        return transformationRetrievePort.findByResolutionNumber(resolutionNumber);
    }

    @Override
    public Optional<Transformation> findFirstByOrderDesc() {
        return transformationRetrievePort.findFirstByOrderDesc();
    }
}
