package com.fundoo.reminderservice.reminder.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.fundoo.reminderservice.reminder.dto.ReminderRequest;
import com.fundoo.reminderservice.reminder.dto.ReminderResponse;
import com.fundoo.reminderservice.reminder.entity.Reminder;
import com.fundoo.reminderservice.reminder.exception.DuplicateReminderException;
import com.fundoo.reminderservice.reminder.repository.ReminderRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReminderService {

    private final ReminderRepository reminderRepository;


    public ReminderResponse createReminder(
            String email,
            ReminderRequest request
    ) {
        if (reminderRepository.existsByNoteIdAndEmailAndReminderTime(
                request.noteId(), email, request.reminderTime())) {
            throw new DuplicateReminderException(
                    "A reminder already exists for note ID " + request.noteId()
                            + " at " + request.reminderTime());
        }
        Reminder reminder = Reminder.builder()
                .noteId(request.noteId())
                .email(email)
                .reminderTime(request.reminderTime())
                .notified(false)
                .createdAt(LocalDateTime.now())
                .build();
        return mapToResponse(reminderRepository.save(reminder));
    }



    public ReminderResponse updateReminder(
            Long reminderId,
            String email,
            ReminderRequest request
    ) {
        Reminder reminder =
                reminderRepository.findByIdAndEmail(reminderId, email)
                        .orElseThrow(() ->
                                new RuntimeException("Reminder not found"));

        // Prevent updating into a time that collides with another existing reminder
        if (!reminder.getReminderTime().equals(request.reminderTime())
                && reminderRepository.existsByNoteIdAndEmailAndReminderTime(
                        reminder.getNoteId(), email, request.reminderTime())) {
            throw new DuplicateReminderException(
                    "A reminder already exists for note ID " + reminder.getNoteId()
                            + " at " + request.reminderTime());
        }

        reminder.setReminderTime(request.reminderTime());
        reminder.setNotified(false);
        return mapToResponse(reminderRepository.save(reminder));
    }

    

    public List<ReminderResponse> getMyReminders(String email) {

        return reminderRepository.findByEmail(email)
                .stream()
                .map(this::mapToResponse)
                .toList();
    }


    public ReminderResponse getReminderById(
            Long reminderId,
            String email
    ) {

        Reminder reminder =
                reminderRepository.findByIdAndEmail(reminderId, email)
                        .orElseThrow(() ->
                                new RuntimeException("Reminder not found"));

        return mapToResponse(reminder);
    }


   


    public void deleteReminder(
            Long reminderId,
            String email
    ) {

        Reminder reminder =
                reminderRepository.findByIdAndEmail(reminderId, email)
                        .orElseThrow(() ->
                                new RuntimeException("Reminder not found"));

        reminderRepository.delete(reminder);
    }


    private ReminderResponse mapToResponse(Reminder reminder) {

        return new ReminderResponse(
                reminder.getId(),
                reminder.getNoteId(),
                reminder.getEmail(),
                reminder.getReminderTime(),
                reminder.isNotified(),
                reminder.getCreatedAt()
        );
    }
}
