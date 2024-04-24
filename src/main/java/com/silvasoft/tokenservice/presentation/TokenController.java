package com.silvasoft.tokenservice.presentation;

import com.silvasoft.tokenservice.domain.TokenClaimException;
import com.silvasoft.tokenservice.domain.TokenValidationException;
import com.silvasoft.tokenservice.presentation.dto.TokenRequest;
import com.silvasoft.tokenservice.service.TokenValidationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/tokens")
public class TokenController {

    @Autowired
    private TokenValidationService tokenValidationService;

    @PostMapping(produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Boolean> validate(@Valid @RequestBody TokenRequest tokenRequest) {
        try {
            tokenValidationService.isValidToken(tokenRequest.getValue());
        } catch (TokenClaimException e) {
            throw new TokenValidationException(e.getMessage(), e);
        }

        return ResponseEntity.ok().body(false);
    }
}
