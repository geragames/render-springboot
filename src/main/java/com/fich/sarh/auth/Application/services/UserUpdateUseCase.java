package com.fich.sarh.auth.Application.services;

import com.fich.sarh.auth.Application.ports.output.persistence.UserUpdateSpiPort;
import com.fich.sarh.auth.Domain.model.UserDTO;
import com.fich.sarh.auth.Infrastructure.adapter.output.persistence.entities.UserEntity;
import com.fich.sarh.auth.Infrastructure.adapter.output.persistence.mapper.RoleMapper;
import com.fich.sarh.auth.Infrastructure.adapter.output.persistence.mapper.UserMapper;
import com.fich.sarh.auth.Infrastructure.adapter.output.persistence.repository.UserRepository;
import com.fich.sarh.common.SecurityUtils;
import com.fich.sarh.common.UseCase;
import com.fich.sarh.common.exceptions.BusinessRuleViolationException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@UseCase
public class UserUpdateUseCase implements UserUpdateSpiPort {

    private final UserRepository userRepository;

    public UserUpdateUseCase(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public UserDTO updateUser(Long userId, UserDTO dto) {
        UserEntity userEntity = userRepository.findById(userId).orElseThrow(
                () -> new BusinessRuleViolationException("Usuario no encontrado"));

        String loggedUsername = SecurityUtils.getCurrentUsername();
        if(loggedUsername == null || userEntity.getUsername().equalsIgnoreCase(loggedUsername)){
            throw new BusinessRuleViolationException("No tiene permisos para modificar este usuario  "+ loggedUsername);
        }


        if (dto.getEmail() != null &&
                !dto.getEmail().equalsIgnoreCase(userEntity.getEmail()) &&
                userRepository.existsByEmailAndIdNot(dto.getEmail(), userId)){
            throw new BusinessRuleViolationException("El email ya se encuentra registrado");
        }
        if (dto.getUsername() != null) {

            String newUsername = dto.getUsername().trim();
            String currentUsername = userEntity.getUsername();

            if (!newUsername.equalsIgnoreCase(currentUsername) &&
                    userRepository.existsByUsernameIgnoreCaseAndIdNot(newUsername, userId)) {

                throw new BusinessRuleViolationException("El nombre de usuario ya se encuentra registrado");
            }

            userEntity.setUsername(newUsername);
        }

        if (dto.getRoles() != null && !dto.getRoles().isEmpty()) {
            userEntity.setRoles(RoleMapper.INSTANCE.toEntityList(dto.getRoles()));
        }

        if (dto.getEmail() != null) {
            userEntity.setEmail(dto.getEmail().trim());
        }

        userEntity.setUsername(dto.getUsername());

        if (dto.getProfilePicturePath() != null && !dto.getProfilePicturePath().isBlank()) {
            userEntity.setProfilePicturePath(dto.getProfilePicturePath());
        }
        userRepository.save(userEntity);
        return UserMapper.INSTANCE.toUserDTO(userEntity);
    }


}
