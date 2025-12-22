package com.fich.sarh.auth.Infrastructure.adapter.input.rest.controller;

import com.fich.sarh.auth.Application.ports.output.persistence.UserDetailsSpiPort;
import com.fich.sarh.auth.Application.ports.output.persistence.UserResetPasswordSpiPort;
import com.fich.sarh.auth.Infrastructure.adapter.configuration.security.CustomUserDetails;
import com.fich.sarh.auth.Infrastructure.adapter.input.rest.model.request.LoginRequest;
import com.fich.sarh.auth.Infrastructure.adapter.input.rest.model.request.UserChangePasswordRequest;
import com.fich.sarh.auth.Infrastructure.adapter.input.rest.model.response.AuthResponse;

import com.fich.sarh.common.exceptions.BusinessRuleViolationException;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final UserDetailsSpiPort userDetailsService;

    private final UserResetPasswordSpiPort passwordService;

    Logger logger = LoggerFactory.getLogger(AuthenticationController.class);


    @PostMapping(value = "log-in")
    public ResponseEntity<AuthResponse> login(@RequestBody @Valid LoginRequest userRequest){

          AuthResponse authResponse =  userDetailsService.loginUser(userRequest);

        logger.info("AUTORIZADO RESPONSE " + authResponse );


        return new ResponseEntity<>( authResponse, HttpStatus.OK);

    }

    @PostMapping("refresh")
    public ResponseEntity<AuthResponse> refresh(@RequestBody Map<String, String> request){
        logger.info("REFRESH TOKEN " + request.get("refreshToken"));
        return ResponseEntity.ok(userDetailsService.refreshToken(request.get("refreshToken")));
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping(value = "/change")
    public ResponseEntity<?> changePassword(Authentication authentication, @RequestBody @Valid UserChangePasswordRequest request){

        logger.error("AUTH EN CONTROLLER: {}", authentication);


        if( authentication == null || !(authentication.getPrincipal() instanceof CustomUserDetails user)){
            throw new BusinessRuleViolationException("Usuario no autenticado");
        }

        boolean success = passwordService.changePassword(
                user.getId(), request.currentPassword(), request.newPassword()
        );

        logger.warn("Actualizacion de contraseña pendiente");

        if(!success) {
            throw new BusinessRuleViolationException("Password actual incorrecta");
        }

        return ResponseEntity.ok( Map.of("message", "Password actualizada correctamente"));

    }

}
