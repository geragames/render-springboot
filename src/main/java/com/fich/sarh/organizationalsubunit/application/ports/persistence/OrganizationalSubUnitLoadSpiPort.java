package com.fich.sarh.organizationalsubunit.application.ports.persistence;

import com.fich.sarh.organizationalsubunit.domain.model.OrganizationalSubUnit;

import java.util.Optional;

public interface OrganizationalSubUnitLoadSpiPort {

    Optional<OrganizationalSubUnit> loadOrganizationalSubUnit(Long id);

}
