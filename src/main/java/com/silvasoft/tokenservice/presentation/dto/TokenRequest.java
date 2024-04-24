package com.silvasoft.tokenservice.presentation.dto;

import jakarta.validation.constraints.Size;
import org.springframework.validation.annotation.Validated;

@Validated
public class TokenRequest {

    @Size(max = 255)
    private String value;

    public String getValue() {
        return value;
    }

    public void setValue(final String value) {
        this.value = value;
    }
}
