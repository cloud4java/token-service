package com.silvasoft.tokenservice.service;

import com.silvasoft.tokenservice.common.NumberUtils;
import com.silvasoft.tokenservice.domain.JwtClaim;
import com.silvasoft.tokenservice.domain.TokenClaimException;
import com.silvasoft.tokenservice.domain.TokenDomain;
import org.springframework.stereotype.Service;

@Service
public class TokenValidationService {

    public static final int MAX_ALLOWED_CLAIMS = 3;

    public boolean isValidToken(final TokenDomain tokenDomain) throws TokenClaimException {
        return isOnlyThreeClaimsPresent(tokenDomain.getValue()) &&
                isNoNumberPresentInName(tokenDomain) &&
                isSeedPrimeNumber(tokenDomain);
    }

    public boolean isOnlyThreeClaimsPresent(final String token) {
        return token.split("\\.").length == MAX_ALLOWED_CLAIMS;
    }

    public boolean isNoNumberPresentInName(final TokenDomain token) throws TokenClaimException {
        final JwtClaim claim = JwtClaim.of(token.getValue(), token.getSignature());

        if (claim.getName().matches(".*\\d.*")) {
            throw new TokenClaimException("TokenDomain claim name must not have number");
        }
        return true;
    }

    public boolean isSeedPrimeNumber(final TokenDomain tokenDomain) {
        final JwtClaim claim = JwtClaim.of(tokenDomain.getValue(), tokenDomain.getSignature());

        return NumberUtils.isPrime(claim.getSeed());
    }
}
