package com.fich.sarh.organizationalsubunit.infrastructure.adapter.output.persistence.repository;

import com.fich.sarh.organizationalsubunit.domain.model.OrganizationalSubUnitDTO;
import com.fich.sarh.organizationalsubunit.infrastructure.adapter.output.persistence.entity.OrganizationalSubUnitEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrganizationalSubUnitRepository extends JpaRepository<OrganizationalSubUnitEntity, Long> {

    @Query(value = """
            SELECT osu.id AS id, osu.nameSubUnit AS nameSubUnit,
            osu.guaraniCode AS guaraniCode, ou.nameUnit AS nameUnit
             FROM OrganizationalSubUnitEntity osu LEFT JOIN osu.organizationalUnit ou
            """)
    List<OrganizationalSubUnitDTO> findOrganizationalSubUnitDTO();

}
