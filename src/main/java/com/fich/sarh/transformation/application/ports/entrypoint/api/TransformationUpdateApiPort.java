package com.fich.sarh.transformation.application.ports.entrypoint.api;

import com.fich.sarh.transformation.domain.model.Transformation;

public interface TransformationUpdateApiPort {

    Transformation updateTransformation(Long id, Transformation transformation);
}
