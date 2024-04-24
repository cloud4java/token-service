package com.silvasoft.tokenservice.presentation.dto;

import com.silvasoft.tokenservice.domain.TokenDomain;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.validation.annotation.Validated;

@Validated
public class TokenRequest {

    @Size(max = 255)
    @NotBlank
    private String value;

    @Size(max = 255)
    @NotBlank
    private String signature;

    public TokenRequest(final String value, final String signature) {
        this.value = value;
        this.signature = signature;
    }

    public String getValue() {
        return value;
    }

    public void setValue(final String value) {
        this.value = value;
    }

    public String getSignature() {
        return signature;
    }

    public void setSignature(final String signature) {
        this.signature = signature;
    }

    public TokenDomain tokenDomain(){
        return new TokenDomain(this.value, this.signature);
    }
}
