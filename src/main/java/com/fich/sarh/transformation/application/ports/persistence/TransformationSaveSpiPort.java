package com.fich.sarh.transformation.application.ports.persistence;

import com.fich.sarh.transformation.domain.model.Transformation;

public interface TransformationSaveSpiPort {

    Transformation saveTransformation(Transformation transformation);
}
