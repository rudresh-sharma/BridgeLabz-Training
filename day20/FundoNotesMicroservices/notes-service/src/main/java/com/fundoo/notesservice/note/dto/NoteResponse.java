package com.fundoo.notesservice.note.dto;

import java.time.LocalDateTime;
import java.util.List;

import com.fundoo.notesservice.labels.dto.LabelResponse;

public record NoteResponse(

        Long id,

        String title,

        String content,

        String status,

        LocalDateTime createdAt,

        LocalDateTime updatedAt,

        List<LabelResponse> labels

) {
}