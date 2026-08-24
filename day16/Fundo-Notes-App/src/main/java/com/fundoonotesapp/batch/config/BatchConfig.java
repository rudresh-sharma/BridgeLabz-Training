package com.fundoonotesapp.batch.config;

import com.fundoonotesapp.batch.dto.NoteExcelRow;
import com.fundoonotesapp.batch.processor.NoteProcessor;
import com.fundoonotesapp.batch.reader.ExcelNoteReader;
import com.fundoonotesapp.batch.writer.NoteWriter;
import com.fundoonotesapp.notes.entity.Note;

import lombok.RequiredArgsConstructor;
import com.fundoonotesapp.batch.reader.CsvNoteReader;
import org.springframework.batch.core.job.Job;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.Step;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.infrastructure.item.ItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
@RequiredArgsConstructor
public class BatchConfig {

    private final JobRepository jobRepository;
    private final PlatformTransactionManager transactionManager;
    private final NoteProcessor noteProcessor;
    private final NoteWriter noteWriter;


    @Bean
    public ItemReader<NoteExcelRow> csvNoteReader() throws Exception {

        return new CsvNoteReader(
                "notes.csv"
        );
    }
    @Bean
    public Step importNotesStep(
            ItemReader<NoteExcelRow> csvNoteReader
    ) {

        return new StepBuilder(
                "importNotesStep",
                jobRepository
        )
        .<NoteExcelRow, Note>chunk(
                5,
                transactionManager
        )
        .reader(csvNoteReader)
        .processor(noteProcessor)
        .writer(noteWriter)
        .build();
    }
    @Bean
    public Job importNotesJob(
            Step importNotesStep
    ) {

        return new JobBuilder(
                "importNotesJob",
                jobRepository
        )
        .start(importNotesStep)
        .build();
    }
}