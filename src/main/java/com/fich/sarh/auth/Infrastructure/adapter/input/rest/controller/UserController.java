package com.fich.sarh.auth.Infrastructure.adapter.input.rest.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fich.sarh.auth.Application.ports.entrypoint.api.RoleRetrieveApiPort;
import com.fich.sarh.auth.Application.ports.entrypoint.api.UserSaveApiPort;
import com.fich.sarh.auth.Application.ports.output.persistence.UserResetPasswordSpiPort;
import com.fich.sarh.auth.Application.ports.output.persistence.UserRetrieveSpiPort;
import com.fich.sarh.auth.Application.ports.output.persistence.UserUpdateSpiPort;
import com.fich.sarh.auth.Application.ports.output.persistence.UserUploadSpiPort;
import com.fich.sarh.auth.Domain.model.RoleDTO;
import com.fich.sarh.auth.Domain.model.UserDTO;
import com.fich.sarh.auth.Infrastructure.adapter.input.rest.mapper.UserRestMapper;
import com.fich.sarh.auth.Infrastructure.adapter.output.persistence.entities.RoleEntity;
import com.fich.sarh.auth.Infrastructure.adapter.output.persistence.entities.UserEntity;
import com.fich.sarh.auth.Infrastructure.adapter.output.persistence.mapper.RoleMapper;
import com.fich.sarh.auth.Infrastructure.adapter.output.persistence.mapper.UserMapper;
import com.fich.sarh.common.exceptions.BusinessRuleViolationException;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Optional;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@RestController
@RequestMapping("user")
public class UserController {

    private final UserSaveApiPort userSave;

    private final UserUploadSpiPort userUploadSpiPort;
    private final PasswordEncoder passwordEncoder;
    private final UserResetPasswordSpiPort passwordService;

    private final RoleRetrieveApiPort roleRetrieveApiPort;

    private final UserRetrieveSpiPort userRetrieveSpiPort;

    private final UserUpdateSpiPort userUpdateSpiPort;

    private String file_path;
    Logger logger = LoggerFactory.getLogger(getClass());


    public UserController(UserSaveApiPort userSave, UserUploadSpiPort userUploadSpiPort, PasswordEncoder passwordEncoder, UserResetPasswordSpiPort passwordService, RoleRetrieveApiPort roleRetrieveApiPort, UserRetrieveSpiPort userRetrieveSpiPort, UserUpdateSpiPort userUpdateSpiPort) {
        this.userSave = userSave;
        this.userUploadSpiPort = userUploadSpiPort;
        this.passwordEncoder = passwordEncoder;
        this.passwordService = passwordService;

        this.roleRetrieveApiPort = roleRetrieveApiPort;
        this.userRetrieveSpiPort = userRetrieveSpiPort;
        this.userUpdateSpiPort = userUpdateSpiPort;
    }

    @PreAuthorize("hasAnyRole('ADMIN','DEVELOPER')")
    @GetMapping("{userId}")
    public ResponseEntity<?> fetchUserById(@PathVariable Long userId) {
        return ResponseEntity.ok().body(UserRestMapper.INSTANCE.toUserResponse(userRetrieveSpiPort.findUserById(userId).get()));
    }

    @PreAuthorize("hasAnyRole('ADMIN', 'DEVELOPER')")
    @GetMapping("all")
    public ResponseEntity<?> fetchAllUsers() {
        return ResponseEntity.ok().body(userRetrieveSpiPort.findAllUsers());
    }


    @PreAuthorize("hasAnyRole('ADMIN','DEVELOPER')")
    @GetMapping("/search/{query}")
    public ResponseEntity<?> fetchUserByUsernameAndEmail(@PathVariable String query) {
        return ResponseEntity.ok().body(userRetrieveSpiPort.findUserByUsernameAndEmail(query));
    }

    @PreAuthorize("hasAnyRole('ADMIN','DEVELOPER')")
    @PostMapping(value = "create", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<?> createUser(@Valid @RequestPart("createUser") UserDTO createUser,
                                        @RequestPart(value = "file", required = false) MultipartFile file) throws JsonProcessingException {


        Set<RoleDTO> roles = createUser.getRoles().stream()
                .map(role -> {
                    return roleRetrieveApiPort.fetchByRoleEnum(role.getRoleEnum());
                })
                .collect(Collectors.toSet());

        Set<RoleEntity> roles_entity = RoleMapper.INSTANCE.toEntityList(roles);


        logger.info("ROLES " + roles_entity);

        String filename = "";
        if (file != null && !file.isEmpty()) {
            filename = userUploadSpiPort.uploadProfilePicture(file);
        }
        logger.info("ARCHIVO " + filename);
        UserEntity userEntity = UserEntity.builder()
                .username(createUser.getUsername())
                .password(passwordEncoder.encode(createUser.getPassword()))
                .email(createUser.getEmail())
                .profilePicturePath(filename)
                .isEnabled(true)
                .accountNonExpired(true)
                .accountNonLocked(true)
                .credentialNonExpired(true)
                .roles(roles_entity).build();


        return ResponseEntity.status(HttpStatus.CREATED).body(userSave.saveUsername(UserMapper.INSTANCE.toUserDTO(userEntity)));
    }

    @PreAuthorize("hasAnyRole('ADMIN','DEVELOPER')")
    @PutMapping(value = "/update/{userId}", consumes = {MediaType.MULTIPART_FORM_DATA_VALUE})
    public ResponseEntity<?> updateUser(@PathVariable Long userId, @Validated @RequestPart("updateUser") UserDTO updateUser,
                                        @RequestPart(value = "file", required = false) MultipartFile file) throws JsonProcessingException {


        if (file != null && !file.isEmpty()) {
            String filename = storeProfileImage(file);
            updateUser.setProfilePicturePath(filename);
        }


        UserDTO saveUser = userUpdateSpiPort.updateUser(userId, updateUser);

        return ResponseEntity.ok(saveUser);
    }


    @PreAuthorize("hasAnyRole('USER','ADMIN','DEVELOPER')")
    @GetMapping("{username}/photo")
    public ResponseEntity<byte[]> getUserPhoto(@PathVariable String username) throws IOException {
        byte[] imageBytes = userRetrieveSpiPort.getPhotoByUsername(username);

        String contentType = Files.probeContentType(Paths.get("uploads/profile-pictures/" + username + ".jpg"));
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(contentType != null ? contentType : "image/jpg"))
                .body(imageBytes);
    }

    private String storeProfileImage(MultipartFile file) {
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
                throw new RuntimeException("Error al guardar imagen de perfil");
            }

            }
        return this.file_path;

    }

}


 /*   @PostMapping("/createuser")
    public ResponseEntity<?> createUser(@Valid @RequestBody UserRequest createUser){

        Set<RoleRequest> roles = createUser.getRoles().stream().map(
                role -> RoleRequest.builder().name(role.getName()).build()
        ).collect(Collectors.toSet());

        UserDTO userDTO = UserDTO.builder()
                .username(createUser.getUsername())
                .password(createUser.getPassword())
                .email(createUser.getEmail())
                .roles(RoleRestMapper.INSTANCE.toRoleDTOList(roles)).build();

        userSave.saveUsername(userDTO);

        return ResponseEntity.ok(userDTO);
    }*/



