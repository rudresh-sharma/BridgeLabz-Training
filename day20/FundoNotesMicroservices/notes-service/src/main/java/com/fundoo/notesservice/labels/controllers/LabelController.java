package com.fundoo.notesservice.labels.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.fundoo.notesservice.labels.dto.CreateLabelRequest;
import com.fundoo.notesservice.labels.dto.LabelResponse;
import com.fundoo.notesservice.labels.dto.UpdateLabelRequest;
import com.fundoo.notesservice.labels.service.LabelService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/labels")
@RequiredArgsConstructor
public class LabelController {

    private final LabelService labelService;

    @PostMapping
    public ResponseEntity<LabelResponse> createLabel(
            @Valid @RequestBody CreateLabelRequest request,
            HttpServletRequest httpRequest
    ) {

        String email =
                (String) httpRequest.getAttribute("email");

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        labelService.createLabel(email, request)
                );
    }

    @GetMapping
    public ResponseEntity<List<LabelResponse>> getMyLabels(
            HttpServletRequest httpRequest
    ) {

        String email =
                (String) httpRequest.getAttribute("email");

        return ResponseEntity.ok(
                labelService.getMyLabels(email)
        );
    }

    @PutMapping("/{labelId}")
    public ResponseEntity<LabelResponse> updateLabel(
            @PathVariable Long labelId,
            @Valid @RequestBody UpdateLabelRequest request,
            HttpServletRequest httpRequest
    ) {

        String email =
                (String) httpRequest.getAttribute("email");

        return ResponseEntity.ok(
                labelService.updateLabel(
                        labelId,
                        email,
                        request
                )
        );
    }

    @DeleteMapping("/{labelId}")
    public ResponseEntity<Void> deleteLabel(
            @PathVariable Long labelId,
            HttpServletRequest httpRequest
    ) {

        String email =
                (String) httpRequest.getAttribute("email");

        labelService.deleteLabel(labelId, email);

        return ResponseEntity.noContent().build();
    }
}