package com.fich.sarh.auth.Application.ports.entrypoint.api;

import com.fich.sarh.auth.Domain.model.RoleDTO;
import com.fich.sarh.auth.Infrastructure.adapter.output.persistence.entities.RoleEnum;

import java.util.List;

public interface RoleRetrieveApiPort {

    RoleDTO fetchByRoleEnum(RoleEnum name);

    List<RoleDTO> fetchAllRole();
}
