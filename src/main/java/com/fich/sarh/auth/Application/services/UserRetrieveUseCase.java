package com.fich.sarh.auth.Application.services;

import com.fich.sarh.auth.Application.ports.entrypoint.api.UserRetrieveApiPort;
import com.fich.sarh.auth.Application.ports.output.persistence.UserRetrieveSpiPort;
import com.fich.sarh.auth.Domain.model.UserDTO;
import com.fich.sarh.common.UseCase;
import com.fich.sarh.common.exceptions.BusinessRuleViolationException;

import java.util.List;
import java.util.Optional;

@UseCase
public class UserRetrieveUseCase implements UserRetrieveApiPort {

   private final UserRetrieveSpiPort userRetrieveSpiPort;

    public UserRetrieveUseCase(UserRetrieveSpiPort userRetrieveSpiPort) {
        this.userRetrieveSpiPort = userRetrieveSpiPort;
    }

    @Override
    public List<UserDTO> findAllUsers() {
        return null;
    }

    @Override
    public List<UserDTO> findUserByUsernameAndEmail(String query) {
        return userRetrieveSpiPort.findUserByUsernameAndEmail(query);
    }

    @Override
    public Optional<UserDTO> findByUsername(String username) {

        return userRetrieveSpiPort.findByUsername(username) ;
    }

    @Override
    public Optional<UserDTO> findUserById(Long userId) {
        Optional<UserDTO> user = userRetrieveSpiPort.findUserById(userId);
        if(!user.isPresent()){
            throw new BusinessRuleViolationException("El usuario con ID " + userId + " no existe");
        }

        return user;
    }


    @Override
    public byte[] getPhotoByUsername(String username) {
        return userRetrieveSpiPort.getPhotoByUsername(username);
    }
}
