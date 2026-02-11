package com.fich.sarh.auth.Application.services;

import com.fich.sarh.auth.Application.ports.entrypoint.api.UserSaveApiPort;
import com.fich.sarh.auth.Application.ports.output.persistence.UserSaveSpiPort;
import com.fich.sarh.auth.Domain.model.UserDTO;
import com.fich.sarh.auth.Infrastructure.adapter.output.persistence.entities.UserEntity;
import com.fich.sarh.common.UseCase;

@UseCase
public class UserSaveUseCase implements UserSaveApiPort {

    private final UserSaveSpiPort userSaveSpiPort;

    public UserSaveUseCase(UserSaveSpiPort userSaveSpiPort) {
        this.userSaveSpiPort = userSaveSpiPort;
    }

    @Override
    public UserEntity saveUsername(UserEntity user) {

        return userSaveSpiPort.saveUsername(user);
    }
}
