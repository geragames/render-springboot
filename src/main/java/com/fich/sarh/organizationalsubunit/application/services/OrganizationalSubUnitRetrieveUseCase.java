package com.fich.sarh.organizationalsubunit.application.services;

import com.fich.sarh.common.UseCase;
import com.fich.sarh.organizationalsubunit.application.ports.entrypoint.api.OrganizationalSubUnitRetrieveApiPort;
import com.fich.sarh.organizationalsubunit.application.ports.persistence.OrganizationalSubUnitRetrieveSpiPort;
import com.fich.sarh.organizationalsubunit.domain.model.OrganizationalSubUnit;
import com.fich.sarh.organizationalsubunit.domain.model.OrganizationalSubUnitDTO;

import java.util.List;
import java.util.Optional;

@UseCase
public class OrganizationalSubUnitRetrieveUseCase implements OrganizationalSubUnitRetrieveApiPort {


    private final OrganizationalSubUnitRetrieveSpiPort subunitRetrieve;

    public OrganizationalSubUnitRetrieveUseCase(OrganizationalSubUnitRetrieveSpiPort subunitRetrieve) {
        this.subunitRetrieve = subunitRetrieve;
    }


    @Override
    public List<OrganizationalSubUnitDTO> getAllOrganizationalSubUnitDTOs() {

        return subunitRetrieve.findAllOrganizationalSubUnitDTO();
    }

    @Override
    public List<OrganizationalSubUnit> getAllOrganizationalSubUnits() {
        return subunitRetrieve.findAllOrganizationalSubUnit();
    }

    @Override
    public Optional<OrganizationalSubUnit> findById(Long id) {
        return  subunitRetrieve.findById(id);
    }

    @Override
    public OrganizationalSubUnit findByNameSubUnit(String subunit) {
        return null;
    }
}
