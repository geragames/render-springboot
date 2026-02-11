package com.fich.sarh.auth.Application.ports.output.persistence;

import com.fich.sarh.auth.Domain.model.UserDTO;
import com.fich.sarh.auth.Infrastructure.adapter.output.persistence.entities.UserEntity;

public interface UserSaveSpiPort {

    UserEntity saveUsername(UserEntity user);
}
