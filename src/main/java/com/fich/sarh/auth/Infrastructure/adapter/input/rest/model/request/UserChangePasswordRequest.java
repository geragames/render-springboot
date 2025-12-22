package com.fich.sarh.auth.Infrastructure.adapter.input.rest.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record UserChangePasswordRequest(
        @NotBlank
        String currentPassword,
        @NotBlank
        @Size(min = 8)
        String newPassword
) {
}
