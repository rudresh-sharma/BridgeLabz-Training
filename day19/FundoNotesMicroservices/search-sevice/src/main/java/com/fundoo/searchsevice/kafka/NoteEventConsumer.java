package com.fundoo.searchsevice.kafka;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.fundoo.searchsevice.entity.SearchNote;
import com.fundoo.searchsevice.event.NoteEvent;
import com.fundoo.searchsevice.repository.SearchNoteRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NoteEventConsumer {

    private final SearchNoteRepository searchNoteRepository;

    @KafkaListener(
            topics = "note-events",
            groupId = "search-service-group"
    )
    public void consume(NoteEvent event) {

        switch (event.eventType()) {

            case "CREATED", "UPDATED" -> {

                SearchNote searchNote = SearchNote.builder()
                        .id(event.noteId())
                        .title(event.title())
                        .content(event.content())
                        .email(event.email())
                        .status(event.status())
                        .build();

                searchNoteRepository.save(searchNote);

                System.out.println(
                        "NOTE " + event.eventType()
                        + " IN SEARCH DATABASE"
                );
            }

            case "DELETED" -> {

                searchNoteRepository.deleteById(
                        event.noteId()
                );

                System.out.println(
                        "NOTE DELETED FROM SEARCH DATABASE"
                );
            }

            default -> System.out.println(
                    "UNKNOWN EVENT: " + event.eventType()
            );
        }
    }
}