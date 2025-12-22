package com.fich.sarh.organizationalsubunit.application.ports.persistence;

import com.fich.sarh.organizationalsubunit.domain.model.OrganizationalSubUnit;

public interface OrganizationalSubUnitSaveSpiPort {

    OrganizationalSubUnit saveOrganizationalSubUnit(OrganizationalSubUnit organizationalSubUnit);
}
