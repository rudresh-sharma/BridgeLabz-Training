package com.fundoo.notesservice.note.scheduler;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.fundoo.notesservice.note.entity.Note;
import com.fundoo.notesservice.note.event.NoteEvent;
import com.fundoo.notesservice.note.kafka.NoteEventProducer;
import com.fundoo.notesservice.repository.NoteRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

/**
 * Batch job that permanently deletes notes which have been in TRASHED state
 * for longer than the configured retention period (default: 30 days).
 *
 * Runs daily at 02:00 AM (configurable via notes.batch.cron).
 *
 * After each hard-delete, a NOTE_DELETED Kafka event is published so
 * search-service removes the document from Elasticsearch.
 */
@Component
@RequiredArgsConstructor
@Slf4j
public class NoteCleanupScheduler {

    private final NoteRepository noteRepository;
    private final NoteEventProducer noteEventProducer;

    @Value("${notes.batch.trash-retention-days:30}")
    private int trashRetentionDays;


    @Scheduled(cron = "${notes.batch.cron:0 0 2 * * *}")
    @Transactional
    public void cleanUpTrashedNotes() {

        LocalDateTime cutoff =
                LocalDateTime.now().minusDays(trashRetentionDays);

        List<Note> expiredNotes =
                noteRepository.findByStatusAndUpdatedAtBefore(
                        Note.NoteStatus.TRASHED,
                        cutoff
                );

        if (expiredNotes.isEmpty()) {
            log.info(
                    "BATCH CLEANUP: No trashed notes older than {} days found.",
                    trashRetentionDays
            );
            return;
        }

        log.info(
                "BATCH CLEANUP: Found {} note(s) trashed before {}. Deleting...",
                expiredNotes.size(),
                cutoff
        );

        int deleted = 0;
        int failed  = 0;

        for (Note note : expiredNotes) {

            try {

                // 1. Publish DELETED event BEFORE deleting from DB
                //    so search-service can remove from Elasticsearch
                noteEventProducer.sendNoteEvent(new NoteEvent(
                        "DELETED",
                        note.getId(),
                        note.getTitle(),
                        note.getContent(),
                        note.getEmail(),
                        note.getStatus().name()
                ));

                // 2. Hard-delete from MySQL
                noteRepository.delete(note);

                deleted++;

                log.info(
                        "BATCH CLEANUP: Permanently deleted note ID {} (owner: {})",
                        note.getId(),
                        note.getEmail()
                );

            } catch (Exception ex) {

                failed++;

                log.error(
                        "BATCH CLEANUP: Failed to delete note ID {} | Error: {}",
                        note.getId(),
                        ex.getMessage()
                );
                // Continue processing remaining notes
            }
        }

        log.info(
                "BATCH CLEANUP: Complete. Deleted={} | Failed={}",
                deleted,
                failed
        );
    }
}
