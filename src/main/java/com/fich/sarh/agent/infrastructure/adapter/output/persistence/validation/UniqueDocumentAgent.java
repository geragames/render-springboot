package com.fich.sarh.agent.infrastructure.adapter.output.persistence.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = UniqueDocumentAgentValidator.class)
public @interface UniqueDocumentAgent {

    String message() default  "El número de documento ingresado ya se encuentra registrado";
    Class<?>[] groups() default  {};

    Class<? extends Payload>[] payload() default {};

}
