package com.fundoo.reminderservice.reminder.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(
        name = "note-service",
        url = "${note-service.url}",
        configuration = FeignClientConfig.class
)
public interface NoteClient {
    @GetMapping("/notes/internal/{id}")
    NoteDto getNoteById(@PathVariable("id") Long id);
}