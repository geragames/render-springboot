package com.fich.sarh.plantofpositions.infrastructure.adapter.output.persistence.repository;

import com.fich.sarh.agent.infrastructure.adapter.output.persistence.entity.AgentEntity;
import com.fich.sarh.common.PlantStatus;
import com.fich.sarh.organizationalsubunit.infrastructure.adapter.output.persistence.entity.OrganizationalSubUnitEntity;
import com.fich.sarh.organizationalunit.infrastructure.adapter.output.persistence.entity.OrganizationalUnitEntity;
import com.fich.sarh.plantofpositions.domain.model.PlantFilter;
import com.fich.sarh.plantofpositions.domain.model.PlantProjectionDTO;
import com.fich.sarh.plantofpositions.infrastructure.adapter.output.persistence.entity.PlantOfPositionEntity;
import com.fich.sarh.point.infrastructure.adapter.output.persistence.entity.PointEntity;
import com.fich.sarh.position.infrastructure.adapter.output.persistence.entity.PositionEntity;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.criteria.*;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PlantOfPositionCustomRepository {

    @PersistenceContext
    private EntityManager em;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    public List<PlantProjectionDTO> findAllProjection(PlantFilter criteria) {

        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery<PlantProjectionDTO> cq = cb.createQuery(PlantProjectionDTO.class);
        Root<PlantOfPositionEntity> root = cq.from(PlantOfPositionEntity.class);

        Join<PlantOfPositionEntity, OrganizationalSubUnitEntity> subUnitJoin = root.join("organizationalSubUnit", JoinType.LEFT);
        Join<PlantOfPositionEntity, AgentEntity> agentJoin = root.join("agent", JoinType.LEFT);
        Join<PlantOfPositionEntity, PositionEntity> positionJoin = root.join("position", JoinType.LEFT);
        Join<PositionEntity, PointEntity> pointJoin = positionJoin.join("pointID", JoinType.LEFT);
        Join<PositionEntity, OrganizationalUnitEntity> unitJoin = positionJoin.join("organizationalUnitID", JoinType.LEFT);

        // Selección


        List<Predicate> predicates = new ArrayList<>();

        if (criteria != null) {
            if (!StringUtils.isBlank(criteria.getSubject())) {
                logger.info("VALOR DE LA MATERIA " + criteria.getSubject());
                predicates.add(cb.like(
                        cb.upper(subUnitJoin.get("nameSubUnit")),
                        "%" + criteria.getSubject().toUpperCase() + "%"
                ));
            }
            if (!StringUtils.isBlank(criteria.getTeacher())) {
                logger.info("VALOR DE APELLIDO " + criteria.getTeacher());
                predicates.add(cb.like(
                        cb.upper(agentJoin.get("lastname")),
                        "%" + criteria.getTeacher().toUpperCase() + "%"
                ));
            }
            if (!StringUtils.isBlank(criteria.getDepartment())) {
                logger.info("VALOR DE LA DEPARTAMENTO " + criteria.getDepartment());
                predicates.add(cb.like(
                        cb.upper(unitJoin.get("nameUnit")),
                        "%" + criteria.getDepartment().toUpperCase() + "%"
                ));
            }
            if (!StringUtils.isBlank(criteria.getNamePosition())) {
                predicates.add(cb.like(
                        cb.upper(pointJoin.get("namePosition")),
                        "%" + criteria.getNamePosition().toUpperCase() + "%"
                ));
            }
        }

        if (!predicates.isEmpty()) {
            cq.where(cb.and(predicates.toArray(new Predicate[0])));
        }

        cq.select(cb.construct(
                PlantProjectionDTO.class,
                root.get("id").as(String.class),
                root.get("currentStatusID").as(String.class),
                root.get("characterplantID").as(String.class),
                subUnitJoin.get("nameSubUnit"),
                agentJoin.get("firstname"),
                agentJoin.get("lastname"),
                agentJoin.get("document"),
                pointJoin.get("namePosition")
        ));

        return em.createQuery(cq).getResultList();
    }

}
