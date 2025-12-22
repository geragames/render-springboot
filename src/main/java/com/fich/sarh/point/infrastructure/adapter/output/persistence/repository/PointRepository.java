package com.fich.sarh.point.infrastructure.adapter.output.persistence.repository;

import com.fich.sarh.point.infrastructure.adapter.output.persistence.entity.PointEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PointRepository extends JpaRepository<PointEntity, Long> {
}
