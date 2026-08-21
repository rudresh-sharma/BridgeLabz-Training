package com.fundoonotesapp.reminder.dto;

import java.time.LocalDateTime;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;

public record UpdateReminderRequest(

        @NotNull(message = "Reminder time is required")
        @Future(message = "Reminder time must be in the future")
        LocalDateTime reminderTime

) {
}