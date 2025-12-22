package com.fich.sarh.auth.Infrastructure.adapter.input.rest.model.response;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponse {

    private String email;
    private String username;
    private String profilePicturePath;
    private Set<RoleResponse> roles;
}
