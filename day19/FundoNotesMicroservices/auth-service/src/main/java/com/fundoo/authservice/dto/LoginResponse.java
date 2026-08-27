package com.fundoo.authservice.dto;

public record LoginResponse(

        String accessToken,

        String refreshToken,

        String tokenType

) {
}