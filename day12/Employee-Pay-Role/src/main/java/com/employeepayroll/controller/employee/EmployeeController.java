package com.employeepayroll.controller.employee;

import com.employeepayroll.dto.employee.*;
import com.employeepayroll.service.employee.EmployeeService;

import jakarta.validation.Valid;

import java.math.BigDecimal;
import java.net.URI;
import java.util.List;
import java.util.UUID;

import org.springdoc.core.annotations.ParameterObject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }
    
    
    
    @PostMapping
    public ResponseEntity<EmployeeResponseDTO> createEmployee(
            @Valid @RequestBody EmployeeRequestDTO dto) {

        EmployeeResponseDTO created = employeeService.createEmployee(dto);

        return ResponseEntity
                .created(URI.create("/api/employees/" + created.id()))
                .body(created);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EmployeeResponseDTO> getEmployee(
            @PathVariable UUID id) {

        return ResponseEntity.ok(employeeService.getEmployee(id));
    }
    
    @GetMapping("/email/{email}")
    public ResponseEntity<EmployeeResponseDTO> getEmployeeByEmail(
            @PathVariable String email) {

        return ResponseEntity.ok(employeeService.getEmployeeByEmail(email));
    }
    @GetMapping
    public ResponseEntity<Page<EmployeeResponseDTO>> getAllEmployees(
    		@ParameterObject	
            @PageableDefault(size = 10, sort = "name") Pageable pageable) {

        return ResponseEntity.ok(employeeService.getAllEmployees(pageable));
    }

    @PutMapping("/{id}")
    public ResponseEntity<EmployeeResponseDTO> updateEmployee(
            @PathVariable UUID id,
            @Valid @RequestBody EmployeeRequestDTO dto) {

        return ResponseEntity.ok(employeeService.updateEmployee(id, dto));
    }
    
    @PutMapping("/email/{email}")
    public ResponseEntity<EmployeeResponseDTO> updateEmployeeByEmail(
            @PathVariable String email,
            @Valid @RequestBody EmployeeRequestDTO dto) {

        return ResponseEntity.ok(employeeService.updateEmployeeByEmail(email, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteEmployee(@PathVariable UUID id) {

        employeeService.deleteEmployee(id);

        return ResponseEntity.noContent().build();
    }
    
    
    @GetMapping("/email/{email}/annual-salary")
    public ResponseEntity<BigDecimal> getAnnualSalaryByEmail(
            @PathVariable String email,
            @RequestParam(defaultValue = "false") boolean bonus) {

        return ResponseEntity.ok(employeeService.getAnnualSalaryByEmail(email, bonus));
    }
    
    
    @GetMapping("/high-earners")
    public ResponseEntity<List<EmployeeResponseDTO>> getHighEarners(
            @RequestParam BigDecimal minSalary) {
        return ResponseEntity.ok(employeeService.getHighEarners(minSalary));
    }
    
    
    @GetMapping("/totalEmployee")
    public ResponseEntity<Long> numberOfEmployee(){
    	return ResponseEntity.ok(employeeService.numberOfEmployees());
    }
}