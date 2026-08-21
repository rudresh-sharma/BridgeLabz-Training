package com.fundoonotesapp.messaging.consumer;

import com.fundoonotesapp.messaging.config.RabbitMQConfig;
import com.fundoonotesapp.messaging.event.NoteCreatedEvent;
import com.fundoonotesapp.messaging.event.NoteDeletedEvent;
import com.fundoonotesapp.messaging.event.NoteUpdatedEvent;
import com.fundoonotesapp.notes.entity.Note;
import com.fundoonotesapp.notes.repository.NoteRepository;
import com.fundoonotesapp.search.service.SearchService;

import lombok.RequiredArgsConstructor;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NoteSearchConsumer {

    private final NoteRepository noteRepository;
    private final SearchService searchService;


    // ================= CREATE =================

    @RabbitListener(
            queues = RabbitMQConfig.NOTE_SEARCH_QUEUE
    )
    public void consumeCreated(NoteCreatedEvent event) {

        System.out.println(
                "SEARCH CREATE EVENT RECEIVED: " + event
        );

        Note note = noteRepository.findById(event.noteId())
                .orElseThrow(() -> new RuntimeException(
                        "Note not found: " + event.noteId()
                ));

        searchService.indexNote(note);

        System.out.println(
                "NOTE INDEXED IN ELASTICSEARCH: " + event.noteId()
        );
    }


    // ================= UPDATE =================

    @RabbitListener(
            queues = RabbitMQConfig.NOTE_SEARCH_QUEUE
    )
    public void consumeUpdated(NoteUpdatedEvent event) {

        System.out.println(
                "SEARCH UPDATE EVENT RECEIVED: " + event
        );

        Note note = noteRepository.findById(event.noteId())
                .orElseThrow(() -> new RuntimeException(
                        "Note not found: " + event.noteId()
                ));

        searchService.indexNote(note);

        System.out.println(
                "NOTE UPDATED IN ELASTICSEARCH: " + event.noteId()
        );
    }
    
    
    @RabbitListener(
            queues = RabbitMQConfig.NOTE_SEARCH_QUEUE
    )
    public void consumeDelete(NoteDeletedEvent event) {

        System.out.println(
                "SEARCH DELETE EVENT RECEIVED: " + event
        );

        searchService.deleteNoteFromIndex(event.noteId());

        System.out.println(
                "NOTE DELETED FROM ELASTICSEARCH: "
                        + event.noteId()
        );
    }
}