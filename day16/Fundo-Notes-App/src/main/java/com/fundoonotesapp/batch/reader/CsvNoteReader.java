package com.fundoonotesapp.batch.reader;

import com.fundoonotesapp.batch.dto.NoteExcelRow;

import org.springframework.batch.infrastructure.item.ItemReader;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CsvNoteReader implements ItemReader<NoteExcelRow> {

    private final BufferedReader reader;

    public CsvNoteReader(String filePath) throws IOException {

        reader = new BufferedReader(
                new FileReader(filePath)
        );

        // Skip header
        reader.readLine();
    }

    @Override
    public NoteExcelRow read() throws IOException {

        String line = reader.readLine();

        if (line == null) {
            return null;
        }

        String[] values = line.split(",", -1);

        return new NoteExcelRow(
                values[0],
                values[1],
                values[2]
        );
    }
}