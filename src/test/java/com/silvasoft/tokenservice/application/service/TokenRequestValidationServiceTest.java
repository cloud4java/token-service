package com.silvasoft.tokenservice.application.service;

import com.silvasoft.tokenservice.domain.TokenValidationException;
import com.silvasoft.tokenservice.service.TokenValidationService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class TokenRequestValidationServiceTest {
    public static final String TOKEN_VALID = "eyJhbGciOiJIUzI1NiJ9.eyJSb2xlIjoiQWRtaW4iLCJTZWVkIjoiNzg0MSIsIk5hbWUiOiJUb25pbmhvIEFyYXVqbyJ9.QY05sIjtrcJnP533kQNk8QXcaleJ1Q01jWY_ZzIZuAg";
    public static final String TOKEN_INVALID = "eyJhbGciOiJzI1NiJ9.dfsdfsfryJSr2xrIjoiQWRtaW4iLCJTZrkIjoiNzg0MSIsIk5hbrUiOiJUb25pbmhvIEFyYXVqbyJ9.QY05fsdfsIjtrcJnP533kQNk8QXcaleJ1Q01jWY_ZzIZuAg";
    public static final String TOKEN_NAME_HAS_NUMBER = "eyJhbGciOiJIUzI1NiJ9.eyJSb2xlIjoiRXh0ZXJuYWwiLCJTZWVkIjoiODgwMzciLCJOYW1lIjoiTTRyaWEgT2xpdmlhIn0.6YD73XWZYQSSMDf6H0i3-kylz1-TY_Yt6h1cV2Ku-Qs";
    public static final String TOKEN_MORE_THAN_TREE_CLAIMS = "eyJhbGciOiJIUzI1NiJ9.eyJSb2xlIjoiTWVtYmVyIiwiT3JnIjoiQlIiLCJTZWVkIjoiMTQ2MjciLCJOYW1lIjoiVmFsZGlyIEFyYW5oYSJ9.cmrXV_Flm5mfdpfNUVopY_I2zeJUy4EZ4i3Fea98zvY";

    private final TokenValidationService tokenValidationService = new TokenValidationService();

    @Test
    void tokenNumberOfClaimsValidTest() {
        Assertions.assertTrue(tokenValidationService.isOnlyThreeClaimsPresent(TOKEN_MORE_THAN_TREE_CLAIMS));
    }

    @Test
    void tokenClaimHasNumber_shouldThrowExceptionTest() {
        Assertions.assertThrows(TokenValidationException.class, () -> tokenValidationService.isNoNumberPresentInName(TOKEN_NAME_HAS_NUMBER));
    }

    @Test
    void tokenClaimHasNoNumber_shouldNotThrowExceptionTest() {
        Assertions.assertDoesNotThrow(() -> tokenValidationService.isNoNumberPresentInName(TOKEN_VALID));
    }
}