package com.fundoonotesapp.messaging.consumer;

import com.fundoonotesapp.messaging.config.RabbitMQConfig;
import com.fundoonotesapp.messaging.event.NoteCreatedEvent;
import com.fundoonotesapp.notes.entity.Note;
import com.fundoonotesapp.notes.repository.NoteRepository;
import com.fundoonotesapp.search.service.SearchService;

import lombok.RequiredArgsConstructor;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NoteCreatedSearchConsumer {

    private final NoteRepository noteRepository;
    private final SearchService searchService;

    @RabbitListener(
            queues = RabbitMQConfig.NOTE_CREATED_SEARCH_QUEUE
    )
    public void handleNoteCreated(NoteCreatedEvent event) {

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
}