package com.fundoonotesapp.reminder.service;

import java.security.Principal;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fundoonotesapp.notes.entity.Note;
import com.fundoonotesapp.notes.repository.NoteRepository;
import com.fundoonotesapp.reminder.dto.ReminderRequest;
import com.fundoonotesapp.reminder.dto.ReminderResponse;
import com.fundoonotesapp.reminder.dto.UpdateReminderRequest;
import com.fundoonotesapp.reminder.entity.Reminder;
import com.fundoonotesapp.reminder.repository.ReminderRepository;
import com.fundoonotesapp.user.entity.User;
import com.fundoonotesapp.user.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReminderService {

	private final ReminderRepository reminderRepository;
	private final NoteRepository noteRepository;
	private final UserRepository userRepository;

	// =========================
	// CREATE REMINDER
	// =========================

	@Transactional
	public ReminderResponse createReminder(ReminderRequest request, Principal principal) {

		User user = getCurrentUser(principal);

		// Find note and verify ownership
		Note note = noteRepository.findByIdAndUserId(request.noteId(), user.getId())
				.orElseThrow(() -> new RuntimeException("Note not found"));

		// Check if reminder already exists for this note
		if (reminderRepository.findByNoteIdAndUserId(note.getId(), user.getId()).isPresent()) {

			throw new RuntimeException("Reminder already exists for this note");
		}

		Reminder reminder = Reminder.builder().note(note).user(user).reminderTime(request.reminderTime())
				.notified(false).build();

		Reminder savedReminder = reminderRepository.save(reminder);

		return mapToResponse(savedReminder);
	}

	// =========================
	// GET MY REMINDERS
	// =========================

	@Transactional(readOnly = true)
	public List<ReminderResponse> getMyReminders(Principal principal) {

		User user = getCurrentUser(principal);

		return reminderRepository.findByUserId(user.getId()).stream().map(this::mapToResponse).toList();
	}

	// =========================
	// GET CURRENT USER
	// =========================

	private User getCurrentUser(Principal principal) {

		return userRepository.findByEmail(principal.getName().toLowerCase())
				.orElseThrow(() -> new RuntimeException("User not found"));
	}

	// =========================
	// MAP ENTITY TO RESPONSE
	// =========================

	private ReminderResponse mapToResponse(Reminder reminder) {

		return new ReminderResponse(reminder.getId(), reminder.getNote().getId(), reminder.getReminderTime(),
				reminder.isNotified());
	}

	// =========================
	// UPDATE REMINDER
	// =========================

	@Transactional
	public ReminderResponse updateReminder(Long reminderId, UpdateReminderRequest request, Principal principal) {

		User user = getCurrentUser(principal);

		Reminder reminder = reminderRepository.findById(reminderId)
				.orElseThrow(() -> new RuntimeException("Reminder not found"));

		// Security: verify ownership
		if (!reminder.getUser().getId().equals(user.getId())) {
			throw new RuntimeException("Reminder not found");
		}

		reminder.setReminderTime(request.reminderTime());

		// If reminder was already notified, make it active again
		reminder.setNotified(false);

		Reminder updatedReminder = reminderRepository.save(reminder);

		return mapToResponse(updatedReminder);
	}

	// =========================
	// DELETE REMINDER
	// =========================

	@Transactional
	public void deleteReminder(Long reminderId, Principal principal) {

		User user = getCurrentUser(principal);

		Reminder reminder = reminderRepository.findById(reminderId)
				.orElseThrow(() -> new RuntimeException("Reminder not found"));

		// Security: verify ownership
		if (!reminder.getUser().getId().equals(user.getId())) {
			throw new RuntimeException("Reminder not found");
		}

		reminderRepository.delete(reminder);
	}
}