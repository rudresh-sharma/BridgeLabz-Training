package com.fundoonotesapp.auth.service;

import com.fundoonotesapp.auth.dto.AuthResponse;
import com.fundoonotesapp.auth.dto.ForgotPasswordRequest;
import com.fundoonotesapp.auth.dto.LoginRequest;
import com.fundoonotesapp.auth.dto.RegisterRequest;
import com.fundoonotesapp.auth.dto.ResetPasswordRequest;
import com.fundoonotesapp.exception.auth.*;
import com.fundoonotesapp.exception.common.*;
import com.fundoonotesapp.exception.user.*;
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

        return new AuthResponse("User registered successfully");
    }


    // Login User
    public AuthResponse login(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() ->
                        new InvalidCredentialsException(
                                "Invalid email or password"
                        )
                );

        // Check password
        if (!passwordEncoder.matches(
                request.getPassword(),
                user.getPassword())) {

            throw new InvalidCredentialsException(
                    "Invalid email or password"
            );
        }

        return new AuthResponse("Login successful");
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