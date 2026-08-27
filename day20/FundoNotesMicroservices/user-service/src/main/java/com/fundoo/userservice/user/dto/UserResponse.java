package com.fundoo.userservice.user.dto;

import java.util.UUID;

import com.fundoo.userservice.user.entity.AuthProvider;
import com.fundoo.userservice.user.entity.Role;

public record UserResponse(

        UUID id,

        String name,

        String email,

        AuthProvider provider,

        Role role
) {
}