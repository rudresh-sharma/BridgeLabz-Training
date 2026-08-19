package com.fundoonotesapp.auth.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class AuthResponse {

    private final String message;
    private final String token;

    public AuthResponse(String message) {
        this.message = message;
        this.token = null;
    }

    public AuthResponse(String message, String token) {
        this.message = message;
        this.token = token;
    }
}