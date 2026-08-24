package com.fundoonotesapp.batch.export;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import org.springframework.stereotype.Service;

import com.fundoonotesapp.notes.entity.Note;
import com.fundoonotesapp.notes.repository.NoteRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class NoteCsvExportService {

    private final NoteRepository noteRepository;

    public void exportNotes() throws IOException {

        List<Note> notes = noteRepository.findAll();

        try (FileWriter writer =
                     new FileWriter("notes-export.csv")) {

            // Header
            writer.write("id,title,content,status,userId\n");

            for (Note note : notes) {

                writer.write(
                        note.getId() + ","
                                + note.getTitle() + ","
                                + note.getContent() + ","
                                + note.getStatus() + ","
                                + note.getUser().getId()
                                + "\n"
                );
            }
        }

        System.out.println(
                "CSV EXPORTED SUCCESSFULLY"
        );
    }
}