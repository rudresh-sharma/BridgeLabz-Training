package com.fundoonotesapp.search.mapper;

import com.fundoonotesapp.notes.entity.Note;
import com.fundoonotesapp.search.document.NoteDocument;
import com.fundoonotesapp.search.dto.SearchNoteResponse;
import org.springframework.stereotype.Component;

@Component
public class NoteDocumentMapper {

    public NoteDocument toDocument(Note note) {

        return NoteDocument.builder()
                .id(note.getId())
                .title(note.getTitle())
                .content(note.getContent())
                .status(note.getStatus())
                .userId(note.getUser().getId())
                .createdAt(note.getCreatedAt())
                .updatedAt(note.getUpdatedAt())
                .build();
    }

    public SearchNoteResponse toResponse(
            NoteDocument document) {

        return new SearchNoteResponse(
                document.getId(),
                document.getTitle(),
                document.getContent(),
                document.getStatus(),
                document.getCreatedAt(),
                document.getUpdatedAt()
        );
    }
}