package com.fich.sarh.plantofpositions.infrastructure.adapter.output.persistence.repository;

import com.fich.sarh.agent.infrastructure.adapter.output.persistence.entity.AgentEntity;
import com.fich.sarh.organizationalunit.infrastructure.adapter.output.persistence.entity.OrganizationalUnitEntity;
import com.fich.sarh.plantofpositions.domain.model.PlantFilter;
import com.fich.sarh.plantofpositions.infrastructure.adapter.output.persistence.entity.PlantOfPositionEntity;
import com.fich.sarh.point.infrastructure.adapter.output.persistence.entity.PointEntity;
import com.fich.sarh.position.infrastructure.adapter.output.persistence.entity.PositionEntity;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.jpa.domain.Specification;

public class PlantSpecifications {

    public static Specification<PlantOfPositionEntity> hasLastNameLike(String lastname) {
        return ((root, query, cb) -> {
            Join<PlantOfPositionEntity, AgentEntity> agentJoin = root.join("agent", JoinType.LEFT);
            return cb.like(cb.upper(agentJoin.get("lastname")), "%" + lastname.toUpperCase() + "%");
        });

    }



    public static Specification<PlantOfPositionEntity> hasNamePosition(String nameposition) {
        return (root, query, cb) -> {
            Join<PlantOfPositionEntity,PositionEntity> positionJoin = root.join("PositionID", JoinType.LEFT);
            Join<PositionEntity,PointEntity> pointJoin = positionJoin.join("pointID", JoinType.LEFT);
            return cb.like(cb.upper(pointJoin.get("namePosition")) , "%" + nameposition.toUpperCase() + "%");
        };
    }

    public static Specification<PlantOfPositionEntity> hasSubject(String subject) {
        return (root, query, cb) -> {
            Join<PlantOfPositionEntity,PositionEntity> positionJoin = root.join("organizationalSubUnitID", JoinType.LEFT);

            return cb.like(cb.upper(positionJoin.get("nameSubUnit")) , "%" + subject.toUpperCase() + "%");
        };
    }

    public static Specification<PlantOfPositionEntity> hasDepartment(String department) {
        return (root, query, cb) -> {
            Join< PlantOfPositionEntity,PositionEntity> positionJoin = root.join("positionID", JoinType.LEFT);
            Join<PositionEntity,OrganizationalUnitEntity> unitJoin = positionJoin.join("organizationalUnitID", JoinType.LEFT);
            return cb.like(cb.upper(unitJoin.get("nameUnit")) , "%" + department.toUpperCase() + "%");
        };
    }

    public static Specification<PlantOfPositionEntity> createSpecification(PlantFilter criteria) {
        Specification<PlantOfPositionEntity> specification = Specification.where(null);

        if (criteria != null) {
            if (!StringUtils.isBlank(criteria.getSubject())) {
                specification = specification.and(hasSubject(criteria.getSubject()));
            }
            if (!StringUtils.isBlank(criteria.getTeacher())) {
                specification = specification.and(hasLastNameLike(criteria.getTeacher()));
            }
            if (!StringUtils.isBlank(criteria.getDepartment())) {
                specification = specification.and(hasDepartment(criteria.getDepartment()));
            }
            if (!StringUtils.isBlank(criteria.getNamePosition())) {
                specification = specification.and(hasNamePosition(criteria.getNamePosition()));
            }
        }
        return specification;
    }


}
