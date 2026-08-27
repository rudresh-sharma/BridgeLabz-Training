package com.fundoo.notificationservice.notification.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import com.fundoo.notificationservice.notification.event.ReminderDueEvent;
import com.fundoo.notificationservice.notification.service.EmailService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class NotificationConsumer {

    private final EmailService emailService;


    @KafkaListener(
            topics = "notification-events",
            groupId = "notification-service-group"
    )
    public void handleReminderDueEvent(
            ReminderDueEvent event,
            @Header(KafkaHeaders.RECEIVED_PARTITION) int partition,
            @Header(KafkaHeaders.OFFSET) long offset
    ) {

        log.info(
                "NOTIFICATION: Received REMINDER_DUE | reminderId={} | noteId={} | email={} | partition={} | offset={}",
                event.reminderId(),
                event.noteId(),
                event.email(),
                partition,
                offset
        );

        try {

            emailService.sendReminderEmail(event);

        } catch (Exception ex) {

            // Log and allow Kafka to move on � email failure should not
            // crash the consumer or cause infinite retry on this offset.
            // For production: use a Dead Letter Topic (DLT).
            log.error(
                    "NOTIFICATION: Failed to send email for reminderId={} | Error: {}",
                    event.reminderId(),
                    ex.getMessage()
            );
        }
    }
}
