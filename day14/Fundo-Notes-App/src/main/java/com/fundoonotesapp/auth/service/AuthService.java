package com.fundoonotesapp.auth.service;

import com.fundoonotesapp.auth.dto.AuthResponse;
import com.fundoonotesapp.auth.jwt.JwtService;

import java.time.LocalDateTime;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import com.fundoonotesapp.auth.dto.ForgotPasswordRequest;
import com.fundoonotesapp.auth.dto.LoginRequest;
import com.fundoonotesapp.auth.dto.RegisterRequest;
import com.fundoonotesapp.auth.dto.ResetPasswordRequest;
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

@Service
@RequiredArgsConstructor
public class AuthService {

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
    public AuthResponse forgotPassword(ForgotPasswordRequest request) {

        userRepository.findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found")
                );

        // Later:
        // Generate reset token
        // Store token and expiry
        // Send reset email

        return new AuthResponse(
                "Password reset request accepted"
        );
    }


    // Reset Password
    public AuthResponse resetPassword(ResetPasswordRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new ResourceNotFoundException("User not found")
                );

        user.setPassword(
                passwordEncoder.encode(request.getNewPassword())
        );

        userRepository.save(user);

        return new AuthResponse("Password reset successfully");
    }
}