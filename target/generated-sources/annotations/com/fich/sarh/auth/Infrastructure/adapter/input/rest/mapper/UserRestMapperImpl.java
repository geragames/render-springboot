package com.fich.sarh.auth.Infrastructure.adapter.input.rest.mapper;

import com.fich.sarh.auth.Domain.model.RoleDTO;
import com.fich.sarh.auth.Domain.model.UserDTO;
import com.fich.sarh.auth.Infrastructure.adapter.input.rest.model.request.RoleRequest;
import com.fich.sarh.auth.Infrastructure.adapter.input.rest.model.request.UserRequest;
import com.fich.sarh.auth.Infrastructure.adapter.input.rest.model.response.RoleResponse;
import com.fich.sarh.auth.Infrastructure.adapter.input.rest.model.response.UserResponse;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-12-23T10:33:59-0300",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.9 (Microsoft)"
)
@Component
public class UserRestMapperImpl implements UserRestMapper {

    @Override
    public UserDTO toUser(UserRequest request) {
        if ( request == null ) {
            return null;
        }

        UserDTO.UserDTOBuilder userDTO = UserDTO.builder();

        userDTO.email( request.getEmail() );
        userDTO.username( request.getUsername() );
        userDTO.password( request.getPassword() );
        userDTO.profilePicturePath( request.getProfilePicturePath() );
        userDTO.roles( roleRequestSetToRoleDTOSet( request.getRoles() ) );

        return userDTO.build();
    }

    @Override
    public UserResponse toUserResponse(UserDTO user) {
        if ( user == null ) {
            return null;
        }

        UserResponse.UserResponseBuilder userResponse = UserResponse.builder();

        userResponse.email( user.getEmail() );
        userResponse.username( user.getUsername() );
        userResponse.profilePicturePath( user.getProfilePicturePath() );
        userResponse.roles( roleDTOSetToRoleResponseSet( user.getRoles() ) );

        return userResponse.build();
    }

    @Override
    public UserRequest toUserRequest(UserDTO user) {
        if ( user == null ) {
            return null;
        }

        UserRequest.UserRequestBuilder userRequest = UserRequest.builder();

        userRequest.email( user.getEmail() );
        userRequest.username( user.getUsername() );
        userRequest.profilePicturePath( user.getProfilePicturePath() );
        userRequest.password( user.getPassword() );
        userRequest.roles( roleDTOSetToRoleRequestSet( user.getRoles() ) );

        return userRequest.build();
    }

    @Override
    public List<UserResponse> toUserResponseList(List<UserDTO> users) {
        if ( users == null ) {
            return null;
        }

        List<UserResponse> list = new ArrayList<UserResponse>( users.size() );
        for ( UserDTO userDTO : users ) {
            list.add( toUserResponse( userDTO ) );
        }

        return list;
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

    protected Set<RoleDTO> roleRequestSetToRoleDTOSet(Set<RoleRequest> set) {
        if ( set == null ) {
            return null;
        }

        Set<RoleDTO> set1 = LinkedHashSet.newLinkedHashSet( set.size() );
        for ( RoleRequest roleRequest : set ) {
            set1.add( roleRequestToRoleDTO( roleRequest ) );
        }

        return set1;
    }

    protected RoleResponse roleDTOToRoleResponse(RoleDTO roleDTO) {
        if ( roleDTO == null ) {
            return null;
        }

        RoleResponse.RoleResponseBuilder roleResponse = RoleResponse.builder();

        roleResponse.id( roleDTO.getId() );

        return roleResponse.build();
    }

    protected Set<RoleResponse> roleDTOSetToRoleResponseSet(Set<RoleDTO> set) {
        if ( set == null ) {
            return null;
        }

        Set<RoleResponse> set1 = LinkedHashSet.newLinkedHashSet( set.size() );
        for ( RoleDTO roleDTO : set ) {
            set1.add( roleDTOToRoleResponse( roleDTO ) );
        }

        return set1;
    }

    protected RoleRequest roleDTOToRoleRequest(RoleDTO roleDTO) {
        if ( roleDTO == null ) {
            return null;
        }

        RoleRequest.RoleRequestBuilder roleRequest = RoleRequest.builder();

        roleRequest.id( roleDTO.getId() );
        roleRequest.roleEnum( roleDTO.getRoleEnum() );

        return roleRequest.build();
    }

    protected Set<RoleRequest> roleDTOSetToRoleRequestSet(Set<RoleDTO> set) {
        if ( set == null ) {
            return null;
        }

        Set<RoleRequest> set1 = LinkedHashSet.newLinkedHashSet( set.size() );
        for ( RoleDTO roleDTO : set ) {
            set1.add( roleDTOToRoleRequest( roleDTO ) );
        }

        return set1;
    }
}
