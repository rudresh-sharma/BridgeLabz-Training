package com.fundoo.reminderservice.reminder.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

public record ReminderRequest(

        @NotNull(message = "Note ID is required")
        Long noteId,

        @NotNull(message = "Reminder time is required")
        @Future(message = "Reminder time must be in the future")
        @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
        LocalDateTime reminderTime

) {
}
