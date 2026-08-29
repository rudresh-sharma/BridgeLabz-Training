package com.fundoo.authservice.dto;

import com.fundoo.authservice.entity.AuthProvider;

public record OAuthUserRequest(
        String name,
        String email,
        AuthProvider provider,   // "GOOGLE"
        String profilePicUrl
) {
}