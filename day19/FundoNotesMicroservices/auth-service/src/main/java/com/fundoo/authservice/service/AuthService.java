package com.fundoo.authservice.service;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fundoo.authservice.client.UserClient;
import com.fundoo.authservice.dto.ChangePasswordRequest;
import com.fundoo.authservice.dto.CreateUserRequest;
import com.fundoo.authservice.dto.ForgotPasswordRequest;
import com.fundoo.authservice.dto.LoginRequest;
import com.fundoo.authservice.dto.LoginResponse;
import com.fundoo.authservice.dto.RefreshTokenRequest;
import com.fundoo.authservice.dto.RefreshTokenResponse;
import com.fundoo.authservice.dto.RegisterRequest;
import com.fundoo.authservice.dto.ResetPasswordRequest;
import com.fundoo.authservice.dto.UserAuthResponse;
import com.fundoo.authservice.dto.UserResponse;
import com.fundoo.authservice.entity.PasswordResetToken;
import com.fundoo.authservice.entity.RefreshToken;
import com.fundoo.authservice.repository.PasswordResetTokenRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final UserClient userClient;
    private final PasswordEncoder passwordEncoder;
    private final RefreshTokenService refreshTokenService;
    private final JwtService jwtService;
    private final PasswordResetTokenRepository passwordResetTokenRepository;

    // Token valid for 15 minutes (matching monolith behaviour)
    private static final long RESET_TOKEN_EXPIRY_MINUTES = 15;

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

        // Check if the account is currently locked
        if (user.accountLockedUntil() != null
                && user.accountLockedUntil().isAfter(LocalDateTime.now())) {

            long secondsRemaining = java.time.Duration.between(
                    LocalDateTime.now(), user.accountLockedUntil()
            ).getSeconds();

            throw new RuntimeException(
                    "Account is locked. Try again in "
                            + (secondsRemaining / 60 + 1) + " minute(s)."
            );
        }

        boolean passwordMatches =
                passwordEncoder.matches(
                        request.password(),
                        user.password()
                );

        if (!passwordMatches) {
            // Increment failed attempts (locks at 5)
            userClient.incrementFailedAttempts(user.id());
            throw new RuntimeException("Invalid email or password");
        }

        // Successful login — reset counter
        userClient.resetFailedAttempts(user.id());

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


    @Transactional
    public String forgotPassword(ForgotPasswordRequest request) {

        // Verify user exists — throws if not found
        UserAuthResponse user =
                userClient.getUserForAuth(request.email());

        // Remove any existing reset token for this user
        passwordResetTokenRepository.deleteByUserId(user.id());

        // Generate a new reset token
        PasswordResetToken resetToken = PasswordResetToken.builder()
                .id(UUID.randomUUID())
                .token(UUID.randomUUID().toString())
                .userId(user.id())
                .expiresAt(
                        LocalDateTime.now()
                                .plusMinutes(RESET_TOKEN_EXPIRY_MINUTES)
                )
                .build();

        passwordResetTokenRepository.save(resetToken);

        // In production: email the token to the user
        // For now: return the token directly so it can be tested
        return resetToken.getToken();
    }


    @Transactional
    public void resetPassword(ResetPasswordRequest request) {

        PasswordResetToken resetToken =
                passwordResetTokenRepository.findByToken(request.token())
                        .orElseThrow(() ->
                                new RuntimeException("Invalid or expired reset token"));

        if (resetToken.isExpired()) {
            passwordResetTokenRepository.delete(resetToken);
            throw new RuntimeException("Reset token has expired. Please request a new one.");
        }

        String encodedPassword =
                passwordEncoder.encode(request.newPassword());

        userClient.updatePassword(
                resetToken.getUserId(),
                new ChangePasswordRequest(encodedPassword)
        );

        passwordResetTokenRepository.delete(resetToken);
    }
}