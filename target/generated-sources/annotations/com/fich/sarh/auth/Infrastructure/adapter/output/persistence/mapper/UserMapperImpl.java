package com.fich.sarh.auth.Infrastructure.adapter.output.persistence.mapper;

import com.fich.sarh.auth.Domain.model.RoleDTO;
import com.fich.sarh.auth.Domain.model.UserDTO;
import com.fich.sarh.auth.Infrastructure.adapter.output.persistence.entities.RoleEntity;
import com.fich.sarh.auth.Infrastructure.adapter.output.persistence.entities.UserEntity;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.processing.Generated;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-12-23T18:53:35-0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.9 (Microsoft)"
)
public class UserMapperImpl implements UserMapper {

    @Override
    public UserEntity toUserEntity(UserDTO user) {
        if ( user == null ) {
            return null;
        }

        UserEntity.UserEntityBuilder userEntity = UserEntity.builder();

        userEntity.roles( roleDTOSetToRoleEntitySet( user.getRoles() ) );
        userEntity.id( user.getId() );
        userEntity.email( user.getEmail() );
        userEntity.username( user.getUsername() );
        userEntity.profilePicturePath( user.getProfilePicturePath() );
        userEntity.password( user.getPassword() );
        userEntity.mustChangePassword( user.isMustChangePassword() );
        userEntity.accountNonExpired( user.isAccountNonExpired() );
        userEntity.accountNonLocked( user.isAccountNonLocked() );
        userEntity.credentialNonExpired( user.isCredentialNonExpired() );

        return userEntity.build();
    }

    @Override
    public UserDTO toUserDTO(UserEntity entity) {
        if ( entity == null ) {
            return null;
        }

        UserDTO.UserDTOBuilder userDTO = UserDTO.builder();

        userDTO.id( entity.getId() );
        userDTO.email( entity.getEmail() );
        userDTO.username( entity.getUsername() );
        userDTO.password( entity.getPassword() );
        userDTO.mustChangePassword( entity.isMustChangePassword() );
        userDTO.profilePicturePath( entity.getProfilePicturePath() );
        userDTO.accountNonExpired( entity.isAccountNonExpired() );
        userDTO.accountNonLocked( entity.isAccountNonLocked() );
        userDTO.credentialNonExpired( entity.isCredentialNonExpired() );
        userDTO.roles( roleEntitySetToRoleDTOSet( entity.getRoles() ) );

        return userDTO.build();
    }

    @Override
    public List<UserDTO> toUserEntityList(List<UserEntity> userList) {
        if ( userList == null ) {
            return null;
        }

        List<UserDTO> list = new ArrayList<UserDTO>( userList.size() );
        for ( UserEntity userEntity : userList ) {
            list.add( toUserDTO( userEntity ) );
        }

        return list;
    }

    protected RoleEntity roleDTOToRoleEntity(RoleDTO roleDTO) {
        if ( roleDTO == null ) {
            return null;
        }

        RoleEntity.RoleEntityBuilder roleEntity = RoleEntity.builder();

        roleEntity.id( roleDTO.getId() );
        roleEntity.roleEnum( roleDTO.getRoleEnum() );

        return roleEntity.build();
    }

    protected Set<RoleEntity> roleDTOSetToRoleEntitySet(Set<RoleDTO> set) {
        if ( set == null ) {
            return null;
        }

        Set<RoleEntity> set1 = LinkedHashSet.newLinkedHashSet( set.size() );
        for ( RoleDTO roleDTO : set ) {
            set1.add( roleDTOToRoleEntity( roleDTO ) );
        }

        return set1;
    }

    protected RoleDTO roleEntityToRoleDTO(RoleEntity roleEntity) {
        if ( roleEntity == null ) {
            return null;
        }

        RoleDTO.RoleDTOBuilder roleDTO = RoleDTO.builder();

        roleDTO.id( roleEntity.getId() );
        roleDTO.roleEnum( roleEntity.getRoleEnum() );

        return roleDTO.build();
    }

    protected Set<RoleDTO> roleEntitySetToRoleDTOSet(Set<RoleEntity> set) {
        if ( set == null ) {
            return null;
        }

        Set<RoleDTO> set1 = LinkedHashSet.newLinkedHashSet( set.size() );
        for ( RoleEntity roleEntity : set ) {
            set1.add( roleEntityToRoleDTO( roleEntity ) );
        }

        return set1;
    }
}
