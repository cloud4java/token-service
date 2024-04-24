package com.silvasoft.tokenservice.domain;

public class TokenValidationException extends RuntimeException {
    public TokenValidationException(final String message, final Exception ex) {
        super(message, ex);
    }

    public TokenValidationException(final TokenClaimException e) {
        super(e);
    }
}
