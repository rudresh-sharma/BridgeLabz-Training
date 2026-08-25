package com.fundoonotesapp.notes.controller;

import com.fundoonotesapp.notes.dto.CreateNoteRequest;
import com.fundoonotesapp.security.CustomUserDetails;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import com.fundoonotesapp.notes.dto.NoteResponse;
import com.fundoonotesapp.notes.dto.UpdateNoteRequest;
import com.fundoonotesapp.notes.service.NoteService;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;

import java.security.Principal;
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
    
    
    @PatchMapping("/{noteId}/pin")
    public ResponseEntity<NoteResponse> pinNote(
            @PathVariable Long noteId,
            @AuthenticationPrincipal CustomUserDetails userDetails) {

        NoteResponse response = noteService.pinNote(
                noteId,
                userDetails.getUser()
        );

        return ResponseEntity.ok(response);
    }
    
    
    @PatchMapping("/{noteId}/unpin")
    public ResponseEntity<NoteResponse> unpinNote(
            @PathVariable Long noteId,
            @AuthenticationPrincipal CustomUserDetails userDetails) {

        NoteResponse response = noteService.unpinNote(
                noteId,
                userDetails.getUser()
        );

        return ResponseEntity.ok(response);
    }
    
    
    @PatchMapping("/{noteId}/archive")
    public ResponseEntity<NoteResponse> archiveNote(
            @PathVariable Long noteId,
            @AuthenticationPrincipal CustomUserDetails userDetails) {

        NoteResponse response = noteService.archiveNote(
                noteId,
                userDetails.getUser()
        );

        return ResponseEntity.ok(response);
    }
    
    @PatchMapping("/{noteId}/unarchive")
    public ResponseEntity<NoteResponse> unarchiveNote(
            @PathVariable Long noteId,
            @AuthenticationPrincipal CustomUserDetails userDetails) {

        NoteResponse response = noteService.unarchiveNote(
                noteId,
                userDetails.getUser()
        );

        return ResponseEntity.ok(response);
    }
    
    
    @PatchMapping("/{noteId}/trash")
    public ResponseEntity<NoteResponse> trashNote(
            @PathVariable Long noteId,
            @AuthenticationPrincipal CustomUserDetails userDetails) {

        NoteResponse response = noteService.trashNote(
                noteId,
                userDetails.getUser()
        );

        return ResponseEntity.ok(response);
    }
    
    @PatchMapping("/{noteId}/restore")
    public ResponseEntity<NoteResponse> restoreNote(
            @PathVariable Long noteId,
            @AuthenticationPrincipal CustomUserDetails userDetails) {

        NoteResponse response = noteService.restoreNote(
                noteId,
                userDetails.getUser()
        );

        return ResponseEntity.ok(response);
    }
    
    @PostMapping("/{noteId}/labels/{labelId}")
    public ResponseEntity<Void> addLabelToNote(
            @PathVariable Long noteId,
            @PathVariable Long labelId,
            Principal principal
    ) {

        noteService.addLabelToNote(
                noteId,
                labelId,
                principal
        );

        return ResponseEntity.noContent().build();
    }
    
    @DeleteMapping("/{noteId}/labels/{labelId}")
    public ResponseEntity<Void> removeLabelFromNote(
            @PathVariable Long noteId,
            @PathVariable Long labelId,
            Principal principal
    ) {

        noteService.removeLabelFromNote(
                noteId,
                labelId,
                principal
        );

        return ResponseEntity.noContent().build();
    }
}