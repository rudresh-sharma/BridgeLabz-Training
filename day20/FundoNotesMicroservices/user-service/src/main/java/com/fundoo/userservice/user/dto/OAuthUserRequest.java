package com.fundoo.userservice.user.dto;

import com.fundoo.userservice.user.entity.AuthProvider;

public record OAuthUserRequest(
        String name,
        String email,
        AuthProvider provider,   // "GOOGLE"
        String profilePicUrl
) {
}