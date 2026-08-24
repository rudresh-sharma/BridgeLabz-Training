package com.fundoonotesapp.batch.processor;

import com.fundoonotesapp.batch.dto.NoteExcelRow;
import com.fundoonotesapp.notes.entity.Note;
import com.fundoonotesapp.notes.entity.Note.NoteStatus;
import com.fundoonotesapp.user.entity.User;
import com.fundoonotesapp.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.batch.infrastructure.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NoteProcessor
        implements ItemProcessor<NoteExcelRow, Note> {

    private final UserRepository userRepository;

    @Override
    public Note process(NoteExcelRow item) {

        User user = userRepository.findById(4L)
                .orElseThrow(() ->
                        new RuntimeException("User not found")
                );

        Note note = new Note();

        note.setTitle(item.title());
        note.setContent(item.content());

        note.setStatus(
                NoteStatus.valueOf(
                        item.status().toUpperCase()
                )
        );

        note.setUser(user);

        return note;
    }
}