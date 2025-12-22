package com.fich.sarh.auth.Application.services;

import com.fich.sarh.auth.Application.ports.entrypoint.api.UserSaveApiPort;
import com.fich.sarh.auth.Application.ports.output.persistence.UserSaveSpiPort;
import com.fich.sarh.auth.Domain.model.UserDTO;
import com.fich.sarh.common.UseCase;

@UseCase
public class UserSaveUseCase implements UserSaveApiPort {

    private final UserSaveSpiPort userSaveSpiPort;

    public UserSaveUseCase(UserSaveSpiPort userSaveSpiPort) {
        this.userSaveSpiPort = userSaveSpiPort;
    }

    @Override
    public UserDTO saveUsername(UserDTO user) {

        return userSaveSpiPort.saveUsername(user);
    }
}
