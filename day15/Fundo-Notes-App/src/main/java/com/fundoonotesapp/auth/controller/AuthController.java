package com.fundoonotesapp.auth.controller;

import com.fundoonotesapp.auth.dto.AuthResponse;
import com.fundoonotesapp.auth.dto.ForgotPasswordRequest;
import com.fundoonotesapp.auth.dto.LoginRequest;
import com.fundoonotesapp.auth.dto.RegisterRequest;
import com.fundoonotesapp.auth.dto.ResetPasswordRequest;
import com.fundoonotesapp.auth.service.AuthService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

	private final AuthService authService;

	@PostMapping("/register")
	public ResponseEntity<AuthResponse> register(@Valid @RequestBody RegisterRequest request) {

		AuthResponse response = authService.register(request);

		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@PostMapping("/login")
	public ResponseEntity<AuthResponse> login(@Valid @RequestBody LoginRequest request) {

		AuthResponse response = authService.login(request);

		return ResponseEntity.ok(response);
	}

	@PostMapping("/forgot-password")
	public ResponseEntity<AuthResponse> forgotPassword(@Valid @RequestBody ForgotPasswordRequest request) {

		return ResponseEntity.ok(authService.forgotPassword(request));
	}

	@PostMapping("/reset-password")
	public ResponseEntity<AuthResponse> resetPassword(@Valid @RequestBody ResetPasswordRequest request) {

		return ResponseEntity.ok(authService.resetPassword(request));
	}
}