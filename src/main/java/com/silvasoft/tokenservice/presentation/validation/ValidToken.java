package com.silvasoft.tokenservice.presentation.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = CustomTokenValidator.class)
public @interface ValidToken {
    String message() default " invalid input value";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
