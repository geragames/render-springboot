package com.fich.sarh.organizationalsubunit.application.ports.entrypoint.api;

import com.fich.sarh.organizationalsubunit.domain.model.OrganizationalSubUnit;
import com.fich.sarh.organizationalsubunit.infrastructure.adapter.input.rest.model.request.OrganizationalSubUnitRequest;

public interface OrganizationalSubUnitUpdateApiPort {

    OrganizationalSubUnit updateOrganizationSubUnit(Long id, OrganizationalSubUnitRequest command);
}
