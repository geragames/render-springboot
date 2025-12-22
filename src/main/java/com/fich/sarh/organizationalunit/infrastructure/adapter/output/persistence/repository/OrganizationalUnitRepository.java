package com.fich.sarh.organizationalunit.infrastructure.adapter.output.persistence.repository;

import com.fich.sarh.organizationalunit.domain.model.OrganizationalDTO;
import com.fich.sarh.organizationalunit.infrastructure.adapter.output.persistence.entity.OrganizationalUnitEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface OrganizationalUnitRepository extends JpaRepository<OrganizationalUnitEntity, Long> {

    @Query(value = """
            SELECT ou.id AS id, ou.nameUnit AS nameUnit, di.firstname AS firstname, 
             vi.firstname AS firstnamevice, vi.lastname AS lastnamevice,
             di.lastname AS lastname
             FROM OrganizationalUnitEntity ou LEFT JOIN  ou.director di LEFT JOIN ou.viceDirector vi """)
    List<OrganizationalDTO> findOrganizationalAll();

}
