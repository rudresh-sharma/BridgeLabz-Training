package com.fundoo.reminderservice.reminder.event;

import java.time.LocalDateTime;

/**
 * Kafka event published to 'notification-events' topic
 * when a reminder's reminderTime has passed and it has not yet been notified.
 * Consumed by notification-service to send the reminder email.
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
