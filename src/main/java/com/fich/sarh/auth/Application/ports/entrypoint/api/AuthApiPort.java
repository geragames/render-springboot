package com.fich.sarh.auth.Application.ports.entrypoint.api;

import com.fich.sarh.auth.Infrastructure.adapter.input.rest.model.request.LoginRequest;
import com.fich.sarh.auth.Infrastructure.adapter.input.rest.model.response.AuthResponse;

public interface AuthApiPort {
    AuthResponse login(LoginRequest request);
    AuthResponse refreshToken(String refreshToken);

}
