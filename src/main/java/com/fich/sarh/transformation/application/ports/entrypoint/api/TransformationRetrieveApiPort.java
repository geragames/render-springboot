package com.fich.sarh.transformation.application.ports.entrypoint.api;

import com.fich.sarh.transformation.domain.model.Transformation;

import java.util.List;
import java.util.Optional;

public interface TransformationRetrieveApiPort {

    List<Transformation> getAllTransformations();

    Optional<Transformation> findById(Long id);

    Optional<Transformation> findByResolutionNumber(String resolutionNumber);

    Optional<Transformation> findFirstByOrderDesc();
}
