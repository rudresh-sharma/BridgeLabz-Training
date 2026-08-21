package com.fundoonotesapp.notes.service;

import com.fundoonotesapp.exception.common.ResourceNotFoundException;
import com.fundoonotesapp.messaging.config.RabbitMQConfig;
import com.fundoonotesapp.messaging.event.NoteCreatedEvent;
import com.fundoonotesapp.messaging.event.NoteDeletedEvent;
import com.fundoonotesapp.messaging.event.NoteUpdatedEvent;
import com.fundoonotesapp.messaging.producer.NoteEventProducer;
import com.fundoonotesapp.labels.entity.Label;
import com.fundoonotesapp.labels.repository.LabelRepository;
import com.fundoonotesapp.mapper.NoteMapper;
import com.fundoonotesapp.notes.dto.CreateNoteRequest;
import com.fundoonotesapp.notes.dto.NoteResponse;
import com.fundoonotesapp.notes.dto.UpdateNoteRequest;
import com.fundoonotesapp.notes.entity.Note;
import com.fundoonotesapp.notes.entity.Note.NoteStatus;
import com.fundoonotesapp.notes.repository.NoteRepository;
import com.fundoonotesapp.user.entity.User;
import com.fundoonotesapp.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;
import com.fundoonotesapp.search.service.SearchService;
@Service
@RequiredArgsConstructor
public class NoteService {

    private final NoteRepository noteRepository;
    private final LabelRepository labelRepository;
    private final UserRepository userRepository;
    private final NoteEventProducer noteEventProducer;
    private final SearchService searchService;
    private final NoteMapper noteMapper;
    private final RabbitTemplate rabbitTemplate;

    // CREATE NOTE
    public NoteResponse createNote(CreateNoteRequest request) {

        User currentUser = getCurrentUser();
        Note note = noteMapper.toEntity(request, currentUser);

        Note savedNote = noteRepository.save(note);

        // Index the saved note in Elasticsearch
        searchService.indexNote(savedNote);
        
        NoteCreatedEvent event = new NoteCreatedEvent(
                savedNote.getId(),
                currentUser.getId(),
                savedNote.getTitle()
        );

        noteEventProducer.publishNoteCreated(event);

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

     // Update Elasticsearch index
     searchService.indexNote(updatedNote);
     
    

     NoteUpdatedEvent event = new NoteUpdatedEvent(
             updatedNote.getId(),
             updatedNote.getUser().getId(),
             updatedNote.getTitle()
     );

     rabbitTemplate.convertAndSend(
             RabbitMQConfig.NOTE_EXCHANGE,
             RabbitMQConfig.NOTE_UPDATED_ROUTING_KEY,
             event
     );

     System.out.println(
             "NOTE UPDATED EVENT SENT TO RABBITMQ: " + event
     );

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
        
        
        if (!note.getUser().getId().equals(currentUser.getId())) {
            throw new RuntimeException("Unauthorized");
        }
        
        // Save event data BEFORE deletion
        NoteDeletedEvent event = new NoteDeletedEvent(
                note.getId(),
                currentUser.getId(),
                note.getTitle()
        );

        noteRepository.delete(note);

        // Remove from Elasticsearch
        searchService.deleteNoteFromIndex(noteId);  
        
        
        // Publish event
        rabbitTemplate.convertAndSend(
                RabbitMQConfig.NOTE_EXCHANGE,
                RabbitMQConfig.NOTE_DELETED_ROUTING_KEY,
                event
        );

        System.out.println(
                "NOTE DELETED EVENT SENT TO RABBITMQ: " + event
        );
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
    
    
    private Note getUserNote(Long noteId, User user) {

        return noteRepository.findByIdAndUser(noteId, user)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Note not found")
                );
    }
    
    private NoteResponse changeNoteStatus(
            Long noteId,
            User user,
            NoteStatus status) {

        Note note = getUserNote(noteId, user);

        note.setStatus(status);

        Note updatedNote = noteRepository.save(note);

        // Synchronize updated status with Elasticsearch
        searchService.indexNote(updatedNote);

        return noteMapper.toResponse(updatedNote);
    }
    
    public NoteResponse pinNote(Long noteId, User user) {

        return changeNoteStatus(
                noteId,
                user,
                NoteStatus.PINNED
        );
    }
    
    
    public NoteResponse unpinNote(Long noteId, User user) {

        return changeNoteStatus(
                noteId,
                user,
                NoteStatus.ACTIVE
        );
    }
    
    
    public NoteResponse archiveNote(Long noteId, User user) {

        return changeNoteStatus(
                noteId,
                user,
                NoteStatus.ARCHIVED
        );
    }
    
    public NoteResponse unarchiveNote(Long noteId, User user) {

        return changeNoteStatus(
                noteId,
                user,
                NoteStatus.ACTIVE
        );
    }
    
    
    public NoteResponse trashNote(Long noteId, User user) {

        return changeNoteStatus(
                noteId,
                user,
                NoteStatus.TRASHED
        );
    }
    
    public NoteResponse restoreNote(Long noteId, User user) {

        return changeNoteStatus(
                noteId,
                user,
                NoteStatus.ACTIVE
        );
    }
    
    
    public void addLabelToNote(
            Long noteId,
            Long labelId,
            Principal principal
    ) {
        User user = getCurrentUser(principal);

        Note note = noteRepository
                .findByIdAndUserId(noteId, user.getId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Note not found")
                );

        Label label = labelRepository
                .findByIdAndUserId(labelId, user.getId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Label not found")
                );

        note.getLabels().add(label);

        noteRepository.save(note);
    }
    
    
    public void removeLabelFromNote(
            Long noteId,
            Long labelId,
            Principal principal
    ) {

        User user = getCurrentUser(principal);

        // Check that the note belongs to the logged-in user
        Note note = noteRepository
                .findByIdAndUserId(noteId, user.getId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Note not found")
                );

        // Check that the label belongs to the logged-in user
        Label label = labelRepository
                .findByIdAndUserId(labelId, user.getId())
                .orElseThrow(() ->
                        new ResourceNotFoundException("Label not found")
                );

        note.getLabels().remove(label);

        noteRepository.save(note);
    }
    
    
 // ============================
    // CURRENT LOGGED-IN USER
    // ============================

    private User getCurrentUser(Principal principal) {

        return userRepository
                .findByEmail(principal.getName())
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "User not found"
                        )
                );
    }
    

}