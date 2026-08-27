package com.fundoo.reminderservice.reminder.scheduler;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.fundoo.reminderservice.reminder.client.NoteClient;
import com.fundoo.reminderservice.reminder.client.NoteDto;
import com.fundoo.reminderservice.reminder.entity.Reminder;
import com.fundoo.reminderservice.reminder.event.ReminderDueEvent;
import com.fundoo.reminderservice.reminder.repository.ReminderRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j
public class ReminderScheduler {

    private static final String NOTIFICATION_TOPIC = "notification-events";

    private final ReminderRepository reminderRepository;
    private final KafkaTemplate<String, ReminderDueEvent> kafkaTemplate;
    private final NoteClient noteClient;

    /**
     * Runs every 60 seconds (matches monolith's fixedRate=60000).
     * Finds all reminders where notified=false AND reminderTime <= now,
     * publishes a REMINDER_DUE event to Kafka for each one,
     * then marks them as notified=true to prevent re-sending.
     */
    @Scheduled(fixedRateString = "${reminder.scheduler.fixed-rate-ms:60000}")
    @Transactional
    public void processReminders() {

        LocalDateTime now = LocalDateTime.now();

        List<Reminder> dueReminders =
                reminderRepository.findDueReminders(now);

        if (dueReminders.isEmpty()) {
            log.debug("SCHEDULER: No due reminders at {}", now);
            return;
        }

        log.info(
                "SCHEDULER: Found {} due reminder(s) at {}",
                dueReminders.size(),
                now
        );

        for (Reminder reminder : dueReminders) {

            try {
                NoteDto note = noteClient.getNoteById(reminder.getNoteId());



                ReminderDueEvent event = new ReminderDueEvent(
                        reminder.getId(),
                        reminder.getNoteId(),
                        reminder.getEmail(),
                        note.title(),
                        note.content(),
                        reminder.getReminderTime()
                );

                kafkaTemplate.send(
                        NOTIFICATION_TOPIC,
                        reminder.getEmail(),    // Kafka key — routes by user
                        event
                );


                // Mark as notified immediately to prevent double-sending
                // even if notification-service fails (at-least-once delivery)
                reminder.setNotified(true);
                reminderRepository.save(reminder);

              

                log.info(
                        "SCHEDULER: Published REMINDER_DUE for reminder ID {} | email={}",
                        reminder.getId(),
                        reminder.getEmail()
                );

            } catch (Exception ex) {

                log.error(
                        "SCHEDULER: Failed to process reminder ID {} | Error: {}",
                        reminder.getId(),
                        ex.getMessage()
                );
                // Do NOT mark as notified — retry on next scheduled run
            }
        }
    }
}
