package com.fundoo.authservice.dto;

import java.util.UUID;

public record UserAuthResponse(

        UUID id,

        String email,

        String password,

        String role

) {
}