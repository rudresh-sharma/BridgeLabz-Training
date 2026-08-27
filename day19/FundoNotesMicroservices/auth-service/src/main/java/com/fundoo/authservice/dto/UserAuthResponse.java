package com.fundoo.authservice.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record UserAuthResponse(

        UUID id,

        String email,

        String password,

        String role,

        int failedAttempts,

        LocalDateTime accountLockedUntil

) {
}