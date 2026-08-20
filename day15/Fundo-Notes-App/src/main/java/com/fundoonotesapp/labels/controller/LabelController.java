package com.fundoonotesapp.labels.controller;

import com.fundoonotesapp.labels.dto.CreateLabelRequest;
import com.fundoonotesapp.labels.dto.LabelResponse;
import com.fundoonotesapp.labels.service.LabelService;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/labels")
@RequiredArgsConstructor
public class LabelController {

    private final LabelService labelService;


    // ============================
    // CREATE LABEL
    // ============================

    @PostMapping
    public ResponseEntity<LabelResponse> createLabel(
            @Valid @RequestBody CreateLabelRequest request,
            Principal principal
    ) {

        LabelResponse response =
                labelService.createLabel(request, principal);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }


    // ============================
    // GET ALL MY LABELS
    // ============================

    @GetMapping
    public ResponseEntity<List<LabelResponse>> getMyLabels(
            Principal principal
    ) {

        return ResponseEntity.ok(
                labelService.getMyLabels(principal)
        );
    }


    // ============================
    // RENAME LABEL
    // ============================

    @PutMapping("/{labelId}")
    public ResponseEntity<LabelResponse> renameLabel(
            @PathVariable Long labelId,
            @Valid @RequestBody CreateLabelRequest request,
            Principal principal
    ) {

        return ResponseEntity.ok(
                labelService.renameLabel(
                        labelId,
                        request,
                        principal
                )
        );
    }


    // ============================
    // DELETE LABEL
    // ============================

    @DeleteMapping("/{labelId}")
    public ResponseEntity<Void> deleteLabel(
            @PathVariable Long labelId,
            Principal principal
    ) {

        labelService.deleteLabel(labelId, principal);

        return ResponseEntity.noContent().build();
    }
}