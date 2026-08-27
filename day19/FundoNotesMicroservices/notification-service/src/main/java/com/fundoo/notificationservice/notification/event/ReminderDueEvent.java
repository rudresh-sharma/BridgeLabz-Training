package com.fundoo.notificationservice.notification.event;

import java.time.LocalDateTime;

/**
 * Kafka event consumed from 'notification-events' topic.
 * Published by reminder-service when a reminder is due.
 * Fields must match exactly what reminder-service serialises.
 */
public record ReminderDueEvent(

        Long reminderId,

        Long noteId,

        String email,

        LocalDateTime reminderTime

) {
}
