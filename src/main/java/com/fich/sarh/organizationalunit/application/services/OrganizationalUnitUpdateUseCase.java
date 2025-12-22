package com.fich.sarh.organizationalunit.application.services;

import com.fich.sarh.common.UseCase;
import com.fich.sarh.common.exceptions.BusinessRuleViolationException;
import com.fich.sarh.organizationalunit.application.ports.entrypoint.api.OrganizationalUnitUpdateApiPort;
import com.fich.sarh.organizationalunit.application.ports.persistence.OrganizationalUnitRetrieveSpiPort;
import com.fich.sarh.organizationalunit.application.ports.persistence.OrganizationalUnitSaveSpiPort;
import com.fich.sarh.organizationalunit.domain.model.OrganizationalUnit;

@UseCase
public class OrganizationalUnitUpdateUseCase implements OrganizationalUnitUpdateApiPort {

    private final OrganizationalUnitSaveSpiPort organizationalUnitSaveSpiPort;

    private final OrganizationalUnitRetrieveSpiPort organizationalUnitRetrieveSpiPort;

    public OrganizationalUnitUpdateUseCase(OrganizationalUnitSaveSpiPort organizationalUnitSaveSpiPort, OrganizationalUnitRetrieveSpiPort organizationalUnitRetrieveSpiPort) {
        this.organizationalUnitSaveSpiPort = organizationalUnitSaveSpiPort;
        this.organizationalUnitRetrieveSpiPort = organizationalUnitRetrieveSpiPort;
    }

    @Override
    public OrganizationalUnit updateOrganizationUnit(Long id, OrganizationalUnit command) {

        OrganizationalUnit organizationalUnit = organizationalUnitRetrieveSpiPort.findById(id)
                .orElseThrow(()-> new BusinessRuleViolationException("El departamento solicitado no se encuentra disponible"));
        organizationalUnit.setNameUnit(command.getNameUnit());
        organizationalUnit.setDirector(command.getDirector());
        if(command.getViceDirector() != null) {
            organizationalUnit.setViceDirector(command.getViceDirector());
        }


        organizationalUnit.validateDirectors();

        return organizationalUnitSaveSpiPort.saveOrganizationalUnit(organizationalUnit);


    }
}
