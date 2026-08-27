package com.fundoo.notesservice.mapper;

import org.springframework.http.ResponseEntity;

public record EmailResult(String email, ResponseEntity<?> error) {}