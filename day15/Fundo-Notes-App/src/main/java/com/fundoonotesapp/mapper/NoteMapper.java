package com.fundoonotesapp.mapper;

import com.fundoonotesapp.notes.dto.CreateNoteRequest;
import com.fundoonotesapp.notes.dto.NoteResponse;
import com.fundoonotesapp.notes.entity.Note;
import com.fundoonotesapp.user.entity.User;

import org.springframework.stereotype.Component;

@Component
public class NoteMapper {

    public Note toEntity(
            CreateNoteRequest request,
            User user) {

        return Note.builder()
                .title(request.getTitle())
                .content(request.getContent())
                .user(user)
                .build();
    }


    public NoteResponse toResponse(Note note) {

        return NoteResponse.builder()
                .id(note.getId())
                .title(note.getTitle())
                .content(note.getContent())
                .status(note.getStatus())
                .createdAt(note.getCreatedAt())
                .updatedAt(note.getUpdatedAt())
                .build();
    }
}