package com.fich.sarh.plantofpositions.infrastructure.adapter.output.persistence.repository;

import com.fich.sarh.common.PlantStatus;
import com.fich.sarh.plantofpositions.domain.model.PlantOfPosition;
import com.fich.sarh.plantofpositions.domain.model.PlantOfPositionDto;
import com.fich.sarh.plantofpositions.domain.model.PlantProjectionDTO;
import com.fich.sarh.plantofpositions.infrastructure.adapter.output.persistence.entity.PlantOfPositionEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlantOfPositionRepository extends
        JpaRepository<PlantOfPositionEntity, Long>, JpaSpecificationExecutor<PlantOfPositionEntity> {

//   @Query(value= "SELECT COUNT(pl.agentID) as countRegister FROM PlantOfPositionEntity pl LEFT JOIN pl.agentID ag LEFT JOIN pl.positionID ps ")

   @Query( value = """ 
           SELECT CASE WHEN COUNT(pl) > 0 THEN true ELSE false END  
           FROM PlantOfPositionEntity pl 
           WHERE pl.agent.id = :agentId AND pl.position.id = :positionId 
           AND pl.currentStatusID IN (:status1, :status2) """)
   boolean existsPlantPositionByAgentAndPosition(
           @Param("agentId") Long agentId,
           @Param("positionId") Long positionId,
           @Param("status1")PlantStatus status1,
           @Param("status2") PlantStatus status2);

   @Query(value = "SELECT pl FROM PlantOfPositionEntity pl LEFT JOIN pl.agent ag " +
           "LEFT JOIN pl.position ps WHERE ag.id = ?1 AND ps.id = ?2 AND pl.currentStatusID = 3")
   List<PlantOfPosition> findPlantPositionByAgentAndPositionWithStatus(Long agentId, Long positionId);

   @Query(value = """
           SELECT pl.id AS id,
                  pl.currentStatusID AS currentStatusID, 
                  pl.characterplantID AS characterplantID,
                  su.nameSubUnit AS nameSubUnit, 
                  ag.lastname AS lastname, 
                  ag.firstname AS firstname,
                  ag.document AS document,
                  po.namePosition AS namePosition
           FROM PlantOfPositionEntity pl LEFT JOIN pl.agent ag
           LEFT JOIN pl.organizationalSubUnit su
           LEFT JOIN pl.position ps LEFT JOIN ps.pointID po 
           
           """)
   List<PlantOfPositionDto> findAllPlantOfPosition();
 //  <T> List<T> findAll(Specification<PlantOfPositionEntity> spec, Class<T> type);

}
  /* @Query(value = """
           SELECT ag AS agent, po AS position, pl  AS plantStatus FROM PlantOfPositionEntity pl LEFT JOIN

           """)
   List<PlantOfPositionDto> findPlantPositionById(Long id);*/

