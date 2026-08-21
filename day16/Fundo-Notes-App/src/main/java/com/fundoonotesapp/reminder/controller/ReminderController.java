package com.fundoonotesapp.reminder.controller;

import java.security.Principal;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.fundoonotesapp.reminder.dto.ReminderRequest;
import com.fundoonotesapp.reminder.dto.ReminderResponse;
import com.fundoonotesapp.reminder.dto.UpdateReminderRequest;
import com.fundoonotesapp.reminder.service.ReminderService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/reminders")
@RequiredArgsConstructor
public class ReminderController {

    private final ReminderService reminderService;


    // CREATE
    @PostMapping
    public ResponseEntity<ReminderResponse> createReminder(
            @Valid @RequestBody ReminderRequest request,
            Principal principal
    ) {

        ReminderResponse response =
                reminderService.createReminder(request, principal);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }


    // GET ALL MY REMINDERS
    @GetMapping
    public ResponseEntity<List<ReminderResponse>> getMyReminders(
            Principal principal
    ) {

        return ResponseEntity.ok(
                reminderService.getMyReminders(principal)
        );
    }


    // UPDATE
    @PutMapping("/{reminderId}")
    public ResponseEntity<ReminderResponse> updateReminder(
            @PathVariable Long reminderId,
            @Valid @RequestBody UpdateReminderRequest request,
            Principal principal
    ) {

        return ResponseEntity.ok(
                reminderService.updateReminder(
                        reminderId,
                        request,
                        principal
                )
        );
    }


    // DELETE
    @DeleteMapping("/{reminderId}")
    public ResponseEntity<Void> deleteReminder(
            @PathVariable Long reminderId,
            Principal principal
    ) {

        reminderService.deleteReminder(
                reminderId,
                principal
        );

        return ResponseEntity.noContent().build();
    }
}