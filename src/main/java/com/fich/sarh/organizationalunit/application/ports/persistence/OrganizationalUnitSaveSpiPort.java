package com.fich.sarh.organizationalunit.application.ports.persistence;

import com.fich.sarh.organizationalunit.domain.model.OrganizationalUnit;

public interface OrganizationalUnitSaveSpiPort {

    OrganizationalUnit saveOrganizationalUnit(OrganizationalUnit unit);
}
