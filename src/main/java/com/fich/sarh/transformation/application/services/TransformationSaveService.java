package com.fich.sarh.transformation.application.services;

import com.fich.sarh.common.UseCase;
import com.fich.sarh.transformation.application.ports.entrypoint.api.TransformationSaveApiPort;
import com.fich.sarh.transformation.application.ports.persistence.TransformationSaveSpiPort;
import com.fich.sarh.transformation.domain.model.Transformation;

@UseCase
public class TransformationSaveService implements TransformationSaveApiPort {

    private final TransformationSaveSpiPort transformationSavePort;

    public TransformationSaveService(TransformationSaveSpiPort transformationSavePort) {
        this.transformationSavePort = transformationSavePort;
    }

    @Override
    public Transformation saveTransformation(Transformation transformation) {

        return transformationSavePort.saveTransformation(transformation);
    }
}
