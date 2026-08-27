package com.fundoo.authservice.dto;

public record RefreshTokenResponse(

        String accessToken,

        String refreshToken,

        String tokenType

) {
}