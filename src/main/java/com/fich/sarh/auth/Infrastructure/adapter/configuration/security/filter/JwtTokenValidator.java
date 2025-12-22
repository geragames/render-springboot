package com.fich.sarh.auth.Infrastructure.adapter.configuration.security.filter;

import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fich.sarh.auth.Infrastructure.adapter.configuration.security.CustomUserDetails;
import com.fich.sarh.auth.Infrastructure.adapter.configuration.security.SecurityConfig;
import com.fich.sarh.auth.Infrastructure.adapter.configuration.security.jwt.JwtUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.Set;

@Component
public class JwtTokenValidator extends OncePerRequestFilter {

    Logger logger = LoggerFactory.getLogger(this.getClass());

    private final JwtUtils jwtUtils;


    private static final Set<String> ALLOWED_PATHS = Set.of(
            "/auth/change",
            "/auth/logout",
            "/auth/refresh"
    );

    public JwtTokenValidator(JwtUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }


    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {

        logger.error(">>> JWT FILTER EJECUTADO PARA {}", request.getRequestURI());

        String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return;
        }

        String token = authHeader.substring(7);

        if (!jwtUtils.isTokenValid(token)) {
            logger.warn("Token no válido o expirado para URI: {}", request.getRequestURI());
            // No se establece la autenticación, el SecurityConfig lo manejará.
            filterChain.doFilter(request, response);
            return;
        }

        try {

            // 3. Decodificar el JWT y construir CustomUserDetails
            DecodedJWT decodedJWT = jwtUtils.validateToken(token);

            // 4. Construir CustomUserDetails y el objeto Authentication
            CustomUserDetails userDetails = jwtUtils.buildUserDetails(decodedJWT);

            // El token de acceso solo debería llegar a rutas que requieren autenticación,
            // pero si la validación JWT es exitosa, se considera auténtico.
            Authentication authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

            // 5. Establecer la autenticación en el SecurityContext
            SecurityContextHolder.getContext().setAuthentication(authentication);

            logger.debug("Usuario autenticado: {}", userDetails.getUsername());

        } catch (JWTVerificationException e) {
            // Esto es redundante si se usa isTokenValid, pero se mantiene para robustez.
            logger.warn("Error al validar el token (aunque isTokenValid pasó): {}", e.getMessage());
            // No se establece la autenticación, se permite seguir a la cadena.
        }

        filterChain.doFilter(request, response);

    }


    private boolean isAllowPath(HttpServletRequest request) {

        String path = request.getRequestURI();

        logger.warn("EL PATH es :", path);

        return ALLOWED_PATHS.stream().anyMatch(path::contains);
    }

   /* @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request,
                                    @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {

         String jwtToken = request.getHeader(HttpHeaders.AUTHORIZATION);

                 if(jwtToken != null){
                    jwtToken = jwtToken.substring(7);

                   DecodedJWT decodeJWT = jwtUtils.validateToken(jwtToken);


                   String username = jwtUtils.extractUsername(decodeJWT);

                   String stringAuthorities = jwtUtils.getSpecificClaim(decodeJWT, "authorities").asString();

                   Collection<? extends GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList(stringAuthorities);


                   Claim mustChangeClain = decodeJWT.getClaim("mustChangePassword");
                   boolean mustChangePassword = mustChangeClain != null && mustChangeClain.asBoolean();

                   logger.debug("Usuario: {}, mustChangePassword: {}", username, mustChangePassword);


                   SecurityContext context = SecurityContextHolder.getContext();

                   Authentication authentication = new UsernamePasswordAuthenticationToken(username, null, authorities);

                   context.setAuthentication(authentication);

                   SecurityContextHolder.setContext(context);

                   request.setAttribute("mustChangePassword", mustChangePassword);
                 }

         filterChain.doFilter(request, response);
    }*/
}
