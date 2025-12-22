package com.fich.sarh.organizationalunit.application.services;

import com.fich.sarh.common.UseCase;
import com.fich.sarh.organizationalunit.application.ports.entrypoint.api.OrganizationalUnitRetrieveApiPort;
import com.fich.sarh.organizationalunit.application.ports.persistence.OrganizationalUnitRetrieveSpiPort;
import com.fich.sarh.organizationalunit.domain.model.OrganizationalDTO;
import com.fich.sarh.organizationalunit.domain.model.OrganizationalUnit;

import java.util.List;
import java.util.Optional;

@UseCase
public class OrganizationalUnitRetrieveUseCase implements OrganizationalUnitRetrieveApiPort {

    private final OrganizationalUnitRetrieveSpiPort organizationalUnitRetrieveSpiPort;

    public OrganizationalUnitRetrieveUseCase(OrganizationalUnitRetrieveSpiPort organizationalUnitRetrieveSpiPort) {
        this.organizationalUnitRetrieveSpiPort = organizationalUnitRetrieveSpiPort;
    }

    @Override
    public List<OrganizationalUnit> getAllOrganizationalUnits() {
        return organizationalUnitRetrieveSpiPort.findAllOrganizationalUnit();
    }

    @Override
    public Optional<OrganizationalUnit> findById(Long id) {
        return organizationalUnitRetrieveSpiPort.findById(id);
    }

    @Override
    public List<OrganizationalDTO> findAllOrganizationDto() {
        return organizationalUnitRetrieveSpiPort.findAllOrganizationalDto();
    }

    @Override
    public OrganizationalUnit findByNameUnit(String unit) {
        return null;
    }
}
