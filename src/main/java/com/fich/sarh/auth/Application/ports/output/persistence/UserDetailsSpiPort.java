package com.fich.sarh.auth.Application.ports.output.persistence;

import com.fich.sarh.auth.Infrastructure.adapter.input.rest.model.request.LoginRequest;
import com.fich.sarh.auth.Infrastructure.adapter.input.rest.model.request.UserRequest;
import com.fich.sarh.auth.Infrastructure.adapter.input.rest.model.response.AuthResponse;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserDetailsSpiPort extends UserDetailsService {

    AuthResponse createUser(UserRequest request);
    AuthResponse loginUser(LoginRequest authLoginRequest);
    AuthResponse refreshToken(String refreshToken);
}
