package com.fundoonotesapp.search.document;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import com.fundoonotesapp.notes.entity.Note.NoteStatus;

import java.time.LocalDateTime;

@Document(indexName = "notes")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class NoteDocument {

    @Id
    private Long id;

    private String title;

    private String content;

    private NoteStatus status;

    private Long userId;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}