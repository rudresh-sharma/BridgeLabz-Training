package com.fundoo.authservice.client;

import com.fundoo.authservice.dto.ChangePasswordRequest;
import com.fundoo.authservice.dto.CreateUserRequest;
import com.fundoo.authservice.dto.UserAuthResponse;
import com.fundoo.authservice.dto.UserResponse;

import java.util.UUID;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "user-service")
public interface UserClient {

    @GetMapping("/users/email/{email}")
    UserResponse getUserByEmail(
            @PathVariable String email
    );

    @PostMapping("/users")
    UserResponse createUser(
            @RequestBody CreateUserRequest request
    );

    @GetMapping("/users/auth/email/{email}")
    UserAuthResponse getUserForAuth(
            @PathVariable String email
    );

    @GetMapping("/users/auth/{userId}")
    UserAuthResponse getUserForAuthById(
            @PathVariable("userId") UUID userId
    );

    @PatchMapping("/users/{userId}/password")
    void updatePassword(
            @PathVariable("userId") UUID userId,
            @RequestBody ChangePasswordRequest request
    );


    @PostMapping("/users/{userId}/lockout/increment")
    void incrementFailedAttempts(
            @PathVariable("userId") UUID userId
    );


    @PostMapping("/users/{userId}/lockout/reset")
    void resetFailedAttempts(
            @PathVariable("userId") UUID userId
    );
}