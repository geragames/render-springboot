package com.fich.sarh.auth.Application.ports.entrypoint.api;

import com.fich.sarh.auth.Domain.model.UserDTO;
import com.fich.sarh.auth.Infrastructure.adapter.output.persistence.entities.UserEntity;

public interface UserSaveApiPort {

    UserEntity saveUsername(UserEntity user);


}
