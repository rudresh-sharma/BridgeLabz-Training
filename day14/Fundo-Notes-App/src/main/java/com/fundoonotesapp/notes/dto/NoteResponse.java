package com.fundoonotesapp.notes.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@Builder
@AllArgsConstructor
public class NoteResponse {

    private final Long id;

    private final String title;

    private final String content;

    private final boolean pinned;

    private final boolean archived;

    private final boolean trashed;

    private final LocalDateTime createdAt;

    private final LocalDateTime updatedAt;
}