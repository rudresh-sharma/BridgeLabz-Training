package com.fundoonotesapp.reminder.dto;

import java.time.LocalDateTime;

public record ReminderResponse(

        Long id,

        Long noteId,

        LocalDateTime reminderTime,

        boolean notified

) {
}