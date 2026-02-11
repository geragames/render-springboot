package com.fich.sarh.auth.Application.services;

import com.auth0.jwt.interfaces.DecodedJWT;
import com.fich.sarh.auth.Application.ports.entrypoint.api.AuthApiPort;
import com.fich.sarh.auth.Infrastructure.adapter.configuration.security.CustomUserDetails;
import com.fich.sarh.auth.Infrastructure.adapter.configuration.security.jwt.JwtUtils;
import com.fich.sarh.auth.Infrastructure.adapter.input.rest.model.request.LoginRequest;
import com.fich.sarh.auth.Infrastructure.adapter.input.rest.model.response.AuthResponse;
import com.fich.sarh.auth.Infrastructure.adapter.output.persistence.adapter.UserDetailPersistenceAdapter;
import com.fich.sarh.common.UseCase;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Set;
import java.util.stream.Collectors;

@UseCase
@RequiredArgsConstructor
public class AuthServiceUseCase implements AuthApiPort {

    Logger logger = LoggerFactory.getLogger(getClass());

    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final UserDetailPersistenceAdapter userDetailService;

    @Override
    public AuthResponse login(LoginRequest request) {
        logger.info("ENTRO AL LOGIN DE AUTHSERVICEUSECASE");

        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.username(),
                            request.password()
                    )
            );

            logger.info("ENTRO A AUTENTICAR");

            SecurityContextHolder.getContext().setAuthentication(authentication);
            CustomUserDetails user = (CustomUserDetails) authentication.getPrincipal();

            boolean mustChangePassword = user.getMustChangePassword();
            String accessToken = jwtUtils.createToken(authentication, mustChangePassword);
            String refreshToken = jwtUtils.createRefreshToken(authentication);

            Set<String> authorities = authentication.getAuthorities().stream()
                    .map(GrantedAuthority::getAuthority)
                    .collect(Collectors.toSet());

            return new AuthResponse(
                    user.getId(),
                    user.getUsername(),
                    mustChangePassword ? "PASSWORD_CHANGE_REQUIRED" : "LOGIN_OK",
                    accessToken,
                    refreshToken,
                    mustChangePassword,
                    authorities,
                    true
            );

        } catch (Exception e) {
            logger.error("ERROR EN AUTHENTICATE", e);
            throw e;
        }
    }

    @Override
    public AuthResponse refreshToken(String refreshToken) {

        DecodedJWT decoded = jwtUtils.validateRefreshToken(refreshToken);

        String username = decoded.getSubject();
        UserDetails userDetails = userDetailService.loadUserByUsername(username);

        Authentication authentication = new UsernamePasswordAuthenticationToken(
                userDetails, null, userDetails.getAuthorities()
        );

        String newAccessToken = jwtUtils.createToken(authentication,false);

        Set<String> authorities =userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority).collect(Collectors.toSet());


        return new AuthResponse(
                null,
                username,
                "ACCESS_TOKEN_REFRESHED",
                newAccessToken,
                refreshToken,
                false,
                authorities,
                true

        );
    }
}
