package com.fich.sarh.organizationalunit.application.ports.persistence;

import com.fich.sarh.organizationalunit.domain.model.OrganizationalUnit;

import java.util.Optional;

public interface OrganizationalUnitLoadApiPort {

    Optional<OrganizationalUnit> loadOrganizationalUnit(Long id);

}
