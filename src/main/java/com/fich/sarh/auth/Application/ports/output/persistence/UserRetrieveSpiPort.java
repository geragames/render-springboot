package com.fich.sarh.auth.Application.ports.output.persistence;

import com.fich.sarh.auth.Domain.model.UserDTO;

import java.util.List;
import java.util.Optional;

public interface UserRetrieveSpiPort {

    List<UserDTO> findAllUsers();

    List<UserDTO> findUserByUsernameAndEmail(String query);

    Optional<UserDTO> findByUsername(String username);

    Optional<UserDTO> findUserById(Long userId);

    byte[] getPhotoByUsername(String username);

    boolean existsUsername(String username);
}
