package com.fundoonotesapp.reminder.scheduler;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.fundoonotesapp.notification.NotificationProducer;
import com.fundoonotesapp.notification.ReminderMessage;
import com.fundoonotesapp.reminder.entity.Reminder;
import com.fundoonotesapp.reminder.repository.ReminderRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class ReminderScheduler {

    private final ReminderRepository reminderRepository;
    private final NotificationProducer notificationProducer;
    
    @Scheduled(fixedRate = 60000) // every 1 minute
    @Transactional
    public void checkDueReminders() {

        LocalDateTime now = LocalDateTime.now();

        List<Reminder> dueReminders =
                reminderRepository
                        .findDueReminders(now);

        if (dueReminders.isEmpty()) {
            return;
        }

        System.out.println(
                "DUE REMINDERS FOUND: " + dueReminders.size()
        );

        dueReminders.forEach(reminder -> {

            ReminderMessage message = new ReminderMessage(
                    reminder.getId(),
                    reminder.getUser().getId(),
                    reminder.getUser().getName(),
                    reminder.getUser().getEmail(),
                    reminder.getNote().getTitle(),
                    reminder.getNote().getContent()
            );

            notificationProducer.sendReminder(message);
        });
    }
}