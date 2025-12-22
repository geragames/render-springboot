package com.fich.sarh.transformation.application.services;

import com.fich.sarh.common.UseCase;
import com.fich.sarh.transformation.application.ports.entrypoint.api.TransformationUpdateApiPort;
import com.fich.sarh.transformation.application.ports.persistence.TransformationRetrieveSpiPort;
import com.fich.sarh.transformation.application.ports.persistence.TransformationSaveSpiPort;
import com.fich.sarh.transformation.domain.model.Transformation;

@UseCase
public class TransformationUpdateService implements TransformationUpdateApiPort {

    private final TransformationRetrieveSpiPort transformationRetrievePort;

    private final TransformationSaveSpiPort transformationSavePort;

    public TransformationUpdateService(TransformationRetrieveSpiPort transformationRetrievePort, TransformationSaveSpiPort transformationSavePort) {
        this.transformationRetrievePort = transformationRetrievePort;
        this.transformationSavePort = transformationSavePort;
    }

    @Override
    public Transformation updateTransformation(Long id, Transformation transformation) {
        return  transformationRetrievePort.findById(id).map(transformedSave ->{
            transformedSave.setDate(transformation.getDate());
            transformedSave.setReason(transformation.getReason());
            transformedSave.setResolutionNumber(transformation.getResolutionNumber());
            return transformationSavePort.saveTransformation(transformedSave);
        }).get();
    }
}
