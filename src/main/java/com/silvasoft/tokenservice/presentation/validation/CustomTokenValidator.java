package com.silvasoft.tokenservice.presentation.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.stereotype.Component;

@Component
public class CustomTokenValidator implements ConstraintValidator<ValidToken, String> {
    public static final int MAX_SIZE = 256;

    @Override
    public boolean isValid(final String value, final ConstraintValidatorContext context) {
        return isSizeValid(value);
    }

    protected boolean isSizeValid(final String token) {
        return token != null && token.length() < MAX_SIZE;
    }
}
