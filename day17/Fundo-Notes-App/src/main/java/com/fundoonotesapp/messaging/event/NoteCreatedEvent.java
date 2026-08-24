package com.fundoonotesapp.messaging.event;

import java.io.Serializable;

public record NoteCreatedEvent(

        Long noteId,
        Long userId,
        String title

) implements Serializable {
}