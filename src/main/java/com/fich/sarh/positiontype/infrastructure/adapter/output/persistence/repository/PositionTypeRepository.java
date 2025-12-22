package com.fich.sarh.positiontype.infrastructure.adapter.output.persistence.repository;

import com.fich.sarh.positiontype.infrastructure.adapter.output.persistence.entity.PositionTypeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PositionTypeRepository extends JpaRepository<PositionTypeEntity, Long> {
}
