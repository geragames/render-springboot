package com.fich.sarh.transformation.infrastructure.adapter.output.persistence.repository;

import com.fich.sarh.transformation.infrastructure.adapter.output.persistence.entity.TransformationEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TransformationRepository extends JpaRepository<TransformationEntity, Long> {

    Optional<TransformationEntity>  findByResolutionNumber(String resolution);

    Optional<TransformationEntity> findFirstByOrderByIdDesc();

}
