package com.silvasoft.tokenservice.domain;


public class TokenDomain {
    public TokenDomain(final String value, final String signature) {
        this.value = value;
        this.signature = signature;
    }

    private String value;

    private String signature;

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
    
}