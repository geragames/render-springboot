package com.fich.sarh.organizationalunit.application.ports.persistence;

import com.fich.sarh.organizationalunit.domain.model.OrganizationalDTO;
import com.fich.sarh.organizationalunit.domain.model.OrganizationalUnit;

import java.util.List;
import java.util.Optional;

public interface OrganizationalUnitRetrieveSpiPort {

    List<OrganizationalUnit> findAllOrganizationalUnit();

    Optional<OrganizationalUnit> findById(Long id);

    List<OrganizationalDTO> findAllOrganizationalDto();

    OrganizationalUnit findByNameUnit(String unit);
}
