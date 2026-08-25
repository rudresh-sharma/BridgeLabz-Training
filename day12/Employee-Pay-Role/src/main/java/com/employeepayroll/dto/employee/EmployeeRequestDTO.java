package com.employeepayroll.dto.employee;

import java.math.BigDecimal;

import java.util.UUID;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record EmployeeRequestDTO(

    @NotBlank(message = "Name is required")
    @Size(min = 2, max = 100, message = "Name must be between 2 and 100 characters")
    String name,

    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    @Size(max = 150, message = "Email cannot exceed 150 characters")
    String email,

    @Pattern(
        regexp = "^\\+?[0-9]{10,15}$",
        message = "Invalid phone number"
    )
    String phone,

    @NotNull(message = "Salary is required")
    @DecimalMin(
        value = "0.0",
        inclusive = false,
        message = "Salary must be greater than 0"
    )
    BigDecimal salary,

    @NotNull(message = "Department ID is required")
    UUID departmentId
) {
}