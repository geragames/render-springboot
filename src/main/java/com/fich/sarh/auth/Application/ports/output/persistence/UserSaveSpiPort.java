package com.fich.sarh.auth.Application.ports.output.persistence;

import com.fich.sarh.auth.Domain.model.UserDTO;

public interface UserSaveSpiPort {

    UserDTO saveUsername(UserDTO user);
}
