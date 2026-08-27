package com.fundoo.notesservice.audit;

import java.time.LocalDateTime;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.fundoo.notesservice.note.event.NoteEvent;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class NoteAuditConsumer {

    private final AuditLogRepository auditLogRepository;

    @KafkaListener(
            topics = "note-events",
            groupId = "notes-audit-group"
    )
    public void handleNoteEvent(NoteEvent event) {

        log.info(
                "AUDIT: Received {} event for note ID {}",
                event.eventType(),
                event.noteId()
        );

        AuditLog auditLog = AuditLog.builder()
                .noteId(event.noteId())
                .email(event.email())
                .action(event.eventType())
                .noteTitle(event.title())
                .createdAt(LocalDateTime.now())
                .build();

        auditLogRepository.save(auditLog);

        log.info(
                "AUDIT: Saved audit log for note ID {} | action={}",
                event.noteId(),
                event.eventType()
        );
    }
}
