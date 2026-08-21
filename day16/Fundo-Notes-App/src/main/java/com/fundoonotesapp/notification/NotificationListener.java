package com.fundoonotesapp.notification;

import com.fundoonotesapp.reminder.entity.Reminder;
import com.fundoonotesapp.reminder.repository.ReminderRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
public class NotificationListener {

    private final ReminderRepository reminderRepository;
    private final EmailService emailService;


    @JmsListener(destination = NotificationQueue.TEST_QUEUE)
    public void receiveTestMessage(String message) {

        System.out.println(
                "MESSAGE RECEIVED FROM QUEUE: " + message
        );
    }


    @JmsListener(destination = NotificationQueue.REMINDER_QUEUE)
    @Transactional
    public void receiveReminder(ReminderMessage message) {

        System.out.println(
                "REMINDER RECEIVED FROM JMS → ID: "
                        + message.reminderId()
        );

        Reminder reminder = reminderRepository
                .findById(message.reminderId())
                .orElseThrow(() ->
                        new RuntimeException("Reminder not found")
                );

        // Send actual email
        emailService.sendReminderEmail(
                message.email(),
                message.noteTitle(),
                message.noteContent()
        );

        // Only reached if email was sent successfully
        reminder.setNotified(true);

        reminderRepository.save(reminder);

        System.out.println(
                "REMINDER EMAIL SENT AND MARKED AS NOTIFIED → ID: "
                        + reminder.getId()
        );
    }
}