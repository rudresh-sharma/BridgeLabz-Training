package com.employeepayroll.service.employee.impl;

import com.employeepayroll.dto.employee.*;

import com.employeepayroll.entity.Department;
import com.employeepayroll.entity.Employee;
import com.employeepayroll.exception.DepartmentNotFoundException;
import com.employeepayroll.exception.EmployeeNotFoundException;
import com.employeepayroll.repository.DepartmentRepository;
import com.employeepayroll.repository.EmployeeRepository;
import com.employeepayroll.service.employee.EmployeeService;
import com.employeepayroll.service.employee.SalaryCalculator;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;
    private final SalaryCalculator defaultCalculator;
    private final SalaryCalculator bonusCalculator;

    public EmployeeServiceImpl(
            EmployeeRepository employeeRepository,
            DepartmentRepository departmentRepository,
            SalaryCalculator defaultCalculator,
            @Qualifier("bonusSalaryCalculator") SalaryCalculator bonusCalculator) {

        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
        this.defaultCalculator = defaultCalculator;
        this.bonusCalculator = bonusCalculator;
    }

    @Override
    public EmployeeResponseDTO createEmployee(EmployeeRequestDTO dto) {

        Department department = departmentRepository.findById(dto.departmentId())
                .orElseThrow(() -> new DepartmentNotFoundException(
                        "Department not found with id: " + dto.departmentId()));

        Employee employee = new Employee();
        employee.setName(dto.name());
        employee.setEmail(dto.email());
        employee.setPhone(dto.phone());
        employee.setSalary(dto.salary());
        employee.setDepartment(department);

        Employee saved = employeeRepository.save(employee);

        return toResponse(saved);
    }

    @Override
    public EmployeeResponseDTO getEmployee(UUID id) {

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(
                        "Employee not found with id: " + id));

        return toResponse(employee);
    }

    @Override
    public EmployeeResponseDTO getEmployeeByEmail(String email) {
        Employee employee = employeeRepository.findByEmail(email)
                .orElseThrow(() -> new EmployeeNotFoundException(
                        "Employee not found with email: " + email));

        return toResponse(employee);
    }

    @Override
    public Page<EmployeeResponseDTO> getAllEmployees(Pageable pageable) {
        return employeeRepository.findAll(pageable)
                .map(this::toResponse);
    }

    @Override
    public EmployeeResponseDTO updateEmployeeByEmail(String email, EmployeeRequestDTO dto) {
        Employee employee = employeeRepository.findByEmail(email)
                .orElseThrow(() -> new EmployeeNotFoundException(
                        "Employee not found with email: " + email));

        Department department = departmentRepository.findById(dto.departmentId())
                .orElseThrow(() -> new DepartmentNotFoundException(
                        "Department not found with id: " + dto.departmentId()));

        employee.setName(dto.name());
        employee.setEmail(dto.email());
        employee.setPhone(dto.phone());
        employee.setSalary(dto.salary());
        employee.setDepartment(department);

        Employee updated = employeeRepository.save(employee);

        return toResponse(updated);
    }

    @Override
    public EmployeeResponseDTO updateEmployee(UUID id, EmployeeRequestDTO dto) {

        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new EmployeeNotFoundException(
                        "Employee not found with id: " + id));

        Department department = departmentRepository.findById(dto.departmentId())
                .orElseThrow(() -> new DepartmentNotFoundException(
                        "Department not found with id: " + dto.departmentId()));

        employee.setName(dto.name());
        employee.setEmail(dto.email());
        employee.setPhone(dto.phone());
        employee.setSalary(dto.salary());
        employee.setDepartment(department);

        Employee updated = employeeRepository.save(employee);

        return toResponse(updated);
    }

    @Override
    public void deleteEmployee(UUID id) {

        if (!employeeRepository.existsById(id)) {
            throw new EmployeeNotFoundException(
                    "Employee not found with id: " + id
            );
        }

        employeeRepository.deleteById(id);
    }

    private EmployeeResponseDTO toResponse(Employee employee) {

        return new EmployeeResponseDTO(
                employee.getId(),
                employee.getName(),
                employee.getEmail(),
                employee.getPhone(),
                employee.getSalary(),
                employee.getDepartment().getId()
        );
    }

    @Override
    public BigDecimal getAnnualSalaryByEmail(String email, boolean bonus) {

        Employee employee = employeeRepository.findByEmail(email)
                .orElseThrow(() -> new EmployeeNotFoundException(
                        "Employee not found with email: " + email));

        SalaryCalculator calculator = bonus ? bonusCalculator : defaultCalculator;

        return calculator.calculateAnnualSalary(employee);
    }
    
    
    @Override
    public List<EmployeeResponseDTO> getHighEarners(BigDecimal minSalary) {

        List<Employee> employees = employeeRepository.findHighEarners(minSalary);

        return employees.stream()
                .map(this::toResponse)
                .toList();
    }
    
    @Override
    public Long numberOfEmployees(){
    	return employeeRepository.count();
    }
}