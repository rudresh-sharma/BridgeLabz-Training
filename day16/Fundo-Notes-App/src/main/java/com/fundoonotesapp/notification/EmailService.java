package com.fundoonotesapp.notification;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class EmailService {

	private final JavaMailSender mailSender;

	public void sendRegistrationEmail(String fullname, String to) throws MessagingException {

		MimeMessage message = mailSender.createMimeMessage();

		MimeMessageHelper helper = new MimeMessageHelper(message, true, "UTF-8");

		helper.setTo(to);

		helper.setSubject("Welcome to Fundoo Notes 🎉");

		String html = """
				<!DOCTYPE html>
				<html>
				<body style="font-family: Arial, sans-serif;">

				    <h2>Welcome, %s 👋</h2>

				    <p>
				        Your account has been successfully created.
				    </p>

				    <p>
				        You can now start creating and managing your notes.
				    </p>

				    <hr>

				    <p>
				        <b>Fundoo Notes App</b>
				    </p>

				</body>
				</html>
				""".formatted(fullname);

		helper.setText(html, true);

		mailSender.send(message);
	}

	public void sendReminderEmail(String fullname, String to, String noteTitle, String noteContent) {

		SimpleMailMessage message = new SimpleMailMessage();

		message.setTo(to);

		message.setSubject("Reminder: " + noteTitle);

		message.setText("""
				Hello %s,

				This is a reminder for your note.

				Title: %s

				Content:
				%s

				---
				Fundoo Notes App
				""".formatted(fullname, noteTitle, noteContent));

		mailSender.send(message);

		System.out.println("REMINDER EMAIL SENT TO: " + to);
	}
}