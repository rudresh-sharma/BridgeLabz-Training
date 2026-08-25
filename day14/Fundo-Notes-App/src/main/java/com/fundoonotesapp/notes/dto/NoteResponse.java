package com.fundoonotesapp.notes.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

import com.fundoonotesapp.notes.entity.Note.NoteStatus;

@Getter
@Builder
@AllArgsConstructor
public class NoteResponse {

    private final Long id;

    private final String title;

    private final String content;

    private final NoteStatus status;

    private final LocalDateTime createdAt;

    private final LocalDateTime updatedAt;
}