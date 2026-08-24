package com.fundoonotesapp.notification;

import java.io.Serializable;

public record ReminderMessage(
        Long reminderId,
        Long userId,
        String fullname,
        String email,
        String noteTitle,
        String noteContent
) implements Serializable {
}