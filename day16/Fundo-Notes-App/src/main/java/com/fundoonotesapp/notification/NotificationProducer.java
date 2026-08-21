package com.fundoonotesapp.notification;

import lombok.RequiredArgsConstructor;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationProducer {

    private final JmsTemplate jmsTemplate;

    public void sendTestMessage(String message) {

        jmsTemplate.convertAndSend(
                NotificationQueue.TEST_QUEUE,
                message
        );

        System.out.println("MESSAGE SENT TO QUEUE: " + message);
    }

    public void sendReminder(
            ReminderMessage reminderMessage
    ) {

        jmsTemplate.convertAndSend(
                NotificationQueue.REMINDER_QUEUE,
                reminderMessage
        );

        System.out.println(
                "REMINDER SENT TO JMS → ID: "
                        + reminderMessage.reminderId()
        );
    }
}