package com.fich.sarh.auth.Infrastructure.adapter.configuration.security.jwt;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fich.sarh.auth.Infrastructure.adapter.configuration.security.CustomUserDetails;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class JwtUtils {

    Logger logger = LoggerFactory.getLogger(JwtUtils.class);

    @Value("${jwt.secret.key}")
    private String privateKey;

    @Value("${jwt.user.generator}")
    private String userGenerator;

    @Value("${jwt.time.expiration}")
    private Long expiration;
    @Value("${jwt.time.refreshExpiration}")
    private Long expiryDate;


    public CustomUserDetails buildUserDetails(DecodedJWT jwt){

        Long userId = jwt.getClaim("userId").asLong();

        String usermame = jwt.getSubject();

        String authoritiesStr = jwt.getClaim("authorities").asString();

        Collection<GrantedAuthority> authorities = AuthorityUtils.commaSeparatedStringToAuthorityList(authoritiesStr);

        return  new CustomUserDetails(
                userId,
                usermame,
                null,
                authorities,
                true,
                true,
                true,
                true,
                false
        );
    }

    private Algorithm getAlgoritm(){
        return Algorithm.HMAC256(this.privateKey);
    }

    public String createToken(Authentication authentication, boolean mustChangePassword) {

//        String username = extractUsernameFromAuth(authentication);

        CustomUserDetails user = (CustomUserDetails)authentication.getPrincipal();

        String authorities = authentication.getAuthorities()
                .stream().map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(",")); // READ,WRITE,
        Date now = new Date();
        Date expiresAt = new Date(now.getTime() + expiration);

        return JWT.create()
                .withIssuer(userGenerator)
                .withSubject(user.getUsername())
                .withClaim("userId", user.getId())
                .withClaim("authorities", authorities)
                .withClaim("type", "access")
                .withClaim("mustChangePassword", mustChangePassword)
                .withIssuedAt(now)
                .withExpiresAt(expiresAt)
                .withJWTId(UUID.randomUUID().toString())
                .sign(getAlgoritm());


    }

    public DecodedJWT validateToken(String token) {


        try {


            JWTVerifier verifier = JWT.require(getAlgoritm())
                    .withIssuer(userGenerator)
                    .withClaim("type", "access")
                    .build();

            DecodedJWT decodedJWT = verifier.verify(token);

            logger.info("DATOS VALIDOS " + decodedJWT.getSubject());

            return decodedJWT;

        } catch (JWTVerificationException e) {
            logger.error("Access token inválido o expirado: {}", e.getMessage());
            throw new JWTVerificationException("Token Invalid, not Authorized");
        }
    }

    /**
     * Comprueba si el token es estructuralmente válido (se puede decodificar y firmar).
     * Usado por el filtro para un manejo más limpio antes de la validación completa.
     * @param token El token JWT a validar.
     * @return true si es válido, false en caso contrario.
     */
    public boolean isTokenValid(String token) {
        try {
            // Intenta decodificar y verificar la firma y el emisor (issuer) y tipo 'access'
            JWTVerifier verifier = JWT.require(getAlgoritm())
                    .withIssuer(userGenerator)
                    .withClaim("type", "access")
                    .build();
            verifier.verify(token);
            return true;
        } catch (JWTVerificationException e) {
            // La verificación falló por cualquier motivo (expiración, firma inválida, etc.)
            logger.debug("Token no válido o expirado: {}", e.getMessage());
            return false;
        }
    }

    public String extractUsername(DecodedJWT decodedJWT) {

        return decodedJWT.getSubject();
    }

    public Collection<String> extractAuthorities(DecodedJWT decodedJWT) {

        Claim authoritiesClaim = decodedJWT.getClaim("authorities");

        if(authoritiesClaim == null){
            return Collections.emptyList();
        }

        String authoritiesStr = authoritiesClaim.asString();

        return AuthorityUtils.commaSeparatedStringToAuthorityList(authoritiesStr)
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());
    }

    public Claim getSpecificClaim(DecodedJWT decodedJWT, String claimName) {
        return decodedJWT.getClaim(claimName);
    }

    public Map<String, Claim> getAllClaims(DecodedJWT decodedJWT) {
        return decodedJWT.getClaims();
    }


    public String createRefreshToken(Authentication authentication) {
     //  Algorithm algorithm = Algorithm.HMAC256(this.privateKey);

        String username = extractUsernameFromAuth(authentication);
        Date now = new Date();
        Date expiresAt = new Date(now.getTime() + expiryDate);

        return JWT.create()
                .withIssuer(userGenerator)
                .withSubject(username)
                .withClaim("type", "refresh")
                .withIssuedAt(now)
                .withExpiresAt(expiresAt)
                .withJWTId(UUID.randomUUID().toString())
               // .withNotBefore(now)
                .sign(getAlgoritm());
    }

    public DecodedJWT validateRefreshToken(String refreshToken){
        try {

            JWTVerifier verifier = JWT.require(getAlgoritm())
                    .withIssuer(userGenerator)

                    .withClaim("type", "refresh")
                    .build();
            DecodedJWT decodedJWT = verifier.verify(refreshToken);
            logger.debug("Refresh token válido para usuario: {}", decodedJWT.getSubject());
            return  decodedJWT;
        }catch (JWTVerificationException e){
           logger.error("Refresh token inválido o expirado: {}", e.getMessage());
           throw new JWTVerificationException("Refresh token invalido o expirado");
        }
    }
    public boolean isRefreshToken(DecodedJWT decodedJWT){
        if(decodedJWT == null) return false;
        Claim typeClaim = decodedJWT.getClaim("type");
        return "refresh".equals(typeClaim.asString());
    }

    public boolean isAccessToken(DecodedJWT decodedJWT){
        if(decodedJWT == null) return false;
        Claim typeClaim = decodedJWT.getClaim("type");
        return  "access".equals(typeClaim.asString());
    }

    /**
     * Método auxiliar seguro para obtener el username del Authentication.
     */
    private String extractUsernameFromAuth(Authentication authentication) {
        Object principal = authentication.getPrincipal();
        if (principal instanceof UserDetails userDetails) {
            return userDetails.getUsername();
        }
        if (principal instanceof String username) {
            return username;
        }

        throw new IllegalArgumentException("No se pudo extraer el username del Authentication");

    }




}
