package com.fich.sarh.positiontype.application.ports.entrypoint.api;

import com.fich.sarh.positiontype.domain.model.PositionType;

import java.util.List;
import java.util.Optional;

public interface PositionTypeRetrieveServicePort {

    List<PositionType> getAllPositionType();

    Optional<PositionType> findById(Long id);


}
