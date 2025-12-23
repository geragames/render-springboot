package com.fich.sarh.auth.Domain.model;

import com.fich.sarh.auth.Infrastructure.adapter.validation.UniqueUsername;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Collection;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDTO {
    private Long id;
    @Email(message = "Correo no valido")
    @NotBlank(message = "El correo electronico es obligatorio")
    private String email;

    @NotBlank(message = "El nombre de usuario es obligatorio")
    @Size(min = 6, max = 12, message = "El nombre de usuario tiene que tener al menos 6 caracteres ")
    //@UniqueUsername
    private String username;
    @Pattern(
            regexp = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).{8,}$",
            message = "La contraseña debe tener al menos 8 caracteres, una mayúscula, una minúscula y un número"
    )
    private String password;

    private boolean mustChangePassword;

    private String profilePicturePath;

    private boolean isEnabled = true;

    private boolean accountNonExpired;

    private boolean accountNonLocked;

    private boolean credentialNonExpired;

    private Set<RoleDTO> roles;


}
