package com.fundoo.userservice.user.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import com.fundoo.userservice.user.entity.Role;

public record UserAuthResponse(

        UUID id,

        String email,

        String password,

        Role role,

        int failedAttempts,

        LocalDateTime accountLockedUntil

) {
}