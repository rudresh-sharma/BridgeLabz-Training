package com.fundoo.notesservice.mapper;

import org.springframework.stereotype.Component;

import com.fundoo.notesservice.note.dto.NoteResponse;
import com.fundoo.notesservice.note.entity.Note;


@Component
public class NoteMapper {
	 public NoteResponse mapToResponse(Note note) {

	        return new NoteResponse(
	                note.getId(),
	                note.getTitle(),
	                note.getContent(),
	                note.getStatus().name(),
	                note.getCreatedAt(),
	                note.getUpdatedAt()
	        );
	    }
}
