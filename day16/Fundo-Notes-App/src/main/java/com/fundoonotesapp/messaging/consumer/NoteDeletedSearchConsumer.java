package com.fundoonotesapp.messaging.consumer;

import com.fundoonotesapp.messaging.config.RabbitMQConfig;
import com.fundoonotesapp.messaging.event.NoteDeletedEvent;
import com.fundoonotesapp.search.service.SearchService;

import lombok.RequiredArgsConstructor;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NoteDeletedSearchConsumer {

    private final SearchService searchService;

    @RabbitListener(
            queues = RabbitMQConfig.NOTE_DELETED_SEARCH_QUEUE
    )
    public void handleNoteDeleted(NoteDeletedEvent event) {

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