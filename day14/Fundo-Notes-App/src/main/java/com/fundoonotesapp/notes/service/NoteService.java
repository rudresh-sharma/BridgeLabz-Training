package com.fundoonotesapp.notes.service;

import com.fundoonotesapp.exception.common.ResourceNotFoundException;
import com.fundoonotesapp.mapper.NoteMapper;
import com.fundoonotesapp.notes.dto.CreateNoteRequest;
import com.fundoonotesapp.notes.dto.NoteResponse;
import com.fundoonotesapp.notes.dto.UpdateNoteRequest;
import com.fundoonotesapp.notes.entity.Note;
import com.fundoonotesapp.notes.repository.NoteRepository;
import com.fundoonotesapp.user.entity.User;
import com.fundoonotesapp.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NoteService {

    private final NoteRepository noteRepository;
    private final UserRepository userRepository;
    private final NoteMapper noteMapper;


    // CREATE NOTE
    public NoteResponse createNote(CreateNoteRequest request) {

        User currentUser = getCurrentUser();

        Note note = noteMapper.toEntity(request, currentUser);

        Note savedNote = noteRepository.save(note);

        return noteMapper.toResponse(savedNote);
    }


    // GET ALL NOTES OF CURRENT USER
    public List<NoteResponse> getMyNotes() {

        User currentUser = getCurrentUser();

        return noteRepository.findByUser(currentUser)
                .stream()
                .map(noteMapper::toResponse)
                .toList();
    }


    // UPDATE NOTE
    public NoteResponse updateNote(
            Long noteId,
            UpdateNoteRequest request) {

        User currentUser = getCurrentUser();

        Note note = noteRepository
                .findByIdAndUser(noteId, currentUser)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Note not found")
                );

        // Update only fields sent by the client
        if (request.getTitle() != null) {
            note.setTitle(request.getTitle());
        }

        if (request.getContent() != null) {
            note.setContent(request.getContent());
        }

        Note updatedNote = noteRepository.save(note);

        return noteMapper.toResponse(updatedNote);
    }


    // DELETE NOTE
    public void deleteNote(Long noteId) {

        User currentUser = getCurrentUser();

        Note note = noteRepository
                .findByIdAndUser(noteId, currentUser)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Note not found")
                );

        noteRepository.delete(note);
    }


    // GET CURRENT LOGGED-IN USER
    private User getCurrentUser() {

        String email = SecurityContextHolder
                .getContext()
                .getAuthentication()
                .getName();

        return userRepository.findByEmail(email)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Authenticated user not found"
                        )
                );
    }
}