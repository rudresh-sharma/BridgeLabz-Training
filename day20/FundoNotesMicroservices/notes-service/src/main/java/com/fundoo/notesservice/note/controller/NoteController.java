package com.fundoo.notesservice.note.controller;

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
import com.fundoo.notesservice.note.entity.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/notes")
@RequiredArgsConstructor
public class NoteController {

	private final NoteService noteService;
	private final EmailExtractorFromToken emailExtractor;
	
	
	
	@GetMapping("/internal/{noteId}")
	public ResponseEntity<NoteResponse> getNoteByIdInternal(@PathVariable Long noteId) {
	    return ResponseEntity.ok(noteService.getNoteByIdInternal(noteId));
	}
	
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
	
	
	@PatchMapping("/{noteId}/archive")
	public ResponseEntity<NoteResponse> archiveNote(
	        @PathVariable Long noteId,
	        HttpServletRequest httpRequest
	) {

	    String email =
	            (String) httpRequest.getAttribute("email");

	    return ResponseEntity.ok(
	            noteService.archiveNote(noteId, email)
	    );
	}


	@PatchMapping("/{noteId}/unpin")
	public ResponseEntity<NoteResponse> unpinNote(
	        @PathVariable Long noteId,
	        HttpServletRequest httpRequest
	) {

	    String email =
	            (String) httpRequest.getAttribute("email");

	    return ResponseEntity.ok(
	            noteService.unpinNote(noteId, email)
	    );
	}


	@PatchMapping("/{noteId}/unarchive")
	public ResponseEntity<NoteResponse> unarchiveNote(
	        @PathVariable Long noteId,
	        HttpServletRequest httpRequest
	) {

	    String email =
	            (String) httpRequest.getAttribute("email");

	    return ResponseEntity.ok(
	            noteService.unarchiveNote(noteId, email)
	    );
	}


	@PatchMapping("/{noteId}/trash")
	public ResponseEntity<NoteResponse> trashNote(
	        @PathVariable Long noteId,
	        HttpServletRequest httpRequest
	) {

	    String email =
	            (String) httpRequest.getAttribute("email");

	    return ResponseEntity.ok(
	            noteService.trashNote(noteId, email)
	    );
	}
	
	
	@PatchMapping("/{noteId}/restore")
	public ResponseEntity<NoteResponse> restoreNote(
	        @PathVariable Long noteId,
	        HttpServletRequest httpRequest
	) {

	    String email =
	            (String) httpRequest.getAttribute("email");

	    return ResponseEntity.ok(
	            noteService.restoreNote(noteId, email)
	    );
	}
	
	
	@GetMapping("/pinned")
	public ResponseEntity<List<NoteResponse>> getPinnedNotes(
	        HttpServletRequest httpRequest
	) {

	    String email =
	            (String) httpRequest.getAttribute("email");

	    return ResponseEntity.ok(
	            noteService.getNotesByStatus(
	                    email,
	                    Note.NoteStatus.PINNED
	            )
	    );
	}
	
	
	@GetMapping("/archived")
	public ResponseEntity<List<NoteResponse>> getArchivedNotes(
	        HttpServletRequest httpRequest
	) {

	    String email =
	            (String) httpRequest.getAttribute("email");

	    return ResponseEntity.ok(
	            noteService.getNotesByStatus(
	                    email,
	                    Note.NoteStatus.ARCHIVED
	            )
	    );
	}
	
	
	@GetMapping("/trashed")
	public ResponseEntity<List<NoteResponse>> getTrashedNotes(
	        HttpServletRequest httpRequest
	) {

	    String email =
	            (String) httpRequest.getAttribute("email");

	    return ResponseEntity.ok(
	            noteService.getNotesByStatus(
	                    email,
	                    Note.NoteStatus.TRASHED
	            )
	    );
	}
	
	
	@PostMapping("/{noteId}/labels/{labelId}")
	public ResponseEntity<NoteResponse> addLabelToNote(
	        @PathVariable Long noteId,
	        @PathVariable Long labelId,
	        HttpServletRequest httpRequest
	) {

	    String email =
	            (String) httpRequest.getAttribute("email");

	    return ResponseEntity.ok(
	            noteService.addLabelToNote(
	                    noteId,
	                    labelId,
	                    email
	            )
	    );
	}
	
	
	@DeleteMapping("/{noteId}/labels/{labelId}")
	public ResponseEntity<NoteResponse> removeLabelFromNote(
	        @PathVariable Long noteId,
	        @PathVariable Long labelId,
	        HttpServletRequest httpRequest
	) {

	    String email =
	            (String) httpRequest.getAttribute("email");

	    return ResponseEntity.ok(
	            noteService.removeLabelFromNote(
	                    noteId,
	                    labelId,
	                    email
	            )
	    );
	}
}