package com.fundoo.notesservice.repository;

import com.fundoo.notesservice.note.entity.Note;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepository extends JpaRepository<Note, Long> {

	List<Note> findByEmail(String userEmail);

	Optional<Note> findByIdAndEmail(Long id, String userEmail);

	List<Note> findByEmailAndStatus(String email, Note.NoteStatus status);

	List<Note> findByEmailAndStatusIn(String email, List<Note.NoteStatus> statuses);

	// Used by NoteCleanupScheduler — finds notes trashed more than N days ago
	List<Note> findByStatusAndUpdatedAtBefore(
			Note.NoteStatus status,
			LocalDateTime cutoff
	);
}