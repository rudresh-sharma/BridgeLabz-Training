package com.fundoo.notesservice.note.service;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.fundoo.notesservice.mapper.NoteMapper;
import com.fundoo.notesservice.note.dto.CreateNoteRequest;
import com.fundoo.notesservice.note.dto.NoteResponse;
import com.fundoo.notesservice.note.dto.UpdateNoteRequest;
import com.fundoo.notesservice.note.entity.Note;
import com.fundoo.notesservice.repository.NoteRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NoteService {

    private final NoteRepository noteRepository;
    private final NoteMapper noteMapper;
    public NoteResponse createNote(
            String email,
            CreateNoteRequest request
    ) {

        Note note = Note.builder()
                .title(request.title())
                .content(request.content())
                .status(Note.NoteStatus.ACTIVE)
                .email(email)
                .build();

        Note savedNote = noteRepository.save(note);

        return noteMapper.mapToResponse(savedNote);
    }
    
    
    
    public List<NoteResponse> getMyNotes(String email) {

        return noteRepository.findByEmail(email)
                .stream()
                .map(noteMapper::mapToResponse)
                .toList();
    }
    
    public NoteResponse getNoteById(
            Long noteId,
            String email
    ) {

        Note note = noteRepository
                .findByIdAndEmail(noteId, email)
                .orElseThrow(() ->
                        new RuntimeException("Note not found"));

        return noteMapper.mapToResponse(note);
    }
    
    
    public NoteResponse updateNote(
            Long noteId,
            String email,
            UpdateNoteRequest request
    ) {

        Note note = noteRepository
                .findByIdAndEmail(noteId, email)
                .orElseThrow(() ->
                        new RuntimeException("Note not found")
                );

        if (request.title() != null) {
            note.setTitle(request.title());
        }

        if (request.content() != null) {
            note.setContent(request.content());
        }

        Note updatedNote = noteRepository.save(note);

        return noteMapper.mapToResponse(updatedNote);
    }
   
    
    
    public void deleteNote(
            Long noteId,
            String email
    ) {

        Note note = noteRepository
                .findByIdAndEmail(noteId, email)
                .orElseThrow(() ->
                        new RuntimeException("Note not found")
                );

        noteRepository.delete(note);
    }
    
    
    public NoteResponse pinNote(
            Long noteId,
            String email
    ) {

        Note note = getNoteByIdAndEmail(noteId, email);

        note.setStatus(Note.NoteStatus.PINNED);

        return noteMapper.mapToResponse(
                noteRepository.save(note)
        );
    }
    
    private Note getNoteByIdAndEmail(
            Long noteId,
            String email
    ) {

        return noteRepository
                .findByIdAndEmail(noteId, email)
                .orElseThrow(() ->
                        new RuntimeException("Note not found")
                );
    }
}