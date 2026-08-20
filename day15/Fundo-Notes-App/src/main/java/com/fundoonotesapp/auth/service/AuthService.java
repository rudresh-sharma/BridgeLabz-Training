package com.fundoonotesapp.auth.service;

import com.fundoonotesapp.auth.dto.AuthResponse;
import com.fundoonotesapp.auth.jwt.JwtService;
import com.fundoonotesapp.auth.repository.PasswordResetTokenRepository;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import com.fundoonotesapp.auth.dto.ForgotPasswordRequest;
import com.fundoonotesapp.auth.dto.LoginRequest;
import com.fundoonotesapp.auth.dto.RegisterRequest;
import com.fundoonotesapp.auth.dto.ResetPasswordRequest;
import com.fundoonotesapp.auth.entity.PasswordResetToken;
import com.fundoonotesapp.exception.auth.*;
import com.fundoonotesapp.exception.common.*;
import com.fundoonotesapp.exception.user.*;
import com.fundoonotesapp.security.CustomUserDetails;
import com.fundoonotesapp.user.entity.AuthProvider;
import com.fundoonotesapp.user.entity.User;
import com.fundoonotesapp.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class AuthService {
	
	private final PasswordResetTokenRepository passwordResetTokenRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private static final int MAX_FAILED_ATTEMPTS = 5;
    private static final int LOCK_DURATION_MINUTES = 15;
    // Register User
    public AuthResponse register(RegisterRequest request) {

        // Check whether email already exists
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new UserAlreadyExistsException(
                    "Email already registered"
            );
        }

        // Create User entity
        User user = User.builder()
                .name(request.getName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .provider(AuthProvider.LOCAL)
                .build();

        // Save user
        userRepository.save(user);
        
        String token = jwtService.generateToken(user);
        

        return new AuthResponse("User registered successfully, Kindly Login", token);
    }


    // Login User
    public AuthResponse login(LoginRequest request) {

        // Needed for account lock logic before authentication
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new UnauthorizedException("Invalid email or password")
                );
        
        // Check whether account is locked
        if (user.getAccountLockedUntil() != null
                && user.getAccountLockedUntil().isAfter(LocalDateTime.now())) {

            throw new UnauthorizedException(
                    "Account is locked. Please try again later."
            );
        }

        // Unlock if lock time expired
        if (user.getAccountLockedUntil() != null
                && !user.getAccountLockedUntil().isAfter(LocalDateTime.now())) {

            user.setAccountLockedUntil(null);
            user.setFailedAttempts(0);
            userRepository.save(user);
        }

        try {

            Authentication authentication =
                    authenticationManager.authenticate(
                            new UsernamePasswordAuthenticationToken(
                                    request.getEmail(),
                                    request.getPassword()
                            )
                    );

            // Get authenticated principal
            CustomUserDetails userDetails =
                    (CustomUserDetails) authentication.getPrincipal();

            // Get the authenticated User
            User authenticatedUser = userDetails.getUser();

            // Successful login → reset attempts
            authenticatedUser.setFailedAttempts(0);
            authenticatedUser.setAccountLockedUntil(null);

            userRepository.save(authenticatedUser);

            String token = jwtService.generateToken(authenticatedUser);

            return new AuthResponse(
                    "Login successful",
                    token
            );

        } catch (AuthenticationException exception) {

            // Authentication failed
            user.setFailedAttempts(user.getFailedAttempts() + 1);

            if (user.getFailedAttempts() >= MAX_FAILED_ATTEMPTS) {

                user.setAccountLockedUntil(
                        LocalDateTime.now()
                                .plusMinutes(LOCK_DURATION_MINUTES)
                );

                userRepository.save(user);

                throw new UnauthorizedException(
                        "Too many failed login attempts. "
                                + "Account locked for 15 minutes."
                );
            }

            userRepository.save(user);

            throw new UnauthorizedException(
                    "Invalid email or password"
            );
        }
    }
    // Forgot Password
    @Transactional
    public AuthResponse forgotPassword(ForgotPasswordRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found")
                );

        // Delete previous reset token, if it exists
        passwordResetTokenRepository.deleteByUser(user);

        // Generate a new random token
        String token = UUID.randomUUID().toString();

        // Create reset token
        PasswordResetToken passwordResetToken =
                PasswordResetToken.builder()
                        .token(token)
                        .expiresAt(
                                LocalDateTime.now().plusMinutes(15)
                        )
                        .user(user)
                        .build();

        // Save token
        passwordResetTokenRepository.save(passwordResetToken);

        // For now, return the token so we can test with Postman.
        // Later, this token should be sent through email instead.
        return new AuthResponse(
                "Password reset token generated",
                token
        );
    }


    // Reset Password
    public AuthResponse resetPassword(ResetPasswordRequest request) {

        // 1. Find reset token
        PasswordResetToken passwordResetToken =
                passwordResetTokenRepository
                        .findByToken(request.getToken())
                        .orElseThrow(() ->
                                new ResourceNotFoundException(
                                        "Invalid password reset token"
                                )
                        );

        // 2. Check whether token has expired
        if (passwordResetToken.getExpiresAt()
                .isBefore(LocalDateTime.now())) {

            // Remove expired token
            passwordResetTokenRepository.delete(passwordResetToken);

            throw new UnauthorizedException(
                    "Password reset token has expired"
            );
        }

        // 3. Get the user associated with this token
        User user = passwordResetToken.getUser();

        // 4. Encode and update password
        user.setPassword(
                passwordEncoder.encode(request.getNewPassword())
        );

        userRepository.save(user);

        // 5. Delete token so it cannot be reused
        passwordResetTokenRepository.delete(passwordResetToken);

        return new AuthResponse(
                "Password reset successfully"
        );
    }
    
}