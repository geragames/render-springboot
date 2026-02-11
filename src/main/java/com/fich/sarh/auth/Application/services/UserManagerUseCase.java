package com.fich.sarh.auth.Application.services;

import com.fich.sarh.auth.Application.ports.entrypoint.api.UserManagerApiPort;
import com.fich.sarh.auth.Application.ports.output.persistence.RoleRetrieveSpiPort;
import com.fich.sarh.auth.Application.ports.output.persistence.UserSaveSpiPort;
import com.fich.sarh.auth.Infrastructure.adapter.input.rest.model.request.UserRequest;
import com.fich.sarh.auth.Infrastructure.adapter.input.rest.model.response.AuthResponse;
import com.fich.sarh.auth.Infrastructure.adapter.output.persistence.entities.UserEntity;
import com.fich.sarh.auth.Infrastructure.adapter.output.persistence.repository.RoleRepository;
import com.fich.sarh.common.UseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Set;
import java.util.stream.Collectors;

@UseCase
@RequiredArgsConstructor
public class UserManagerUseCase implements UserManagerApiPort {

    private final UserSaveSpiPort userSave;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public AuthResponse createUser(UserRequest request) {
        Set<String> roles = request.getRoles().stream()
                .map( r -> r.getRoleEnum().name())
                .collect(Collectors.toSet());

        UserEntity user = UserEntity.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .roles(roleRepository.findRoleEntitiesByRoleEnumIn(roles))
                .build();

        userSave.saveUsername(user);

        return new AuthResponse(
                user.getId(),
                user.getUsername(),
                "USER_CREATED",
                null,
                null,
                true,
                roles,
                true
        );
    }
}
