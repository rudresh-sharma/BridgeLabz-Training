package com.fundoonotesapp.reminder.entity;

import java.time.LocalDateTime;

import com.fundoonotesapp.notes.entity.Note;
import com.fundoonotesapp.user.entity.User;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(
    name = "reminders",
    uniqueConstraints = {
        @UniqueConstraint(
            name = "uk_reminder_note_user",
            columnNames = {"note_id", "user_id"}
        )
    }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Reminder {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "reminder_time", nullable = false)
    private LocalDateTime reminderTime;

    @Column(nullable = false)
    private boolean notified = false;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
        name = "note_id",
        nullable = false,
        foreignKey = @ForeignKey(name = "fk_reminder_note")
    )
    private Note note;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(
        name = "user_id",
        nullable = false,
        foreignKey = @ForeignKey(name = "fk_reminder_user")
    )
    private User user;
}