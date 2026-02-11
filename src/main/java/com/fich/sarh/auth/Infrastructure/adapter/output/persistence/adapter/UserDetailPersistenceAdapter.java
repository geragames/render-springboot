package com.fich.sarh.auth.Infrastructure.adapter.output.persistence.adapter;


import com.fich.sarh.auth.Application.ports.output.persistence.UserResetPasswordSpiPort;
import com.fich.sarh.auth.Application.ports.output.persistence.UserSendEmailResetPasswordSpiPort;
import com.fich.sarh.auth.Infrastructure.adapter.configuration.security.CustomUserDetails;
import com.fich.sarh.auth.Infrastructure.adapter.configuration.security.jwt.JwtUtils;
import com.fich.sarh.auth.Infrastructure.adapter.output.persistence.entities.UserEntity;
import com.fich.sarh.auth.Infrastructure.adapter.output.persistence.repository.RoleRepository;
import com.fich.sarh.auth.Infrastructure.adapter.output.persistence.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class UserDetailPersistenceAdapter implements UserDetailsService, UserResetPasswordSpiPort {


    Logger logger = LoggerFactory.getLogger(UserDetailPersistenceAdapter.class);

    private JwtUtils jwtUtils;
    private final UserRepository userRepository;

    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;
    private final UserSendEmailResetPasswordSpiPort emailService;



    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.findUserEntityByUsername(username).
                orElseThrow(() -> new UsernameNotFoundException("El usuario " + username + " no existe"));

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        userEntity.getRoles().forEach(role ->
                authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getRoleEnum().name())));

        userEntity.getRoles().stream().flatMap(role -> role.getPermissionSet().stream())
                .forEach(permission -> authorities.add(new SimpleGrantedAuthority(permission.getName())));

        return new CustomUserDetails(
                userEntity.getId(),
                userEntity.getUsername(),
                userEntity.getPassword(),
                authorities,
                userEntity.isEnabled(),
                userEntity.isAccountNonLocked(),
                userEntity.isAccountNonExpired(),
                userEntity.isCredentialNonExpired(),
                userEntity.isMustChangePassword()
        );

    }

    @Override
    public boolean changePassword(Long userId, String currentPassword, String newPassword) {
        return userRepository.findById(userId).map(user -> {
            if (!passwordEncoder.matches(currentPassword, user.getPassword())) {
                return false;
            }

            user.setPassword(passwordEncoder.encode(newPassword));
            user.setMustChangePassword(false);

            userRepository.save(user);

            return true;

        }).orElse(false);
    }
    @Override
    public Optional<String> resetPasswordByAdmin(Long userId) {
        return userRepository.findById(userId).map(user -> {
            String tempPassword = UUID.randomUUID().toString().substring(0, 10);

            user.setPassword(passwordEncoder.encode(tempPassword));

            user.setMustChangePassword(true);

            userRepository.save(user);

            emailService.sendEmailResetPassword(tempPassword, user.getEmail());

            return tempPassword;

        });
    }

  /*  public AuthResponse createUser(UserRequest request) {

        String username = request.getUsername();
        String password = request.getPassword();

        Set<String> rolesRequest = request.getRoles() == null ?
                Collections.emptySet() : request.getRoles().stream().map(rol -> rol.getRoleEnum()
                .name()).collect(Collectors.toSet());

        Set<RoleEntity> roleEntityList = roleRepository.findRoleEntitiesByRoleEnumIn(rolesRequest)
                .stream().collect(Collectors.toSet());

        if (roleEntityList.isEmpty() && !rolesRequest.isEmpty()) {
            throw new IllegalArgumentException("The roles specified does not exist.");
        }

        UserEntity userEntity = UserEntity.builder()
                .username(username)
                .password(passwordEncoder.encode(password))
                .roles(roleEntityList)
                .enabled(true)
                .accountNonLocked(true)
                .accountNonExpired(true)
                .credentialNonExpired(true).build();

        UserEntity userSaved = userRepository.save(userEntity);

        List<SimpleGrantedAuthority> authorities = new ArrayList<>();

        userSaved.getRoles().forEach(role -> authorities
                .add(new SimpleGrantedAuthority("ROLE_".concat(role.getRoleEnum().name()))));

        userSaved.getRoles().stream().flatMap(role ->
                        role.getPermissionSet().stream())
                .map(p -> new SimpleGrantedAuthority(p.getName()))
                .forEach(authorities::add);
        //  .forEach(permission ->
        //          authorities.add(new SimpleGrantedAuthority(permission.getName())));


        CustomUserDetails userDetails = new CustomUserDetails(
                userSaved.getId(),
                userSaved.getUsername(),
                userSaved.getPassword(),
                authorities,
                userSaved.isEnabled(),
                userSaved.isAccountNonLocked(),
                userSaved.isAccountNonExpired(),
                userSaved.isCredentialNonExpired(),
                userSaved.isMustChangePassword()
        );
        //SecurityContext securityContextHolder = SecurityContextHolder.getContext();
        Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails,
                null, userDetails.getAuthorities());

        String accessToken = jwtUtils.createToken(authentication, userEntity.isMustChangePassword());
        String refreshToken = jwtUtils.createRefreshToken(authentication);

        Set<String> authorities_roles = userSaved.getRoles().stream()
                .map(rol -> rol.getRoleEnum().name())
                .collect(Collectors.toSet());

        //  AuthResponse authResponse = new AuthResponse(username, "User created successfully", accessToken, refreshToken, authorities_roles, true);
        return new AuthResponse(userSaved.getId(), userSaved.getUsername(), "User created successfully", accessToken,
                refreshToken, false, authorities_roles, true);
    }*/

  /*  public AuthResponse loginUser(LoginRequest authLoginRequest) {

        // logger.info("LOGIN " + authLoginRequest);
        if (authLoginRequest == null) {
            throw new BusinessRuleViolationException("LoginRequest is null");
        }

        String username = authLoginRequest.username();
        String password = authLoginRequest.password();
        logger.info("LOGIN " + username + " " + password);
        // Authentication authentication = this.authenticate(username, password);
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
        SecurityContextHolder.getContext().setAuthentication(authentication);


        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();


        boolean mustChangePassword = userDetails.getMustChangePassword();


        String accessToken = jwtUtils.createToken(authentication, mustChangePassword);
        String refreshToken = jwtUtils.createRefreshToken(authentication);

        logger.info("LOGIN " + authentication);

        Set<String> authorities = authentication.getAuthorities()
                .stream().map(GrantedAuthority::getAuthority)
                .collect(Collectors.toSet());

        if (mustChangePassword) {
            return new AuthResponse(
                    userDetails.getId(),
                    username,
                    "PASSWORD_CHANGE_REQUIRED",
                    accessToken,
                    refreshToken,
                    true,
                    authorities,
                    false
            );
        }


        AuthResponse authResponse = new AuthResponse(userDetails.getId(),
                username,
                "User loged succesfully",
                accessToken,
                refreshToken,
                false,
                authorities,
                true);


        return authResponse;
    }

    public AuthResponse refreshToken(String refreshToken) {

        try {
            // 1️⃣ Validar el token de refresh
            DecodedJWT decoded = jwtUtils.validateRefreshToken(refreshToken);


            if (!jwtUtils.isRefreshToken(decoded)) {
                throw new IllegalArgumentException("Invalid refresh token");
            }


            // 3️⃣ Obtener el usuario del token
            String username = decoded.getSubject();
            UserDetails userDetails = this.loadUserByUsername(username);

            logger.info("USUARIO USUARIO USUARIO  " + decoded.getSubject());

            // 4️⃣ Crear una nueva autenticación (sin contraseña)
            Authentication authentication = new UsernamePasswordAuthenticationToken(
                    userDetails,
                    null,
                    userDetails.getAuthorities()
            );

            // 5️⃣ Generar nuevo access token
            String newAccessToken = jwtUtils.createToken(authentication, false);

            Set<String> authorities = userDetails.getAuthorities()
                    .stream().map(GrantedAuthority::getAuthority).collect(Collectors.toSet());

            // 6️⃣ Devolver respuesta coherente
            return new AuthResponse(null,
                    username,
                    "Access token successfully renewed",
                    newAccessToken,
                    refreshToken, // El refresh no cambia
                    false,
                    authorities,
                    true
            );

        } catch (JWTVerificationException e) {
            throw new IllegalArgumentException("Invalid or expired refresh token", e);
        } catch (UsernameNotFoundException e) {
            logger.error("Usuario no encontrado al refrescar token: {}", e.getMessage());
            throw e;
        } catch (Exception e) {
            throw new RuntimeException("Error refreshing access token", e);
        }
    }*/

    /*public Authentication authenticate(String username, String password) {
        UserDetails userDetails = this.loadUserByUsername(username);

        if (!passwordEncoder.matches(password, userDetails.getPassword())) {
            throw new BadCredentialsException("Incorrect Password");
        }

        // Verifica si la cuenta está deshabilitada/bloqueada/expirada usando los métodos de UserDetails
        if (!userDetails.isEnabled() || !userDetails.isAccountNonLocked() || !userDetails.isAccountNonExpired() || !userDetails.isCredentialsNonExpired()) {
            throw new BadCredentialsException("User account is disabled, locked, or expired.");
        }

        return new UsernamePasswordAuthenticationToken(userDetails,
                null,
                userDetails.getAuthorities());
    }*/






}



  /* @Override
     public UserDetails loadUserByUsername(String username) {

         logger.error("USERNAME " + username);

         UserEntity userEntity = userRepository.findUserEntityByUsername(username)
                 .orElseThrow(() -> new UsernameNotFoundException("El usuario " + username + " no existe."));
         List<SimpleGrantedAuthority> authorityList = new ArrayList<>();

         userEntity.getRoles().forEach(role -> authorityList.add(new
                 SimpleGrantedAuthority("ROLE_"+ role.getRoleEnum().name())));

         userEntity.getRoles().stream().flatMap(role -> role.getPermissionSet().stream())
                 .forEach(permission -> authorityList.add(new SimpleGrantedAuthority(permission.getName())));

         return  new CustomUserDetails(
                 userEntity.getId(),
                 userEntity.getUsername(),
                 userEntity.getPassword(),
                 authorityList,
                 userEntity.isEnabled(),
                 userEntity.isAccountNonLocked(),
                 userEntity.isAccountNonExpired(),
                 userEntity.isCredentialNonExpired(),
                 userEntity.isMustChangePassword()
         );

     }
 */