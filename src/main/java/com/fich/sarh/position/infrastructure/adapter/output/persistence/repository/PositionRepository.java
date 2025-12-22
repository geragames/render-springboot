package com.fich.sarh.position.infrastructure.adapter.output.persistence.repository;

import com.fich.sarh.common.StatusOfPositions;
import com.fich.sarh.position.domain.model.PositionDto;
import com.fich.sarh.position.infrastructure.adapter.output.persistence.entity.PositionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional(readOnly = true)
public interface PositionRepository extends JpaRepository<PositionEntity, Long> {

    @Query("""
             SELECT p.id AS id, po.namePosition AS namePosition, 
             o.nameUnit AS nameUnit,
             p.pointsAvailable AS pointsAvailable,
             p.positionStatus AS positionStatus FROM PositionEntity p  LEFT JOIN
             p.organizationalUnitID o LEFT JOIN p.pointID po WHERE p.newPosition.id = ?1
            """)
    List<PositionDto> findOriginPosition(Long generatePosition);

    @Query("""
             SELECT COUNT(p) > 0 FROM PositionEntity p  LEFT JOIN
             p.organizationalUnitID o LEFT JOIN p.pointID po WHERE p.id = ?1 AND 
             p.newPosition.id IS NOT NULL
            """)
    boolean existsByOriginPositionId(Long generatePosition);

    @Query(""" 
             SELECT p.id AS id, po.namePosition AS namePosition, 
             o.nameUnit AS nameUnit,
             p.pointsAvailable AS pointsAvailable,
             po.amountPoint as amountPoint,
             p.positionStatus AS positionStatus FROM PositionEntity p  LEFT JOIN
             p.organizationalUnitID o LEFT JOIN p.pointID po WHERE p.positionStatus IN (1 ,3) AND p.pointsAvailable > 0 
            """)
    List<PositionDto> findVacantPositions();

    @Query(""" 
             SELECT p.id AS id, po.namePosition AS namePosition, 
             o.nameUnit AS nameUnit,
             p.pointsAvailable AS pointsAvailable,
             p.positionStatus AS positionStatus, po.amountPoint AS amountPoint FROM PositionEntity p
             LEFT JOIN
             p.organizationalUnitID o LEFT JOIN p.pointID po
            """)
    List<PositionDto> findAllPosition();

    @Query("SELECT p FROM PositionEntity p WHERE p.positionStatus = ?1")
    List<PositionEntity> findAvailablePosition(StatusOfPositions status);


    @Query("""
               SELECT p.id AS id, po.namePosition AS namePosition, o.nameUnit AS nameUnit,
                p.pointsAvailable AS pointsAvailable, p.positionStatus AS positionStatus, 
                po.amountPoint AS amountPoint
                FROM PositionEntity p
                LEFT JOIN p.pointID po
                LEFT JOIN p.organizationalUnitID o
                LEFT JOIN PlantOfPositionEntity pl ON pl.position = p
                WHERE (pl IS NULL AND (p.pointsAvailable * po.amountPoint / 100) = po.amountPoint) OR (p.positionStatus = 2)
            """)   // AND p.pointsAvailable = po.amountPoint
    List<PositionDto> findFreePosition();

    List<PositionEntity> findAllByIdIn(List<Long> ids);
}
