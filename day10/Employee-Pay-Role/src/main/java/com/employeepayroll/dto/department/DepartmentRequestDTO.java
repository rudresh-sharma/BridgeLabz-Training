package com.employeepayroll.dto.department;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record DepartmentRequestDTO(

    @NotBlank(message = "Department name is required")
    @Size(
        min = 2,
        max = 100,
        message = "Department name must be between 2 and 100 characters"
    )
    String name,

    @Size(
        max = 1000,
        message = "Description cannot exceed 1000 characters"
    )
    String description
) {
}