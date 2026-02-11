package com.fich.sarh.auth.Application.ports.entrypoint.api;

import com.fich.sarh.auth.Infrastructure.adapter.input.rest.model.request.UserRequest;
import com.fich.sarh.auth.Infrastructure.adapter.input.rest.model.response.AuthResponse;

public interface UserManagerApiPort {
    AuthResponse createUser(UserRequest request);
}
