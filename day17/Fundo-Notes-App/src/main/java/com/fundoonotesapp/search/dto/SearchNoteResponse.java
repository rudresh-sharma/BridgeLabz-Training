package com.fundoonotesapp.search.dto;

import com.fundoonotesapp.notes.entity.Note.NoteStatus;


import java.time.LocalDateTime;

public record SearchNoteResponse(

        Long id,

        String title,

        String content,

        NoteStatus status,

        LocalDateTime createdAt,

        LocalDateTime updatedAt

) {
}