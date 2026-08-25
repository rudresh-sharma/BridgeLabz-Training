package com.fundoo.userservice.user.controller;

import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.fundoo.userservice.user.dto.CreateUserRequest;
import com.fundoo.userservice.user.dto.UpdateUserRequest;
import com.fundoo.userservice.user.dto.UserAuthResponse;
import com.fundoo.userservice.user.dto.UserResponse;
import com.fundoo.userservice.user.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    
    @PostMapping
    public ResponseEntity<UserResponse> createUser(
            @Valid @RequestBody CreateUserRequest request
    ) {
        return ResponseEntity.ok(
                userService.createUser(request)
        );
    }
    
    
    @GetMapping("/{id}")
    public ResponseEntity<UserResponse> getUserById(
            @PathVariable UUID id
    ) {

        return ResponseEntity.ok(
                userService.getUserById(id)
        );
    }


    @GetMapping("/email/{email}")
    public ResponseEntity<UserResponse> getUserByEmail(
            @PathVariable String email
    ) {

        return ResponseEntity.ok(
                userService.getUserByEmail(email)
        );
    }


    @PutMapping("/email/{email}")
    public ResponseEntity<UserResponse> updateUser(
            @PathVariable String email,
            @RequestBody UpdateUserRequest request
    ) {

        return ResponseEntity.ok(
                userService.updateUser(email, request)
        );
    }
    
    @GetMapping("/auth/{userId}")
    public ResponseEntity<UserAuthResponse> getUserForAuthById(
            @PathVariable UUID userId
    ) {
        return ResponseEntity.ok(
                userService.getUserForAuthById(userId)
        );
    }
    
    @GetMapping("/auth/email/{email}")
    public ResponseEntity<UserAuthResponse> getUserForAuth(
            @PathVariable String email
    ) {
    	System.out.println("ENDPOINT CALLED ");
        return ResponseEntity.ok(
                userService.getUserForAuth(email)
        );
    }
}