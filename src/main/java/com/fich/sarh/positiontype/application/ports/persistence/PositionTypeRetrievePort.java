package com.fich.sarh.positiontype.application.ports.persistence;

import com.fich.sarh.positiontype.domain.model.PositionType;

import java.util.List;
import java.util.Optional;

public interface PositionTypeRetrievePort {

    List<PositionType> findAll();

    Optional<PositionType> findById(Long id);

}
