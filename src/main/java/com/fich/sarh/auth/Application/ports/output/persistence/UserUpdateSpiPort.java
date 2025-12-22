package com.fich.sarh.auth.Application.ports.output.persistence;

import com.fich.sarh.auth.Domain.model.UserDTO;

public interface UserUpdateSpiPort {

    UserDTO updateUser(Long userId, UserDTO dto);
}
