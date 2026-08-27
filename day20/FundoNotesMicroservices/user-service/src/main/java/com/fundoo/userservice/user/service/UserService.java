package com.fundoo.userservice.user.service;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.fundoo.userservice.user.dto.ChangePasswordRequest;
import com.fundoo.userservice.user.dto.CreateUserRequest;
import com.fundoo.userservice.user.dto.UpdateUserRequest;
import com.fundoo.userservice.user.dto.UserAuthResponse;
import com.fundoo.userservice.user.dto.UserResponse;
import com.fundoo.userservice.user.entity.User;
import com.fundoo.userservice.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    
    
    public UserResponse createUser(CreateUserRequest request) {

        if (userRepository.existsByEmail(request.email())) {
            throw new RuntimeException("Email already exists");
        }

        User user = User.builder()
                .name(request.name())
                .email(request.email())
                .password(request.password())
                .provider(request.provider())
                .role(request.role())
                .build();

        User savedUser = userRepository.save(user);

        return mapToResponse(savedUser);
    }

    public UserResponse getUserById(UUID id) {

        User user = userRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        return mapToResponse(user);
    }
    
    
    public UserAuthResponse getUserForAuth(String email) {

    	User user = userRepository.findByEmailIgnoreCase(email)
    	        .orElseThrow(() ->
    	                new ResponseStatusException(HttpStatus.NOT_FOUND, "User not found"));

        return new UserAuthResponse(
                user.getId(),
                user.getEmail(),
                user.getPassword(),
                user.getRole(),
                user.getFailedAttempts(),
                user.getAccountLockedUntil()
        );
    }
    
    
    public UserAuthResponse getUserForAuthById(UUID userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        return new UserAuthResponse(
                user.getId(),
                user.getEmail(),
                user.getPassword(),
                user.getRole(),
                user.getFailedAttempts(),
                user.getAccountLockedUntil()
        );
    }


    // Called by auth-service on every failed login attempt
    public void incrementFailedAttempts(UUID userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        int attempts = user.getFailedAttempts() + 1;
        user.setFailedAttempts(attempts);

        if (attempts >= 5) {
            user.setAccountLockedUntil(
                    LocalDateTime.now().plusMinutes(15)
            );
        }

        userRepository.save(user);
    }


    // Called by auth-service on successful login
    public void resetFailedAttempts(UUID userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        user.setFailedAttempts(0);
        user.setAccountLockedUntil(null);

        userRepository.save(user);
    }

    public UserResponse getUserByEmail(String email) {

        User user = userRepository.findByEmailIgnoreCase(email)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        return mapToResponse(user);
    }


    public UserResponse updateUser(
            String email,
            UpdateUserRequest request
    ) {

        User user = userRepository.findByEmailIgnoreCase(email)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        user.setName(request.name());

        User updatedUser = userRepository.save(user);

        return mapToResponse(updatedUser);
    }


    public void updatePassword(
            UUID userId,
            ChangePasswordRequest request
    ) {

        User user = userRepository.findById(userId)
                .orElseThrow(() ->
                        new RuntimeException("User not found"));

        user.setPassword(request.encodedPassword());

        userRepository.save(user);
    }

    private UserResponse mapToResponse(User user) {

        return new UserResponse(
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getProvider(),
                user.getRole()
        );
    }
}