package com.fich.sarh.planthistory.infrastructure.adapter.output.persistence.repository;

import com.fich.sarh.planthistory.domain.model.PlantHistory;
import com.fich.sarh.planthistory.infrastructure.adapter.output.persistence.entity.PlantHistoryEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

public interface PlantHistoryRepository extends JpaRepository<PlantHistoryEntity, Long> {

    @Query(value = "SELECT ph FROM PlantHistoryEntity ph LEFT JOIN ph.plantOfPosition pl WHERE pl.id = ?1 AND ph.dateTo IS NULL")
    Optional<PlantHistoryEntity> findPlantByIdActive(Long plantId);

    @Query(value = "SELECT ph FROM PlantHistoryEntity ph JOIN ph.plantOfPosition pl WHERE pl.id = ?1")
    List<PlantHistoryEntity> findPlantHistoryByPlantId(Long plantId);


    PlantHistoryEntity findFirstByPlantOfPosition_IdOrderByIdDesc(Long plantId);
/*    @Query("""
    SELECT ph 
    FROM PlantHistoryEntity ph 
    JOIN FETCH ph.plantOfPosition pl 
    WHERE pl.id = :plantId 
    ORDER BY ph.id DESC
""")
    PlantHistoryEntity findTopByPlantIdOrderByIdDesc(@Param("plantId") Long plantId);*/

/*    @Query(value = """
            SELECT ph FROM PlantHistoryEntity ph JOIN FETCH ph.plantOfPosition pl WHERE pl.id = ?1 ORDER BY  ph.id DESC
            """)
    List<PlantHistoryEntity> findTopByPlantIdOrderHistoryIdDesc(Long plantId);*/
    // boolean existsByHistoryCurrent(Long historyCurrent);
}
