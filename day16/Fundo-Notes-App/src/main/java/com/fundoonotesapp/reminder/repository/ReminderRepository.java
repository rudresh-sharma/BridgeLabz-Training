package com.fundoonotesapp.reminder.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fundoonotesapp.reminder.entity.Reminder;

public interface ReminderRepository extends JpaRepository<Reminder, Long> {

	Optional<Reminder> findByNoteIdAndUserId(Long noteId, Long userId);

	List<Reminder> findByNotifiedFalseAndReminderTimeLessThanEqual(LocalDateTime currentTime);

	List<Reminder> findByUserId(Long userId);
}