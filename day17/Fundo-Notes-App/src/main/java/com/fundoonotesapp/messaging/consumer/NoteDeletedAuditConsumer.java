package com.fundoonotesapp.messaging.consumer;

import java.time.LocalDateTime;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import com.fundoonotesapp.audit.entity.AuditLog;
import com.fundoonotesapp.audit.repository.AuditLogRepository;
import com.fundoonotesapp.messaging.config.RabbitMQConfig;
import com.fundoonotesapp.messaging.event.NoteDeletedEvent;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class NoteDeletedAuditConsumer {

    private final AuditLogRepository auditLogRepository;

    @RabbitListener(
            queues = RabbitMQConfig.NOTE_DELETED_AUDIT_QUEUE
    )
    public void handleNoteDeleted(NoteDeletedEvent event) {

        System.out.println(
                "AUDIT DELETE EVENT RECEIVED: " + event
        );

        AuditLog auditLog = new AuditLog();

        auditLog.setNoteId(event.noteId());
        auditLog.setUserId(event.userId());
        auditLog.setAction("NOTE_DELETED");
        auditLog.setNoteTitle(event.title());
        auditLog.setCreatedAt(LocalDateTime.now());

        auditLogRepository.save(auditLog);

        System.out.println(
                "AUDIT LOG SAVED: NOTE_DELETED FOR NOTE: "
                        + event.noteId()
        );
    }
}