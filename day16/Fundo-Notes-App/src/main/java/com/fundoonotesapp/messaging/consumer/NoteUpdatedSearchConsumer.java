package com.fundoonotesapp.messaging.consumer;

import com.fundoonotesapp.messaging.config.RabbitMQConfig;
import com.fundoonotesapp.messaging.event.NoteUpdatedEvent;
import com.fundoonotesapp.notes.entity.Note;
import com.fundoonotesapp.notes.repository.NoteRepository;
import com.fundoonotesapp.search.service.SearchService;

import lombok.RequiredArgsConstructor;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NoteUpdatedSearchConsumer {

    private final NoteRepository noteRepository;
    private final SearchService searchService;

    @RabbitListener(
            queues = RabbitMQConfig.NOTE_UPDATED_SEARCH_QUEUE
    )
    public void handleNoteUpdated(NoteUpdatedEvent event) {

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
}