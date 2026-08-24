package com.fundoonotesapp.batch.controller;

import java.io.IOException;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fundoonotesapp.batch.export.NoteCsvExportService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/batch")
@RequiredArgsConstructor
public class NoteExportController {

    private final NoteCsvExportService noteCsvExportService;

    @PostMapping("/export")
    public ResponseEntity<String> exportNotes() throws IOException {

        noteCsvExportService.exportNotes();

        return ResponseEntity.ok(
                "Notes exported successfully"
        );
    }
}