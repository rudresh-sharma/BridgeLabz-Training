package com.fundoonotesapp.messaging.event;

public record NoteDeletedEvent(
        Long noteId,
        Long userId,
        String title
) {
}