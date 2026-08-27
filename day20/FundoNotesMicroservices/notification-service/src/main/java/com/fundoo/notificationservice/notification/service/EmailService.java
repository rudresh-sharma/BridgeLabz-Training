package com.fundoo.notificationservice.notification.service;

import java.time.format.DateTimeFormatter;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.fundoo.notificationservice.notification.event.ReminderDueEvent;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class EmailService {

    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String fromAddress;

    @Value("${app.mail.from-name}")
    private String fromName;

    private static final DateTimeFormatter DISPLAY_FORMAT =
            DateTimeFormatter.ofPattern("dd MMM yyyy, hh:mm a");

    public void sendReminderEmail(ReminderDueEvent event) {
        try {
            MimeMessage mimeMessage = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, true, "UTF-8");

            helper.setFrom(fromAddress, fromName);
            helper.setTo(event.email());
            helper.setSubject("Fundo Notes - Reminder for \"" + event.title() + "\"");
            helper.setText(buildReminderHtmlBody(event), true); // true = isHtml

            mailSender.send(mimeMessage);

            log.info(
                    "EMAIL SENT: reminder for note ID {} to {}",
                    event.noteId(),
                    event.email()
            );
        } catch (MessagingException | java.io.UnsupportedEncodingException ex) {
            log.error(
                    "EMAIL BUILD FAILED: reminder ID {} to {} | Error: {}",
                    event.reminderId(),
                    event.email(),
                    ex.getMessage()
            );
            throw new RuntimeException("Failed to build reminder email", ex);
        } catch (MailException ex) {
            log.error(
                    "EMAIL SEND FAILED: reminder ID {} to {} | Error: {}",
                    event.reminderId(),
                    event.email(),
                    ex.getMessage()
            );
            // Re-throw so NotificationConsumer can decide retry strategy
            throw ex;
        }
    }

    private String buildReminderHtmlBody(ReminderDueEvent event) {
        return """
                <html>
                <body style="margin:0; padding:0; background-color:#f4f4f7; font-family:Segoe UI, Arial, sans-serif;">
                  <table width="100%%" cellpadding="0" cellspacing="0" style="padding:30px 0;">
                    <tr>
                      <td align="center">
                        <table width="480" cellpadding="0" cellspacing="0"
                               style="background-color:#ffffff; border-radius:12px; overflow:hidden; box-shadow:0 2px 8px rgba(0,0,0,0.08);">

                          <tr>
                            <td style="background: linear-gradient(135deg, #6a5af9, #d66efd); padding:24px 32px;">
                              <h2 style="margin:0; color:#ffffff; font-size:20px;">🔔 Fundo Notes Reminder</h2>
                            </td>
                          </tr>

                          <tr>
                            <td style="padding:28px 32px;">
                              <p style="font-size:15px; color:#333333; margin:0 0 16px;">Hi,</p>
                              <p style="font-size:15px; color:#333333; margin:0 0 20px;">
                                Your scheduled reminder is due for the note below:
                              </p>

                              <div style="background-color:#f0f0ff; border-left:4px solid #6a5af9; border-radius:6px; padding:16px 18px; margin-bottom:20px;">
                                <p style="margin:0 0 6px; font-size:16px; font-weight:600; color:#4b32c3;">
                                  %s
                                </p>
                                <p style="margin:0; font-size:14px; color:#555555; line-height:1.5;">
                                  %s
                                </p>
                              </div>

                              <table cellpadding="0" cellspacing="0" style="margin-bottom:24px;">
                                <tr>
                                  <td style="background-color:#fff4e5; border-radius:6px; padding:8px 14px;">
                                    <span style="font-size:13px; color:#b25e00; font-weight:600;">
                                      ⏰ Scheduled for: %s
                                    </span>
                                  </td>
                                </tr>
                              </table>

                              <p style="font-size:14px; color:#333333; margin:0 0 24px;">
                                Please open Fundo Notes to view the full note.
                              </p>

                              <p style="font-size:13px; color:#999999; margin:0;">
                                — Fundo Notes Team
                              </p>
                            </td>
                          </tr>

                          <tr>
                            <td style="background-color:#fafafa; padding:14px 32px; text-align:center;">
                              <p style="font-size:11px; color:#aaaaaa; margin:0;">
                                This is an automated message. Please do not reply.
                              </p>
                            </td>
                          </tr>

                        </table>
                      </td>
                    </tr>
                  </table>
                </body>
                </html>
                """.formatted(
                        escapeHtml(event.title()),
                        escapeHtml(event.content()),
                        event.reminderTime().format(DISPLAY_FORMAT)
                );
    }

    /**
     * Minimal HTML escaping so note title/content can't break the email markup
     * or allow injected HTML/script content.
     */
    private String escapeHtml(String input) {
        if (input == null) return "";
        return input
                .replace("&", "&amp;")
                .replace("<", "&lt;")
                .replace(">", "&gt;")
                .replace("\"", "&quot;")
                .replace("'", "&#39;");
    }
}