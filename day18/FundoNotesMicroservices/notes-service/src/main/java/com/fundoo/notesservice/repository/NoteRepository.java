package com.fundoo.notesservice.repository;

import com.fundoo.notesservice.note.entity.Note;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;

public interface NoteRepository extends JpaRepository<Note, Long> {

	List<Note> findByEmail(String userEmail);

	Optional<Note> findByIdAndEmail(Long id, String userEmail);
}