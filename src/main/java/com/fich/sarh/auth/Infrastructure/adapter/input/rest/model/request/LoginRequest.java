package com.fich.sarh.auth.Infrastructure.adapter.input.rest.model.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;


public record LoginRequest(@NotBlank String username,
        @NotBlank String password) {


}
