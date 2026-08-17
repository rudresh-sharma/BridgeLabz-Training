package com.employeepayroll.dto.employee;

import java.math.BigDecimal;
import java.util.UUID;

public record EmployeeResponseDTO(
    UUID id,
    String name,
    String email,
    String phone,
    BigDecimal salary,
    UUID departmentId
) {
}