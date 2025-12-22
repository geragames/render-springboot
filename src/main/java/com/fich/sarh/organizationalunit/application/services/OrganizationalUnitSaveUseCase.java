package com.fich.sarh.organizationalunit.application.services;

import com.fich.sarh.common.UseCase;
import com.fich.sarh.organizationalunit.application.ports.entrypoint.api.OrganizationalUnitSaveApiPort;
import com.fich.sarh.organizationalunit.application.ports.persistence.OrganizationalUnitSaveSpiPort;
import com.fich.sarh.organizationalunit.domain.model.OrganizationalUnit;

@UseCase
public class OrganizationalUnitSaveUseCase implements OrganizationalUnitSaveApiPort {

    private final OrganizationalUnitSaveSpiPort organizationalUnitSaveSpiPort;

    public OrganizationalUnitSaveUseCase(OrganizationalUnitSaveSpiPort organizationalUnitSaveSpiPort) {
        this.organizationalUnitSaveSpiPort = organizationalUnitSaveSpiPort;
    }

    @Override
    public OrganizationalUnit saveOrganizationUnit(OrganizationalUnit organizational) {
        organizational.validateDirectors();
        return organizationalUnitSaveSpiPort.saveOrganizationalUnit(organizational);
    }
}
