package com.fich.sarh.auth.Infrastructure.adapter.input.rest.mapper;


import com.fich.sarh.auth.Domain.model.UserDTO;
import com.fich.sarh.auth.Infrastructure.adapter.input.rest.model.request.UserRequest;
import com.fich.sarh.auth.Infrastructure.adapter.input.rest.model.response.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface UserRestMapper {
    UserRestMapper INSTANCE = Mappers.getMapper(UserRestMapper.class);
    UserDTO toUser(UserRequest request);
    UserResponse toUserResponse(UserDTO user);

    UserRequest toUserRequest(UserDTO user);
    List<UserResponse> toUserResponseList(List<UserDTO> users);

}
