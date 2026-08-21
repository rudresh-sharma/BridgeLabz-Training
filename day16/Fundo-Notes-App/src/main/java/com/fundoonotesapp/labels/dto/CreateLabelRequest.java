package com.fundoonotesapp.labels.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record CreateLabelRequest(

        @NotBlank(message = "Label name cannot be empty")
        @Size(max = 50, message = "Label name cannot exceed 50 characters")
        String name

) {
}