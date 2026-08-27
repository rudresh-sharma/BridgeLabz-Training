package com.fundoo.reminderservice.reminder.exception;

public class DuplicateReminderException extends RuntimeException {
    public DuplicateReminderException(String message) {
        super(message);
    }
}