package com.fundoo.reminderservice.reminder.dto;

import java.time.LocalDateTime;

public record ReminderResponse(

        Long id,

        Long noteId,

        String email,

        LocalDateTime reminderTime,

        boolean notified,

        LocalDateTime createdAt

) {
}
