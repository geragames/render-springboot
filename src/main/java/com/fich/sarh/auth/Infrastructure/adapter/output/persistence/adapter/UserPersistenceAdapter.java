package com.fich.sarh.auth.Infrastructure.adapter.output.persistence.adapter;

import com.fich.sarh.auth.Application.ports.output.persistence.UserRetrieveSpiPort;
import com.fich.sarh.auth.Application.ports.output.persistence.UserSaveSpiPort;
import com.fich.sarh.auth.Application.ports.output.persistence.UserUploadSpiPort;
import com.fich.sarh.auth.Domain.model.UserDTO;
import com.fich.sarh.auth.Infrastructure.adapter.output.persistence.entities.UserEntity;
import com.fich.sarh.auth.Infrastructure.adapter.output.persistence.mapper.UserMapper;
import com.fich.sarh.auth.Infrastructure.adapter.output.persistence.repository.UserRepository;
import com.fich.sarh.common.PersistenceAdapter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@PersistenceAdapter
public class UserPersistenceAdapter implements UserRetrieveSpiPort, UserSaveSpiPort, UserUploadSpiPort {

    Logger logger = LoggerFactory.getLogger(this.getClass());
    private final UserRepository userRepository;
    private String file_path;

    public UserPersistenceAdapter(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<UserDTO> findAllUsers() {
        return this.userRepository.findAll().stream().map(user -> UserMapper.INSTANCE.toUserDTO(user)
        ).collect(Collectors.toList());

    }

    @Override
    public Optional<UserDTO> findByUsername(String username) {
        return Optional.of(userRepository.findByUsername(username).map(
                UserMapper.INSTANCE::toUserDTO
        )).get();
    }

    @Override
    public Optional<UserDTO> findUserById(Long userId) {
        return Optional.of(userRepository.findById(userId).map(
                UserMapper.INSTANCE::toUserDTO
        ) ).get();
    }

    @Override
    public List<UserDTO> findUserByUsernameAndEmail(String query) {
        return this.userRepository.findUserByUsernameAndEmail(query).stream().map(
                UserMapper.INSTANCE::toUserDTO
        ).collect(Collectors.toList());
    }

    @Override
    public byte[] getPhotoByUsername(String username) {

        logger.info("USUARIO A BUSCAR ... " + username);
        UserEntity user = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("Usuario no encontrado"));
        String photoProfile = user.getProfilePicturePath();
        String basePath = new File(".").getPath();


        try {
            Path path = Paths.get(basePath, photoProfile).normalize();
            return Files.readAllBytes(Paths.get(path.toString()));
        } catch (IOException e) {
            throw new RuntimeException("Error al leer la foto " + e.getMessage());
        }
    }

    @Override
    public boolean existsUsername(String username) {
        return userRepository.existsByUsername(username);
    }

    @Override
    public UserDTO saveUsername(UserDTO user) {


        return UserMapper.INSTANCE.toUserDTO(userRepository.save(UserMapper.INSTANCE.toUserEntity(user)));
    }


    @Override
    public String uploadProfilePicture(MultipartFile file) {
        if (file != null && !file.isEmpty()) {
            try {
                String uploadsDir = "uploads/profile-pictures/";
                Path path = Paths.get(uploadsDir);
                if (!Files.exists(path)) {
                    Files.createDirectories(path);
                }
                String filename = UUID.randomUUID() + "_" + file.getOriginalFilename();
                Path filepath = path.resolve(filename);
                Files.copy(file.getInputStream(), filepath, StandardCopyOption.REPLACE_EXISTING);
                this.file_path = "/" + uploadsDir + filename;
            } catch (IOException e) {
                throw new RuntimeException("Error al guardar la imagen " + e);
            }
        }

        return this.file_path;
    }
}
