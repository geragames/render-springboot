package com.fich.sarh.organizationalsubunit.application.ports.entrypoint.api;

import com.fich.sarh.organizationalsubunit.domain.model.OrganizationalSubUnit;
import com.fich.sarh.organizationalsubunit.domain.model.OrganizationalSubUnitDTO;

import java.util.List;
import java.util.Optional;

public interface OrganizationalSubUnitRetrieveApiPort {

    List<OrganizationalSubUnitDTO> getAllOrganizationalSubUnitDTOs();
    List<OrganizationalSubUnit> getAllOrganizationalSubUnits();

    Optional<OrganizationalSubUnit> findById(Long id);

    OrganizationalSubUnit findByNameSubUnit(String unit);
}
