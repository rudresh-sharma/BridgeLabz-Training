package com.fundoo.notesservice.labels.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateLabelRequest(

        @NotBlank(message = "Label name is required")
        @Size(max = 100, message = "Label name cannot exceed 100 characters")
        String name

) {
}