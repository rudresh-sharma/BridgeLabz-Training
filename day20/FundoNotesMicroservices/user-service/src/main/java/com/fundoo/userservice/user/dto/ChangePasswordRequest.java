package com.fundoo.userservice.user.dto;

import jakarta.validation.constraints.NotBlank;

public record ChangePasswordRequest(

        @NotBlank(message = "Encoded password is required")
        String encodedPassword

) {
}
