package com.fundoonotesapp.messaging.event;

public record NoteUpdatedEvent(
        Long noteId,
        Long userId,
        String title
) {
}