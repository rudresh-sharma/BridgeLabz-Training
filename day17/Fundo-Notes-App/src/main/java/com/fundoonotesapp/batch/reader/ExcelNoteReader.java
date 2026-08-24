package com.fundoonotesapp.batch.reader;

import com.fundoonotesapp.batch.dto.NoteExcelRow;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import org.springframework.batch.infrastructure.item.ItemReader;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class ExcelNoteReader
        implements ItemReader<NoteExcelRow> {

    private final FileInputStream inputStream;

    private final Workbook workbook;

    private final Sheet sheet;

    private int currentRow = 1;


    public ExcelNoteReader(String filePath) throws IOException {

        File file = new File(filePath);

        this.inputStream = new FileInputStream(file);

        this.workbook = new XSSFWorkbook(inputStream);

        this.sheet = workbook.getSheetAt(0);
    }


    @Override
    public NoteExcelRow read() {

        if (currentRow > sheet.getLastRowNum()) {
            return null;
        }

        Row row = sheet.getRow(currentRow++);

        if (row == null) {
            return read();
        }

        String title = row.getCell(0)
                .getStringCellValue();

        String content = row.getCell(1)
                .getStringCellValue();

        String status = row.getCell(2)
                .getStringCellValue();

        return new NoteExcelRow(
                title,
                content,
                status
        );
    }
}