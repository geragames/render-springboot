package com.fich.sarh.auth.Infrastructure.adapter.output.persistence.mapper;

import com.fich.sarh.auth.Domain.model.RoleDTO;
import com.fich.sarh.auth.Infrastructure.adapter.output.persistence.entities.RoleEntity;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-12-24T16:36:46-0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.9 (Microsoft)"
)
@Component
public class RoleMapperImpl implements RoleMapper {

    @Override
    public RoleDTO toDto(RoleEntity entity) {
        if ( entity == null ) {
            return null;
        }

        RoleDTO.RoleDTOBuilder roleDTO = RoleDTO.builder();

        roleDTO.id( entity.getId() );
        roleDTO.roleEnum( entity.getRoleEnum() );

        return roleDTO.build();
    }

    @Override
    public RoleEntity toEntity(RoleDTO dto) {
        if ( dto == null ) {
            return null;
        }

        RoleEntity.RoleEntityBuilder roleEntity = RoleEntity.builder();

        roleEntity.id( dto.getId() );
        roleEntity.roleEnum( dto.getRoleEnum() );

        return roleEntity.build();
    }

    @Override
    public Set<RoleEntity> toEntityList(Set<RoleDTO> roles) {
        if ( roles == null ) {
            return null;
        }

        Set<RoleEntity> set = LinkedHashSet.newLinkedHashSet( roles.size() );
        for ( RoleDTO roleDTO : roles ) {
            set.add( toEntity( roleDTO ) );
        }

        return set;
    }

    @Override
    public List<RoleDTO> toDtoList(List<RoleEntity> roles) {
        if ( roles == null ) {
            return null;
        }

        List<RoleDTO> list = new ArrayList<RoleDTO>( roles.size() );
        for ( RoleEntity roleEntity : roles ) {
            list.add( toDto( roleEntity ) );
        }

        return list;
    }
}
