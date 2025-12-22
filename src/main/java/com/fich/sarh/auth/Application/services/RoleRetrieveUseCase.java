package com.fich.sarh.auth.Application.services;

import com.fich.sarh.auth.Application.ports.entrypoint.api.RoleRetrieveApiPort;
import com.fich.sarh.auth.Application.ports.output.persistence.RoleRetrieveSpiPort;
import com.fich.sarh.auth.Domain.model.RoleDTO;
import com.fich.sarh.auth.Infrastructure.adapter.output.persistence.entities.RoleEnum;
import com.fich.sarh.common.UseCase;

import java.util.List;

@UseCase
public class RoleRetrieveUseCase implements RoleRetrieveApiPort {

    private final RoleRetrieveSpiPort roleRetrieveSpiPort;

    public RoleRetrieveUseCase(RoleRetrieveSpiPort roleRetrieveSpiPort) {
        this.roleRetrieveSpiPort = roleRetrieveSpiPort;
    }

    @Override
    public RoleDTO fetchByRoleEnum(RoleEnum name) {
        return roleRetrieveSpiPort.fetchByRoleEnum(name);
    }

    @Override
    public List<RoleDTO> fetchAllRole() {
        return roleRetrieveSpiPort.fetchAllRole();
    }
}
