package com.fundoo.reminderservice.reminder.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.fundoo.reminderservice.reminder.dto.ReminderRequest;
import com.fundoo.reminderservice.reminder.dto.ReminderResponse;
import com.fundoo.reminderservice.reminder.entity.Reminder;
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

        if (reminderRepository.existsByNoteIdAndEmail(
                request.noteId(), email)) {

            throw new RuntimeException(
                    "A reminder already exists for note ID "
                            + request.noteId()
            );
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


    public ReminderResponse updateReminder(
            Long reminderId,
            String email,
            ReminderRequest request
    ) {

        Reminder reminder =
                reminderRepository.findByIdAndEmail(reminderId, email)
                        .orElseThrow(() ->
                                new RuntimeException("Reminder not found"));

        reminder.setReminderTime(request.reminderTime());
        // Updating reminder time resets the notified flag
        reminder.setNotified(false);

        return mapToResponse(reminderRepository.save(reminder));
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
