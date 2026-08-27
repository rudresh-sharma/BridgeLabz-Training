package com.fundoo.notesservice.note.kafka;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import com.fundoo.notesservice.note.event.NoteEvent;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NoteEventProducer {

    private static final String TOPIC = "note-events";

    private final KafkaTemplate<String, NoteEvent> kafkaTemplate;

    public void sendNoteEvent(NoteEvent event) {

        kafkaTemplate.send(
                TOPIC,
                event.noteId().toString(),
                event
        );
    }
}