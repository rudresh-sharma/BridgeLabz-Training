package com.fundoonotesapp.reminder.repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.fundoonotesapp.reminder.entity.Reminder;

public interface ReminderRepository extends JpaRepository<Reminder, Long> {

	Optional<Reminder> findByNoteIdAndUserId(Long noteId, Long userId);

	@Query("""
			SELECT r
			FROM Reminder r
			WHERE r.notified = false
			AND r.reminderTime <= :currentTime
			""")
	List<Reminder> findDueReminders(@Param("currentTime") LocalDateTime currentTime);

	List<Reminder> findByUserId(Long userId);
}