package com.fich.sarh.transformation.application.ports.persistence;

import com.fich.sarh.transformation.domain.model.Transformation;

import java.util.List;
import java.util.Optional;

public interface TransformationRetrieveSpiPort {

    List<Transformation> findAll();

    Optional<Transformation> findById(Long id);

    Optional<Transformation> findByResolutionNumber(String resolution);

    Optional<Transformation> findFirstByOrderDesc();
}
