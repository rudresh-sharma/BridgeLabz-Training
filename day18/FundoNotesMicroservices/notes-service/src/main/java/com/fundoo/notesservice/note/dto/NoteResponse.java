package com.fundoo.notesservice.note.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record NoteResponse(

        Long id,

        String title,

        String content,

        String status,

        LocalDateTime createdAt,

        LocalDateTime updatedAt

) {
}