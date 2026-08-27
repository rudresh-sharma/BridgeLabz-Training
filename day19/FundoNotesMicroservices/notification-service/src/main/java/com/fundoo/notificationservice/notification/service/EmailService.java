package com.fundoo.notificationservice.notification.service;

import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.fundoo.notificationservice.notification.event.ReminderDueEvent;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailService {

    private final JavaMailSender mailSender;

    @Value("\")
    private String fromAddress;

    @Value("\")
    private String fromName;

    private static final DateTimeFormatter DISPLAY_FORMAT =
            DateTimeFormatter.ofPattern("dd MMM yyyy, hh:mm a");


    public void sendReminderEmail(ReminderDueEvent event) {

        try {

            SimpleMailMessage message = new SimpleMailMessage();

            message.setFrom(fromName + " <" + fromAddress + ">");
            message.setTo(event.email());
            message.setSubject("Fundo Notes - Reminder for Note #" + event.noteId());
            message.setText(buildReminderBody(event));

            mailSender.send(message);

            log.info(
                    "EMAIL SENT: reminder for note ID {} to {}",
                    event.noteId(),
                    event.email()
            );

        } catch (MailException ex) {

            log.error(
                    "EMAIL FAILED: reminder ID {} to {} | Error: {}",
                    event.reminderId(),
                    event.email(),
                    ex.getMessage()
            );

            // Re-throw so NotificationConsumer can decide retry strategy
            throw ex;
        }
    }


    private String buildReminderBody(ReminderDueEvent event) {

        return """
                Hi,

                This is a reminder from Fundo Notes.

                Your reminder for Note #%d was scheduled for:
                  %s

                Please open Fundo Notes to view your note.

                ---
                Fundo Notes Team
                (This is an automated message. Do not reply.)
                """.formatted(
                        event.noteId(),
                        event.reminderTime().format(DISPLAY_FORMAT)
                );
    }
}
