package com.fundoonotesapp.auth.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class AuthResponse {
	@NotBlank
	@Size(max = 300, message = "Auth Response Message")
    private String message;
}