package com.fich.sarh.organizationalsubunit.application.services;

import com.fich.sarh.common.UseCase;
import com.fich.sarh.common.exceptions.BusinessRuleViolationException;
import com.fich.sarh.organizationalsubunit.application.ports.entrypoint.api.OrganizationalSubUnitUpdateApiPort;
import com.fich.sarh.organizationalsubunit.application.ports.persistence.OrganizationalSubUnitRetrieveSpiPort;
import com.fich.sarh.organizationalsubunit.application.ports.persistence.OrganizationalSubUnitSaveSpiPort;
import com.fich.sarh.organizationalsubunit.domain.model.OrganizationalSubUnit;
import com.fich.sarh.organizationalsubunit.infrastructure.adapter.input.rest.model.request.OrganizationalSubUnitRequest;
import com.fich.sarh.organizationalunit.application.ports.persistence.OrganizationalUnitRetrieveSpiPort;
import com.fich.sarh.organizationalunit.domain.model.OrganizationalUnit;

@UseCase
public class OrganizationalSubUnitUpdateUseCase implements OrganizationalSubUnitUpdateApiPort {

    private final OrganizationalSubUnitSaveSpiPort subunitSavePort;
    private final OrganizationalSubUnitRetrieveSpiPort subunitRetrievePort;
    private final OrganizationalUnitRetrieveSpiPort organizationalUnitRetrieveSpiPort;
    public OrganizationalSubUnitUpdateUseCase(OrganizationalSubUnitSaveSpiPort subunitSavePort, OrganizationalSubUnitRetrieveSpiPort subunitRetrievePort, OrganizationalUnitRetrieveSpiPort organizationalUnitRetrieveSpiPort) {
        this.subunitSavePort = subunitSavePort;
        this.subunitRetrievePort = subunitRetrievePort;
        this.organizationalUnitRetrieveSpiPort = organizationalUnitRetrieveSpiPort;
    }


    @Override
    public OrganizationalSubUnit updateOrganizationSubUnit(Long id, OrganizationalSubUnitRequest command) {

       OrganizationalUnit organizationalUnit = organizationalUnitRetrieveSpiPort.findById(command.getOrganizationalUnit())
               .orElseThrow(() -> new BusinessRuleViolationException("No existe el Departamento indicado"));

        return subunitRetrievePort.findById(id).map(
                subUnit -> {
                    subUnit.setNameSubUnit(command.getNameSubUnit());
                    subUnit.setGuaraniCode(command.getGuaraniCode());
                    subUnit.setOrganizationalUnit(organizationalUnit);
                    return subunitSavePort.saveOrganizationalSubUnit(subUnit);
                }
        ).get();
    }
}
