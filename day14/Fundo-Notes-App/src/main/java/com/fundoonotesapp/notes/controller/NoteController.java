package com.fundoonotesapp.notes.controller;

import com.fundoonotesapp.notes.dto.CreateNoteRequest;
import com.fundoonotesapp.notes.dto.NoteResponse;
import com.fundoonotesapp.notes.dto.UpdateNoteRequest;
import com.fundoonotesapp.notes.service.NoteService;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notes")
@RequiredArgsConstructor
public class NoteController {

    private final NoteService noteService;


    // CREATE NOTE
    @PostMapping
    public ResponseEntity<NoteResponse> createNote(
            @Valid @RequestBody CreateNoteRequest request) {

        NoteResponse response = noteService.createNote(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }


    // GET MY NOTES
    @GetMapping
    public ResponseEntity<List<NoteResponse>> getMyNotes() {

        return ResponseEntity.ok(noteService.getMyNotes());
    }


    // UPDATE MY NOTE
    @PutMapping("/{noteId}")
    public ResponseEntity<NoteResponse> updateNote(
            @PathVariable Long noteId,
            @Valid @RequestBody UpdateNoteRequest request) {

        NoteResponse response =
                noteService.updateNote(noteId, request);

        return ResponseEntity.ok(response);
    }


    // DELETE MY NOTE
    @DeleteMapping("/{noteId}")
    public ResponseEntity<Void> deleteNote(
            @PathVariable Long noteId) {

        noteService.deleteNote(noteId);

        return ResponseEntity.noContent().build();
    }
}