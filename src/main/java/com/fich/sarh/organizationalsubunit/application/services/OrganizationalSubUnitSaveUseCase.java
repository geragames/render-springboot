package com.fich.sarh.organizationalsubunit.application.services;

import com.fich.sarh.common.UseCase;
import com.fich.sarh.common.exceptions.BusinessRuleViolationException;
import com.fich.sarh.organizationalsubunit.application.ports.entrypoint.api.OrganizationalSubUnitSaveApiPort;
import com.fich.sarh.organizationalsubunit.application.ports.persistence.OrganizationalSubUnitRetrieveSpiPort;
import com.fich.sarh.organizationalsubunit.application.ports.persistence.OrganizationalSubUnitSaveSpiPort;
import com.fich.sarh.organizationalsubunit.domain.model.OrganizationalSubUnit;
import com.fich.sarh.organizationalsubunit.infrastructure.adapter.input.rest.model.request.OrganizationalSubUnitRequest;
import com.fich.sarh.organizationalunit.application.ports.persistence.OrganizationalUnitRetrieveSpiPort;
import com.fich.sarh.organizationalunit.domain.model.OrganizationalUnit;

@UseCase
public class OrganizationalSubUnitSaveUseCase implements OrganizationalSubUnitSaveApiPort {

    private final OrganizationalSubUnitSaveSpiPort subunitSavePort;

    private final OrganizationalUnitRetrieveSpiPort unitRetrieveSpiPort;

    public OrganizationalSubUnitSaveUseCase(OrganizationalSubUnitSaveSpiPort subunitSavePort, OrganizationalUnitRetrieveSpiPort unitRetrieveSpiPort) {
        this.subunitSavePort = subunitSavePort;

        this.unitRetrieveSpiPort = unitRetrieveSpiPort;
    }


    @Override
    public OrganizationalSubUnit saveOrganizationSubUnit(OrganizationalSubUnitRequest request) {

        OrganizationalUnit organizationalUnit = unitRetrieveSpiPort.findById(request.getOrganizationalUnit())
                .orElseThrow(() -> new BusinessRuleViolationException("El departamento seleccionado no existe"));

        OrganizationalSubUnit subUnit = OrganizationalSubUnit.builder()
                                     .nameSubUnit(request.getNameSubUnit())
                                     .guaraniCode(request.getGuaraniCode())
                                     .organizationalUnit(organizationalUnit)
                                     .build();

        return subunitSavePort.saveOrganizationalSubUnit(subUnit);
    }
}
