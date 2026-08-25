package com.fundoo.authservice.service;

import org.springframework.security.crypto.password.PasswordEncoder;

import org.springframework.stereotype.Service;

import com.fundoo.authservice.client.UserClient;
import com.fundoo.authservice.dto.CreateUserRequest;
import com.fundoo.authservice.dto.LoginRequest;
import com.fundoo.authservice.dto.LoginResponse;
import com.fundoo.authservice.dto.RefreshTokenRequest;
import com.fundoo.authservice.dto.RefreshTokenResponse;
import com.fundoo.authservice.dto.RegisterRequest;
import com.fundoo.authservice.dto.UserAuthResponse;
import com.fundoo.authservice.dto.UserResponse;
import com.fundoo.authservice.entity.RefreshToken;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserClient userClient;
    private final PasswordEncoder passwordEncoder;
    private final RefreshTokenService refreshTokenService;
    private final JwtService jwtService;
    public UserResponse register(RegisterRequest request) {

        String encodedPassword =
                passwordEncoder.encode(request.password());

        CreateUserRequest createUserRequest =
                new CreateUserRequest(
                        request.name(),
                        request.email().toLowerCase(),
                        encodedPassword,
                        "LOCAL",
                        "USER"
                );

        return userClient.createUser(createUserRequest);
    }
    
    public LoginResponse login(LoginRequest request) {

        UserAuthResponse user =
                userClient.getUserForAuth(request.email());

        boolean passwordMatches =
                passwordEncoder.matches(
                        request.password(),
                        user.password()
                );

        if (!passwordMatches) {
            throw new RuntimeException("Invalid email or password");
        }

        String accessToken = jwtService.generateToken(
                user.id(),
                user.email(),
                user.role()
        );

        RefreshToken refreshToken =
                refreshTokenService.createRefreshToken(user.id());

        return new LoginResponse(
                accessToken,
                refreshToken.getToken(),
                "Bearer"
        );
    }
    
    public RefreshTokenResponse refresh(
            RefreshTokenRequest request
    ) {

        RefreshToken oldRefreshToken =
                refreshTokenService.verifyRefreshToken(
                        request.refreshToken()
                );

        UserAuthResponse user =
                userClient.getUserForAuthById(
                        oldRefreshToken.getUserId()
                );

        String accessToken = jwtService.generateToken(
                user.id(),
                user.email(),
                user.role()
        );

        RefreshToken newRefreshToken =
                refreshTokenService.rotateRefreshToken(
                        oldRefreshToken
                );

        return new RefreshTokenResponse(
                accessToken,
                newRefreshToken.getToken(),
                "Bearer"
        );
    }
    
    
    public void logout(RefreshTokenRequest request) {

        refreshTokenService.revokeRefreshToken(
                request.refreshToken()
        );
    }
}