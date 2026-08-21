package com.fundoonotesapp.notification;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;


    public void sendReminderEmail(
            String to,
            String noteTitle,
            String noteContent
    ) {

        SimpleMailMessage message =
                new SimpleMailMessage();

        message.setTo(to);

        message.setSubject(
                "Reminder: " + noteTitle
        );

        message.setText(
                """
                Hello,

                This is a reminder for your note.

                Title: %s

                Content:
                %s

                ---
                Fundoo Notes App
                """.formatted(
                        noteTitle,
                        noteContent
                )
        );

        mailSender.send(message);

        System.out.println(
                "REMINDER EMAIL SENT TO: " + to
        );
    }
}