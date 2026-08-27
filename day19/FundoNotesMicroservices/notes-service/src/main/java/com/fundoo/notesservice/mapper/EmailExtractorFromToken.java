package com.fundoo.notesservice.mapper;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.fundoo.notesservice.security.JwtService;

import io.jsonwebtoken.JwtException;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class EmailExtractorFromToken {
	
	private final JwtService jwtService;
	
	
	
	public EmailResult extractEmailOrUnauthorized(HttpServletRequest httpRequest) {
		String header = httpRequest.getHeader("Authorization");

		if (header == null || !header.startsWith("Bearer ")) {
			return new EmailResult(null,
					ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Missing or invalid Authorization header"));
		}

		String token = header.substring(7);

		try {
			return new EmailResult(jwtService.extractEmail(token), null);
		} catch (JwtException e) {
			return new EmailResult(null,
					ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid or expired token: " + e.getMessage()));
		}
	}
}
