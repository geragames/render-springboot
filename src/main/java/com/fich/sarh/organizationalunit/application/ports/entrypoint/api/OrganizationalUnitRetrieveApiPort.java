package com.fich.sarh.organizationalunit.application.ports.entrypoint.api;

import com.fich.sarh.organizationalunit.domain.model.OrganizationalDTO;
import com.fich.sarh.organizationalunit.domain.model.OrganizationalUnit;

import java.util.List;
import java.util.Optional;

public interface OrganizationalUnitRetrieveApiPort {

    List<OrganizationalUnit> getAllOrganizationalUnits();

    Optional<OrganizationalUnit> findById(Long id);

    List<OrganizationalDTO> findAllOrganizationDto();

    OrganizationalUnit findByNameUnit(String unit);
}
