package com.employeepayroll.service;

import com.employeepayroll.dto.employee.EmployeeRequestDTO;
import com.employeepayroll.dto.employee.EmployeeResponseDTO;
import com.employeepayroll.entity.Department;
import com.employeepayroll.entity.Employee;
import com.employeepayroll.exception.DepartmentNotFoundException;
import com.employeepayroll.exception.EmployeeNotFoundException;
import com.employeepayroll.repository.DepartmentRepository;
import com.employeepayroll.repository.EmployeeRepository;
import com.employeepayroll.service.employee.SalaryCalculator;
import com.employeepayroll.service.employee.impl.EmployeeServiceImpl;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceImplTest {

    @Mock
    private EmployeeRepository employeeRepository;

    @Mock
    private DepartmentRepository departmentRepository;

    @Mock
    private SalaryCalculator defaultCalculator;

    @Mock
    private SalaryCalculator bonusCalculator;

    private EmployeeServiceImpl employeeService;

    private UUID employeeId;
    private UUID departmentId;
    private Department department;
    private Employee employee;
    private EmployeeRequestDTO requestDTO;

    @BeforeEach
    void setUp() {
        // Constructed manually (not @InjectMocks) since two SalaryCalculator
        // mocks of the same type would be ambiguous for Mockito's auto-wiring.
        employeeService = new EmployeeServiceImpl(
                employeeRepository, departmentRepository, defaultCalculator, bonusCalculator);

        employeeId = UUID.randomUUID();
        departmentId = UUID.randomUUID();

        department = new Department();
        department.setId(departmentId);
        department.setName("Engineering");
        department.setDescription("Engineering department");

        employee = new Employee();
        employee.setId(employeeId);
        employee.setName("John Doe");
        employee.setEmail("john@example.com");
        employee.setPhone("9999999999");
        employee.setSalary(BigDecimal.valueOf(50000));
        employee.setDepartment(department);

        requestDTO = new EmployeeRequestDTO(
                "John Doe", "john@example.com", "9999999999",
                BigDecimal.valueOf(50000), departmentId);
    }

    // ---------------- createEmployee ----------------

    @Test
    void createEmployee_WhenDepartmentExists_ShouldReturnSavedEmployee() {
        when(departmentRepository.findById(departmentId)).thenReturn(Optional.of(department));
        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);

        EmployeeResponseDTO result = employeeService.createEmployee(requestDTO);

        assertThat(result).isNotNull();
        assertThat(result.name()).isEqualTo("John Doe");
        assertThat(result.departmentId()).isEqualTo(departmentId);
        verify(departmentRepository, times(1)).findById(departmentId);
        verify(employeeRepository, times(1)).save(any(Employee.class));
    }

    @Test
    void createEmployee_WhenDepartmentNotFound_ShouldThrowException() {
        when(departmentRepository.findById(departmentId)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> employeeService.createEmployee(requestDTO))
                .isInstanceOf(DepartmentNotFoundException.class);

        verify(employeeRepository, never()).save(any());
    }

    // ---------------- getEmployee ----------------

    @Test
    void getEmployee_WhenExists_ShouldReturnEmployee() {
        when(employeeRepository.findById(employeeId)).thenReturn(Optional.of(employee));

        EmployeeResponseDTO result = employeeService.getEmployee(employeeId);

        assertThat(result.id()).isEqualTo(employeeId);
        assertThat(result.name()).isEqualTo("John Doe");
        verify(employeeRepository, times(1)).findById(employeeId);
    }

    @Test
    void getEmployee_WhenNotExists_ShouldThrowException() {
        when(employeeRepository.findById(employeeId)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> employeeService.getEmployee(employeeId))
                .isInstanceOf(EmployeeNotFoundException.class)
                .hasMessageContaining(employeeId.toString());
    }

    // ---------------- getEmployeeByEmail ----------------

    @Test
    void getEmployeeByEmail_WhenExists_ShouldReturnEmployee() {
        when(employeeRepository.findByEmail("john@example.com")).thenReturn(Optional.of(employee));

        EmployeeResponseDTO result = employeeService.getEmployeeByEmail("john@example.com");

        assertThat(result.email()).isEqualTo("john@example.com");
        verify(employeeRepository, times(1)).findByEmail("john@example.com");
    }

    @Test
    void getEmployeeByEmail_WhenNotExists_ShouldThrowException() {
        when(employeeRepository.findByEmail("missing@example.com")).thenReturn(Optional.empty());

        assertThatThrownBy(() -> employeeService.getEmployeeByEmail("missing@example.com"))
                .isInstanceOf(EmployeeNotFoundException.class);
    }

    // ---------------- getAllEmployees ----------------

    @Test
    void getAllEmployees_ShouldReturnPageOfEmployees() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Employee> page = new PageImpl<>(List.of(employee), pageable, 1);

        when(employeeRepository.findAll(pageable)).thenReturn(page);

        Page<EmployeeResponseDTO> result = employeeService.getAllEmployees(pageable);

        assertThat(result.getTotalElements()).isEqualTo(1);
        assertThat(result.getContent().get(0).name()).isEqualTo("John Doe");
        verify(employeeRepository, times(1)).findAll(pageable);
    }

    // ---------------- updateEmployeeByEmail ----------------

    @Test
    void updateEmployeeByEmail_WhenExists_ShouldReturnUpdatedEmployee() {
        EmployeeRequestDTO updateDTO = new EmployeeRequestDTO(
                "Jane Doe", "jane@example.com", "8888888888",
                BigDecimal.valueOf(60000), departmentId);

        when(employeeRepository.findByEmail("john@example.com")).thenReturn(Optional.of(employee));
        when(departmentRepository.findById(departmentId)).thenReturn(Optional.of(department));
        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);

        EmployeeResponseDTO result = employeeService.updateEmployeeByEmail("john@example.com", updateDTO);

        assertThat(result.name()).isEqualTo("Jane Doe");
        verify(employeeRepository, times(1)).save(employee);
    }

    @Test
    void updateEmployeeByEmail_WhenEmployeeNotFound_ShouldThrowException() {
        when(employeeRepository.findByEmail(anyString())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> employeeService.updateEmployeeByEmail("missing@example.com", requestDTO))
                .isInstanceOf(EmployeeNotFoundException.class);

        verify(employeeRepository, never()).save(any());
    }

    @Test
    void updateEmployeeByEmail_WhenDepartmentNotFound_ShouldThrowException() {
        when(employeeRepository.findByEmail("john@example.com")).thenReturn(Optional.of(employee));
        when(departmentRepository.findById(departmentId)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> employeeService.updateEmployeeByEmail("john@example.com", requestDTO))
                .isInstanceOf(DepartmentNotFoundException.class);

        verify(employeeRepository, never()).save(any());
    }

    // ---------------- updateEmployee ----------------

    @Test
    void updateEmployee_WhenExists_ShouldReturnUpdatedEmployee() {
        EmployeeRequestDTO updateDTO = new EmployeeRequestDTO(
                "Jane Doe", "jane@example.com", "8888888888",
                BigDecimal.valueOf(60000), departmentId);

        when(employeeRepository.findById(employeeId)).thenReturn(Optional.of(employee));
        when(departmentRepository.findById(departmentId)).thenReturn(Optional.of(department));
        when(employeeRepository.save(any(Employee.class))).thenReturn(employee);

        EmployeeResponseDTO result = employeeService.updateEmployee(employeeId, updateDTO);

        assertThat(result.name()).isEqualTo("Jane Doe");
        verify(employeeRepository, times(1)).save(employee);
    }

    @Test
    void updateEmployee_WhenEmployeeNotFound_ShouldThrowException() {
        when(employeeRepository.findById(employeeId)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> employeeService.updateEmployee(employeeId, requestDTO))
                .isInstanceOf(EmployeeNotFoundException.class);

        verify(employeeRepository, never()).save(any());
    }

    @Test
    void updateEmployee_WhenDepartmentNotFound_ShouldThrowException() {
        when(employeeRepository.findById(employeeId)).thenReturn(Optional.of(employee));
        when(departmentRepository.findById(departmentId)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> employeeService.updateEmployee(employeeId, requestDTO))
                .isInstanceOf(DepartmentNotFoundException.class);

        verify(employeeRepository, never()).save(any());
    }

    // ---------------- deleteEmployee ----------------

    @Test
    void deleteEmployee_WhenExists_ShouldDeleteSuccessfully() {
        when(employeeRepository.existsById(employeeId)).thenReturn(true);
        doNothing().when(employeeRepository).deleteById(employeeId);

        employeeService.deleteEmployee(employeeId);

        verify(employeeRepository, times(1)).existsById(employeeId);
        verify(employeeRepository, times(1)).deleteById(employeeId);
    }

    @Test
    void deleteEmployee_WhenNotExists_ShouldThrowException() {
        when(employeeRepository.existsById(employeeId)).thenReturn(false);

        assertThatThrownBy(() -> employeeService.deleteEmployee(employeeId))
                .isInstanceOf(EmployeeNotFoundException.class);

        verify(employeeRepository, never()).deleteById(any());
    }

    // ---------------- getAnnualSalaryByEmail ----------------

    @Test
    void getAnnualSalaryByEmail_WithoutBonus_ShouldUseDefaultCalculator() {
        when(employeeRepository.findByEmail("john@example.com")).thenReturn(Optional.of(employee));
        when(defaultCalculator.calculateAnnualSalary(employee)).thenReturn(BigDecimal.valueOf(600000));

        BigDecimal result = employeeService.getAnnualSalaryByEmail("john@example.com", false);

        assertThat(result).isEqualByComparingTo(BigDecimal.valueOf(600000));
        verify(defaultCalculator, times(1)).calculateAnnualSalary(employee);
        verify(bonusCalculator, never()).calculateAnnualSalary(any());
    }

    @Test
    void getAnnualSalaryByEmail_WithBonus_ShouldUseBonusCalculator() {
        when(employeeRepository.findByEmail("john@example.com")).thenReturn(Optional.of(employee));
        when(bonusCalculator.calculateAnnualSalary(employee)).thenReturn(BigDecimal.valueOf(660000));

        BigDecimal result = employeeService.getAnnualSalaryByEmail("john@example.com", true);

        assertThat(result).isEqualByComparingTo(BigDecimal.valueOf(660000));
        verify(bonusCalculator, times(1)).calculateAnnualSalary(employee);
        verify(defaultCalculator, never()).calculateAnnualSalary(any());
    }

    @Test
    void getAnnualSalaryByEmail_WhenEmployeeNotFound_ShouldThrowException() {
        when(employeeRepository.findByEmail(anyString())).thenReturn(Optional.empty());

        assertThatThrownBy(() -> employeeService.getAnnualSalaryByEmail("missing@example.com", false))
                .isInstanceOf(EmployeeNotFoundException.class);
    }

    // ---------------- getHighEarners ----------------

    @Test
    void getHighEarners_ShouldReturnMatchingEmployees() {
        when(employeeRepository.findHighEarners(BigDecimal.valueOf(40000)))
                .thenReturn(List.of(employee));

        List<EmployeeResponseDTO> result = employeeService.getHighEarners(BigDecimal.valueOf(40000));

        assertThat(result).hasSize(1);
        assertThat(result.get(0).name()).isEqualTo("John Doe");
        verify(employeeRepository, times(1)).findHighEarners(BigDecimal.valueOf(40000));
    }

    @Test
    void getHighEarners_WhenNoneMatch_ShouldReturnEmptyList() {
        when(employeeRepository.findHighEarners(any(BigDecimal.class)))
                .thenReturn(List.of());

        List<EmployeeResponseDTO> result = employeeService.getHighEarners(BigDecimal.valueOf(1_000_000));

        assertThat(result).isEmpty();
    }

    // ---------------- numberOfEmployees ----------------

    @Test
    void numberOfEmployees_ShouldReturnCount() {
        when(employeeRepository.count()).thenReturn(5L);

        Long result = employeeService.numberOfEmployees();

        assertThat(result).isEqualTo(5L);
        verify(employeeRepository, times(1)).count();
    }
}