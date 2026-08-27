package com.fundoo.notesservice.mapper;

import java.util.List;

import org.springframework.stereotype.Component;

import com.fundoo.notesservice.labels.dto.LabelResponse;
import com.fundoo.notesservice.note.dto.NoteResponse;
import com.fundoo.notesservice.note.entity.Note;


@Component
public class NoteMapper {
	public NoteResponse mapToResponse(Note note) {

	    List<LabelResponse> labels = note.getLabels()
	            .stream()
	            .map(label -> new LabelResponse(
	                    label.getId(),
	                    label.getName()
	            ))
	            .toList();

	    return new NoteResponse(
	            note.getId(),
	            note.getTitle(),
	            note.getContent(),
	            note.getStatus().name(),
	            note.getCreatedAt(),
	            note.getUpdatedAt(),
	            labels
	    );
	}
}
