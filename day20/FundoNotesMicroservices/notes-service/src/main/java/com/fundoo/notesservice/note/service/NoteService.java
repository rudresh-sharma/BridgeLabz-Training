package com.fundoo.notesservice.note.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fundoo.notesservice.labels.entity.Label;
import com.fundoo.notesservice.labels.repository.LabelRepository;
import com.fundoo.notesservice.mapper.NoteMapper;
import com.fundoo.notesservice.note.dto.CreateNoteRequest;
import com.fundoo.notesservice.note.dto.NoteResponse;
import com.fundoo.notesservice.note.dto.UpdateNoteRequest;
import com.fundoo.notesservice.note.entity.Note;
import com.fundoo.notesservice.note.event.NoteEvent;
import com.fundoo.notesservice.note.kafka.NoteEventProducer;
import com.fundoo.notesservice.repository.NoteRepository;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor
public class NoteService {

    private final NoteRepository noteRepository;
    private final NoteMapper noteMapper;
    private final LabelRepository labelRepository;
    private final NoteEventProducer noteEventProducer;
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
        
        NoteEvent event = new NoteEvent(
                "CREATED",
                savedNote.getId(),
                savedNote.getTitle(),
                savedNote.getContent(),
                savedNote.getEmail(),
                savedNote.getStatus().name()
        );

        noteEventProducer.sendNoteEvent(event);

        return noteMapper.mapToResponse(savedNote);
    }
    
    
    
    public List<NoteResponse> getMyNotes(String email) {

        List<Note.NoteStatus> statuses = List.of(
                Note.NoteStatus.ACTIVE,
                Note.NoteStatus.PINNED
        );

        return noteRepository
                .findByEmailAndStatusIn(email, statuses)
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
    
    public NoteResponse getNoteByIdInternal(Long noteId) {

        Note note = noteRepository
                .findById(noteId)
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


        NoteEvent event = new NoteEvent(
                "UPDATED",
                updatedNote.getId(),
                updatedNote.getTitle(),
                updatedNote.getContent(),
                updatedNote.getEmail(),
                updatedNote.getStatus().name()
        );

        noteEventProducer.sendNoteEvent(event);


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
        
        NoteEvent event = new NoteEvent(
                "DELETED",
                note.getId(),
                note.getTitle(),
                note.getContent(),
                note.getEmail(),
                note.getStatus().name()
        );

        noteEventProducer.sendNoteEvent(event);
    }
    
    
    public NoteResponse pinNote(
            Long noteId,
            String email
    ) {

        Note note = getNoteByIdAndEmail(noteId, email);

        if (note.getStatus() == Note.NoteStatus.TRASHED) {
            throw new RuntimeException(
                    "Cannot pin a trashed note. Please restore it first."
            );
        }

        note.setStatus(Note.NoteStatus.PINNED);
        
        Note updatedNote = noteRepository.save(note);

        NoteEvent event = new NoteEvent(
                "UPDATED",
                updatedNote.getId(),
                updatedNote.getTitle(),
                updatedNote.getContent(),
                updatedNote.getEmail(),
                updatedNote.getStatus().name()
        );

        noteEventProducer.sendNoteEvent(event);
        
        return noteMapper.mapToResponse(
                updatedNote
        );
    }
    
    public NoteResponse archiveNote(
            Long noteId,
            String email
    ) {

        Note note = getNoteByIdAndEmail(noteId, email);
        
        if (note.getStatus() == Note.NoteStatus.TRASHED) {
            throw new RuntimeException(
                    "Cannot archive a trashed note. Please restore it first."
            );
        }
        
        
        note.setStatus(Note.NoteStatus.ARCHIVED);

        Note updatedNote = noteRepository.save(note);

        NoteEvent event = new NoteEvent(
                "UPDATED",
                updatedNote.getId(),
                updatedNote.getTitle(),
                updatedNote.getContent(),
                updatedNote.getEmail(),
                updatedNote.getStatus().name()
        );

        noteEventProducer.sendNoteEvent(event);
        
        return noteMapper.mapToResponse(
                updatedNote
        );
    }


    public NoteResponse unpinNote(
            Long noteId,
            String email
    ) {

        Note note = getNoteByIdAndEmail(noteId, email);

        if (note.getStatus() != Note.NoteStatus.PINNED) {
            throw new RuntimeException(
                    "Note is not pinned."
            );
        }

        note.setStatus(Note.NoteStatus.ACTIVE);

        Note updatedNote = noteRepository.save(note);

        NoteEvent event = new NoteEvent(
                "UPDATED",
                updatedNote.getId(),
                updatedNote.getTitle(),
                updatedNote.getContent(),
                updatedNote.getEmail(),
                updatedNote.getStatus().name()
        );

        noteEventProducer.sendNoteEvent(event);

        return noteMapper.mapToResponse(updatedNote);
    }


    public NoteResponse unarchiveNote(
            Long noteId,
            String email
    ) {

        Note note = getNoteByIdAndEmail(noteId, email);

        if (note.getStatus() != Note.NoteStatus.ARCHIVED) {
            throw new RuntimeException(
                    "Note is not archived."
            );
        }

        note.setStatus(Note.NoteStatus.ACTIVE);

        Note updatedNote = noteRepository.save(note);

        NoteEvent event = new NoteEvent(
                "UPDATED",
                updatedNote.getId(),
                updatedNote.getTitle(),
                updatedNote.getContent(),
                updatedNote.getEmail(),
                updatedNote.getStatus().name()
        );

        noteEventProducer.sendNoteEvent(event);

        return noteMapper.mapToResponse(updatedNote);
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
    
    
    public NoteResponse trashNote(
            Long noteId,
            String email
    ) {

        Note note = getNoteByIdAndEmail(noteId, email);

        note.setStatus(Note.NoteStatus.TRASHED);
        Note updatedNote = noteRepository.save(note);

        NoteEvent event = new NoteEvent(
                "UPDATED",
                updatedNote.getId(),
                updatedNote.getTitle(),
                updatedNote.getContent(),
                updatedNote.getEmail(),
                updatedNote.getStatus().name()
        );

        noteEventProducer.sendNoteEvent(event);
        
        return noteMapper.mapToResponse(
                updatedNote
        );
    }
    
    
    public NoteResponse restoreNote(
            Long noteId,
            String email
    ) {

        Note note = getNoteByIdAndEmail(noteId, email);

        note.setStatus(Note.NoteStatus.ACTIVE);

        Note updatedNote = noteRepository.save(note);

        NoteEvent event = new NoteEvent(
                "UPDATED",
                updatedNote.getId(),
                updatedNote.getTitle(),
                updatedNote.getContent(),
                updatedNote.getEmail(),
                updatedNote.getStatus().name()
        );

        noteEventProducer.sendNoteEvent(event);
        
        return noteMapper.mapToResponse(
                updatedNote
        );
    }
    
    
    public List<NoteResponse> getNotesByStatus(
            String email,
            Note.NoteStatus status
    ) {

        return noteRepository
                .findByEmailAndStatus(email, status)
                .stream()
                .map(noteMapper::mapToResponse)
                .toList();
    }
    
    
    public NoteResponse addLabelToNote(
            Long noteId,
            Long labelId,
            String email
    ) {

        Note note = getNoteByIdAndEmail(
                noteId,
                email
        );

        Label label = labelRepository
                .findByIdAndEmail(labelId, email)
                .orElseThrow(() ->
                        new RuntimeException("Label not found")
                );

        note.getLabels().add(label);

        return noteMapper.mapToResponse(
                noteRepository.save(note)
        );
    }
    
    
    public NoteResponse removeLabelFromNote(
            Long noteId,
            Long labelId,
            String email
    ) {

        Note note = getNoteByIdAndEmail(
                noteId,
                email
        );

        Label label = labelRepository
                .findByIdAndEmail(labelId, email)
                .orElseThrow(() ->
                        new RuntimeException("Label not found")
                );

        if (!note.getLabels().contains(label)) {
            throw new RuntimeException(
                    "This label is not attached to the note"
            );
        }

        note.getLabels().remove(label);

        return noteMapper.mapToResponse(
                noteRepository.save(note)
        );
    }
}