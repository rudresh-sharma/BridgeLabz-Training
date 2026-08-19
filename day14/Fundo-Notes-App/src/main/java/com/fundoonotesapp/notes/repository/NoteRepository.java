package com.fundoonotesapp.notes.repository;

import com.fundoonotesapp.notes.entity.Note;
import com.fundoonotesapp.user.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface NoteRepository extends JpaRepository<Note, Long> {

    List<Note> findByUser(User user);

    Optional<Note> findByIdAndUser(Long id, User user);
}