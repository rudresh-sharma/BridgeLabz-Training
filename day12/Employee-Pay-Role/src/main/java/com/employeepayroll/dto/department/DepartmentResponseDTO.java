package com.employeepayroll.dto.department;

import java.util.UUID;

public record DepartmentResponseDTO(
    UUID id,
    String name,
    String description
) {
}