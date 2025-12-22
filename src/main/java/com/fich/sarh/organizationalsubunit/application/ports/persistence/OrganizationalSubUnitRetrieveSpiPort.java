package com.fich.sarh.organizationalsubunit.application.ports.persistence;

import com.fich.sarh.organizationalsubunit.domain.model.OrganizationalSubUnit;
import com.fich.sarh.organizationalsubunit.domain.model.OrganizationalSubUnitDTO;

import java.util.List;
import java.util.Optional;

public interface OrganizationalSubUnitRetrieveSpiPort {

    List<OrganizationalSubUnitDTO> findAllOrganizationalSubUnitDTO();
    List<OrganizationalSubUnit> findAllOrganizationalSubUnit();

    Optional<OrganizationalSubUnit> findById(Long id);

    OrganizationalSubUnit findByNameSubUnit(String unit);
}
