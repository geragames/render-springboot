package com.fich.sarh.auth.Infrastructure.adapter.input.rest.model.request;


import com.fich.sarh.auth.Domain.model.RoleDTO;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequest {

    @Email
    @NotBlank
    private String email;
    @NotBlank
    private String username;

    private String profilePicturePath;

    @NotBlank
    private String password;

    boolean isEnabled;

    boolean accountNonExpired;

    boolean accountNonLocked;

    boolean credentialNonExpired;

    private Set<RoleRequest> roles;

}
