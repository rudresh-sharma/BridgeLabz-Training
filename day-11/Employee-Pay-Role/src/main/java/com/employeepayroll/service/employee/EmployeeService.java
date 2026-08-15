package com.employeepayroll.service.employee;

import com.employeepayroll.dto.employee.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface EmployeeService {

    EmployeeResponseDTO createEmployee(EmployeeRequestDTO dto);

    EmployeeResponseDTO getEmployee(UUID id);

    Page<EmployeeResponseDTO> getAllEmployees(Pageable pageable);

    EmployeeResponseDTO updateEmployee(
            UUID id,
            EmployeeRequestDTO dto
    );
    
    EmployeeResponseDTO getEmployeeByEmail(String email);
    
    EmployeeResponseDTO updateEmployeeByEmail(String email, EmployeeRequestDTO dto);
    
    void deleteEmployee(UUID id);
    
     BigDecimal getAnnualSalaryByEmail(String email, boolean bonus);
     
     List<EmployeeResponseDTO> getHighEarners(BigDecimal minSalary);
     
     Long numberOfEmployees();
}