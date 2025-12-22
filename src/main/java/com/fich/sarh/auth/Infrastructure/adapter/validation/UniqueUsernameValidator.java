package com.fich.sarh.auth.Infrastructure.adapter.validation;

import com.fich.sarh.auth.Application.ports.output.persistence.UserRetrieveSpiPort;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UniqueUsernameValidator implements ConstraintValidator<UniqueUsername, String> {

   private final UserRetrieveSpiPort userRetrieveSpiPort;
    @Override
    public boolean isValid(String username, ConstraintValidatorContext context) {
        if(username == null || username.isBlank()){
            return  true;
        }
        return !userRetrieveSpiPort.existsUsername(username);
    }
}
