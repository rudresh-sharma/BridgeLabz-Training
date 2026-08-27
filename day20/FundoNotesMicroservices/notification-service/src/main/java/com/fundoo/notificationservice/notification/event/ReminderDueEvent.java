package com.fundoo.notificationservice.notification.event;

import java.time.LocalDateTime;

/**
 * Kafka event consumed from 'notification-events' topic.
 * Published by reminder-service when a reminder is due.
 * Fields must match exactly what reminder-service serialises.
 */
public record ReminderDueEvent(

        // The reminder ID  used for acknowledgment / idempotency
        Long reminderId,

        // The note the reminder is for
        Long noteId,

        // The user who created the reminder
        String email,

         String title,

         String content,

        // The originally scheduled reminder time
        LocalDateTime reminderTime

) {
}
