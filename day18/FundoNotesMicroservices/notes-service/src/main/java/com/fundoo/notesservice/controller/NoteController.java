package com.fundoo.notesservice.controller;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.fundoo.notesservice.mapper.EmailExtractorFromToken;
import com.fundoo.notesservice.mapper.EmailResult;
import com.fundoo.notesservice.note.dto.CreateNoteRequest;
import com.fundoo.notesservice.note.dto.NoteResponse;
import com.fundoo.notesservice.note.dto.UpdateNoteRequest;
import com.fundoo.notesservice.note.service.NoteService;
import com.fundoo.notesservice.security.JwtService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/notes")
@RequiredArgsConstructor
public class NoteController {

	private final NoteService noteService;
	private final EmailExtractorFromToken emailExtractor;

	@PostMapping
	public ResponseEntity<NoteResponse> createNote(@Valid @RequestBody CreateNoteRequest request,
			HttpServletRequest httpRequest) {
		String email = (String) httpRequest.getAttribute("email");

		NoteResponse response = noteService.createNote(email, request);

		return ResponseEntity.status(HttpStatus.CREATED).body(response);
	}

	@GetMapping
	public ResponseEntity<List<NoteResponse>> getMyNotes(HttpServletRequest httpRequest) {

		String email = (String) httpRequest.getAttribute("email");

		return ResponseEntity.ok(noteService.getMyNotes(email));
	}

	@GetMapping("/{noteId}")
	public ResponseEntity<NoteResponse> getNoteById(@PathVariable Long noteId, HttpServletRequest httpRequest) {

		String email = (String) httpRequest.getAttribute("email");

		return ResponseEntity.ok(noteService.getNoteById(noteId, email));
	}
	
	@PutMapping("/{noteId}")
	public ResponseEntity<NoteResponse> updateNote(
	        @PathVariable Long noteId,
	        @Valid @RequestBody UpdateNoteRequest request,
	        HttpServletRequest httpRequest
	) {

	    String email =
	            (String) httpRequest.getAttribute("email");

	    return ResponseEntity.ok(
	            noteService.updateNote(
	                    noteId,
	                    email,
	                    request
	            )
	    );
	}
	
	
	@PatchMapping("/{noteId}/pin")
	public ResponseEntity<NoteResponse> pinNote(
	        @PathVariable Long noteId,
	        HttpServletRequest httpRequest
	) {

	    String email =
	            (String) httpRequest.getAttribute("email");

	    return ResponseEntity.ok(
	            noteService.pinNote(noteId, email)
	    );
	}
}