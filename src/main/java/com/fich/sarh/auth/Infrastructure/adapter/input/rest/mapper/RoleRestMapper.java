package com.fich.sarh.auth.Infrastructure.adapter.input.rest.mapper;

import com.fich.sarh.auth.Domain.model.RoleDTO;
import com.fich.sarh.auth.Infrastructure.adapter.input.rest.model.request.RoleRequest;
import com.fich.sarh.auth.Infrastructure.adapter.input.rest.model.response.RoleResponse;
import com.fich.sarh.auth.Infrastructure.adapter.output.persistence.entities.RoleEntity;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.Set;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface RoleRestMapper {
    RoleRestMapper INSTANCE = Mappers.getMapper(RoleRestMapper.class);

    RoleRequest toRequest(RoleDTO role);

    Set<RoleRequest> toRequestList(Set<RoleDTO> roles);

    Set<RoleEntity> toRoleEntityList(Set<RoleRequest> roles);

    Set<RoleResponse> toRoleResponseSet(Set<RoleEntity>roles);

    Set<RoleDTO> toRoleDTOList(Set<RoleRequest> roles);
}
