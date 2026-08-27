package com.fundoo.reminderservice.reminder.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.fundoo.reminderservice.reminder.entity.Reminder;

public interface ReminderRepository
        extends JpaRepository<Reminder, Long> {

    List<Reminder> findByEmail(String email);

    Optional<Reminder> findByIdAndEmail(Long id, String email);

    // Changed: now checks note + email + exact reminder time
    boolean existsByNoteIdAndEmailAndReminderTime(
            Long noteId, String email, LocalDateTime reminderTime);

    @Query("SELECT r FROM Reminder r " +
           "WHERE r.notified = false " +
           "AND r.reminderTime <= :now")
    List<Reminder> findDueReminders(LocalDateTime now);
}