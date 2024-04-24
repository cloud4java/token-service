package com.silvasoft.tokenservice.service;

import com.silvasoft.tokenservice.domain.JwtClaim;
import com.silvasoft.tokenservice.domain.TokenClaimException;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

@Service
public class TokenValidationService {

    public static final int MAX_ALLOWED_CLAIMS = 3;

    public boolean isValidToken(final String value) throws TokenClaimException {
        return isOnlyThreeClaimsPresent(value) &&
                isNoNumberPresentInName(value) &&
                isSeedPrimeNumber(value);
    }

    public boolean isOnlyThreeClaimsPresent(final String token) {
        return token.split("\\.").length == MAX_ALLOWED_CLAIMS;
    }

    public boolean isNoNumberPresentInName(final String token) throws TokenClaimException {
        if (isEmpty(token)) return false;

        final JwtClaim claim = JwtClaim.of(token, System.getenv("TOKEN_SECRET"));

        if (claim.getName().matches(".*\\d.*")) {
            throw new TokenClaimException("TokenRequest claim name must not have number");
        }
        return true;
    }

    public boolean isSeedPrimeNumber(final String token) {
        final JwtClaim claim = JwtClaim.of(token, System.getenv("TOKEN_SECRET"));

        return isPrime(claim.getSeed());
    }

    private static boolean isEmpty(final String token) {
        return ObjectUtils.isEmpty(token);
    }

    private boolean isPrime(int num) {
        if (num < 2) return false;
        if (num % 2 == 0 && num > 2) return false;
        int i = 3;
        while (i * i <= num) {
            if (num % i == 0 && num != i) return false;
            i += 2;
        }
        return true;
    }
}
