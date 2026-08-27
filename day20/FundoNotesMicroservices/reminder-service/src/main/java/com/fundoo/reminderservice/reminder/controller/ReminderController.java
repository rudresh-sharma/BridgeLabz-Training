package com.fundoo.reminderservice.reminder.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fundoo.reminderservice.reminder.dto.ReminderRequest;
import com.fundoo.reminderservice.reminder.dto.ReminderResponse;
import com.fundoo.reminderservice.reminder.service.ReminderService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/reminders")
@RequiredArgsConstructor
public class ReminderController {

    private final ReminderService reminderService;


    @PostMapping
    public ResponseEntity<ReminderResponse> createReminder(
            @Valid @RequestBody ReminderRequest request,
            HttpServletRequest httpRequest
    ) {

        String email = (String) httpRequest.getAttribute("email");

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(reminderService.createReminder(email, request));
    }


    @GetMapping
    public ResponseEntity<List<ReminderResponse>> getMyReminders(
            HttpServletRequest httpRequest
    ) {

        String email = (String) httpRequest.getAttribute("email");

        return ResponseEntity.ok(
                reminderService.getMyReminders(email)
        );
    }


    @GetMapping("/{id}")
    public ResponseEntity<ReminderResponse> getReminderById(
            @PathVariable Long id,
            HttpServletRequest httpRequest
    ) {

        String email = (String) httpRequest.getAttribute("email");

        return ResponseEntity.ok(
                reminderService.getReminderById(id, email)
        );
    }


    @PutMapping("/{id}")
    public ResponseEntity<ReminderResponse> updateReminder(
            @PathVariable Long id,
            @Valid @RequestBody ReminderRequest request,
            HttpServletRequest httpRequest
    ) {

        String email = (String) httpRequest.getAttribute("email");

        return ResponseEntity.ok(
                reminderService.updateReminder(id, email, request)
        );
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReminder(
            @PathVariable Long id,
            HttpServletRequest httpRequest
    ) {

        String email = (String) httpRequest.getAttribute("email");

        reminderService.deleteReminder(id, email);

        return ResponseEntity.noContent().build();
    }
}
