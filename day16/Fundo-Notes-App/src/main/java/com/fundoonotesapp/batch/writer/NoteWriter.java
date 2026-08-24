package com.fundoonotesapp.batch.writer;

import com.fundoonotesapp.notes.entity.Note;
import com.fundoonotesapp.notes.repository.NoteRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.batch.infrastructure.item.Chunk;
import org.springframework.batch.infrastructure.item.ItemWriter;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NoteWriter implements ItemWriter<Note> {

    private final NoteRepository noteRepository;

    @Override
    public void write(Chunk<? extends Note> chunk) {

        noteRepository.saveAll(chunk.getItems());

        System.out.println(
                "SAVED NOTES: " + chunk.size()
        );
    }
}