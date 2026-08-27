package com.fundoo.reminderservice.reminder.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "reminders",
uniqueConstraints = @UniqueConstraint(
        name = "uq_reminder_note_email_time",
        columnNames = {"note_id", "email", "reminder_time"}))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reminder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "note_id", nullable = false)
    private Long noteId;

    @Column(nullable = false)
    private String email;

    @Column(name = "reminder_time", nullable = false)
    private LocalDateTime reminderTime;

    // Set to true by ReminderScheduler after notification is sent
    @Column(nullable = false)
    @Builder.Default
    private boolean notified = false;

    @Column(name = "created_at", nullable = false)
    private LocalDateTime createdAt;
}
