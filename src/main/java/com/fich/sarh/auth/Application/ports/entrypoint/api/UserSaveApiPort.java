package com.fich.sarh.auth.Application.ports.entrypoint.api;

import com.fich.sarh.auth.Domain.model.UserDTO;

public interface UserSaveApiPort {

    UserDTO saveUsername(UserDTO user);


}
