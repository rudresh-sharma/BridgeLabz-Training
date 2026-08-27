package com.fundoo.notesservice.note.event;

public record NoteEvent(

        String eventType,

        Long noteId,

        String title,

        String content,

        String email,

        String status

) {
}