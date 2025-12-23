package com.fich.sarh.auth.Infrastructure.adapter.input.rest.mapper;

import com.fich.sarh.auth.Domain.model.RoleDTO;
import com.fich.sarh.auth.Infrastructure.adapter.input.rest.model.request.RoleRequest;
import com.fich.sarh.auth.Infrastructure.adapter.input.rest.model.response.RoleResponse;
import com.fich.sarh.auth.Infrastructure.adapter.output.persistence.entities.RoleEntity;
import java.util.LinkedHashSet;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-12-23T07:54:26-0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.9 (Microsoft)"
)
@Component
public class RoleRestMapperImpl implements RoleRestMapper {

    @Override
    public RoleRequest toRequest(RoleDTO role) {
        if ( role == null ) {
            return null;
        }

        RoleRequest.RoleRequestBuilder roleRequest = RoleRequest.builder();

        roleRequest.id( role.getId() );
        roleRequest.roleEnum( role.getRoleEnum() );

        return roleRequest.build();
    }

    @Override
    public Set<RoleRequest> toRequestList(Set<RoleDTO> roles) {
        if ( roles == null ) {
            return null;
        }

        Set<RoleRequest> set = LinkedHashSet.newLinkedHashSet( roles.size() );
        for ( RoleDTO roleDTO : roles ) {
            set.add( toRequest( roleDTO ) );
        }

        return set;
    }

    @Override
    public Set<RoleEntity> toRoleEntityList(Set<RoleRequest> roles) {
        if ( roles == null ) {
            return null;
        }

        Set<RoleEntity> set = LinkedHashSet.newLinkedHashSet( roles.size() );
        for ( RoleRequest roleRequest : roles ) {
            set.add( roleRequestToRoleEntity( roleRequest ) );
        }

        return set;
    }

    @Override
    public Set<RoleResponse> toRoleResponseSet(Set<RoleEntity> roles) {
        if ( roles == null ) {
            return null;
        }

        Set<RoleResponse> set = LinkedHashSet.newLinkedHashSet( roles.size() );
        for ( RoleEntity roleEntity : roles ) {
            set.add( roleEntityToRoleResponse( roleEntity ) );
        }

        return set;
    }

    @Override
    public Set<RoleDTO> toRoleDTOList(Set<RoleRequest> roles) {
        if ( roles == null ) {
            return null;
        }

        Set<RoleDTO> set = LinkedHashSet.newLinkedHashSet( roles.size() );
        for ( RoleRequest roleRequest : roles ) {
            set.add( roleRequestToRoleDTO( roleRequest ) );
        }

        return set;
    }

    protected RoleEntity roleRequestToRoleEntity(RoleRequest roleRequest) {
        if ( roleRequest == null ) {
            return null;
        }

        RoleEntity.RoleEntityBuilder roleEntity = RoleEntity.builder();

        roleEntity.id( roleRequest.getId() );
        roleEntity.roleEnum( roleRequest.getRoleEnum() );

        return roleEntity.build();
    }

    protected RoleResponse roleEntityToRoleResponse(RoleEntity roleEntity) {
        if ( roleEntity == null ) {
            return null;
        }

        RoleResponse.RoleResponseBuilder roleResponse = RoleResponse.builder();

        roleResponse.id( roleEntity.getId() );

        return roleResponse.build();
    }

    protected RoleDTO roleRequestToRoleDTO(RoleRequest roleRequest) {
        if ( roleRequest == null ) {
            return null;
        }

        RoleDTO.RoleDTOBuilder roleDTO = RoleDTO.builder();

        roleDTO.id( roleRequest.getId() );
        roleDTO.roleEnum( roleRequest.getRoleEnum() );

        return roleDTO.build();
    }
}
