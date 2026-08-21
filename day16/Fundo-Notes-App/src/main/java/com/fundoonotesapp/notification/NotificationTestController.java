package com.fundoonotesapp.notification;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test/jms")
@RequiredArgsConstructor
public class NotificationTestController {

    private final NotificationProducer notificationProducer;

    @PostMapping
    public ResponseEntity<String> sendMessage(
            @RequestParam String message
    ) {

        notificationProducer.sendTestMessage(message);

        return ResponseEntity.ok(
                "Message sent successfully to JMS queue"
        );
    }
}