package com.silvasoft.tokenservice.service;

import com.silvasoft.tokenservice.common.JwtUtil;
import com.silvasoft.tokenservice.domain.TokenClaimException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TokenRequestValidationServiceTest {
    public static final String SECRET = "de90b0b14b014073a76b61340c39da0cde90b0b14b014073a76b61340c39da0c";
    public static final String TOKEN_MORE_THAN_TREE_CLAIMS = "eyJhbGciOiJIUzI1NiJ9.eyJSb2xlIjoiTWVtYmVyIiwiT3JnIjoiQlIiLCJTZWVkIjoiMTQ2MjciLCJOYW1lIjoiVmFsZGlyIEFyYW5oYSJ9.cmrXV_Flm5mfdpfNUVopY_I2zeJUy4EZ4i3Fea98zvY";

    private final TokenValidationService tokenValidationService = new TokenValidationService();

    @Test
    void tokenNumberOfClaimsValidTest() {
        Assertions.assertTrue(tokenValidationService.isOnlyThreeClaimsPresent(TOKEN_MORE_THAN_TREE_CLAIMS));
    }

    @Test
    void tokenClaimHasNumber_shouldThrowExceptionTest() {
        Assertions.assertThrows(TokenClaimException.class, () -> tokenValidationService.isNoNumberPresentInName(JwtUtil.generateTokenDomain("user44", SECRET)));
    }

    @Test
    void tokenClaimHasNoNumber_shouldNotThrowExceptionTest() {
        Assertions.assertDoesNotThrow(() -> tokenValidationService.isNoNumberPresentInName(JwtUtil.generateTokenDomain("AnyUserName", SECRET)));
    }
}