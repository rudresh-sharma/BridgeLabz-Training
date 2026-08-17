package com.employeepayroll.controller;

import com.employeepayroll.controller.employee.EmployeeController;
import com.employeepayroll.dto.employee.EmployeeRequestDTO;
import com.employeepayroll.dto.employee.EmployeeResponseDTO;
import com.employeepayroll.service.employee.EmployeeService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class EmployeeControllerTest {

    private MockMvc mockMvc;

    @Mock
    private EmployeeService employeeService;

    @InjectMocks
    private EmployeeController employeeController;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private UUID employeeId;
    private UUID departmentId;
    private EmployeeResponseDTO responseDTO;
    private EmployeeRequestDTO requestDTO;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(employeeController)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .build();

        employeeId = UUID.randomUUID();
        departmentId = UUID.randomUUID();

        responseDTO = new EmployeeResponseDTO(
                employeeId, "John Doe", "john@example.com", "9999999999",
                BigDecimal.valueOf(50000), departmentId);

        requestDTO = new EmployeeRequestDTO(
                "John Doe", "john@example.com", "9999999999",
                BigDecimal.valueOf(50000), departmentId);
    }

    // ---------------- POST /api/employees ----------------

    @Test
    void createEmployee_ShouldReturnCreated() throws Exception {
        when(employeeService.createEmployee(any(EmployeeRequestDTO.class)))
                .thenReturn(responseDTO);

        mockMvc.perform(post("/api/employees")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "/api/employees/" + employeeId))
                .andExpect(jsonPath("$.id").value(employeeId.toString()))
                .andExpect(jsonPath("$.name").value("John Doe"));

        verify(employeeService, times(1)).createEmployee(any(EmployeeRequestDTO.class));
    }

    // ---------------- GET /api/employees/{id} ----------------

    @Test
    void getEmployee_ShouldReturnOk() throws Exception {
        when(employeeService.getEmployee(employeeId)).thenReturn(responseDTO);

        mockMvc.perform(get("/api/employees/{id}", employeeId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("John Doe"));

        verify(employeeService, times(1)).getEmployee(employeeId);
    }

    // ---------------- GET /api/employees/email/{email} ----------------

    @Test
    void getEmployeeByEmail_ShouldReturnOk() throws Exception {
        when(employeeService.getEmployeeByEmail("john@example.com")).thenReturn(responseDTO);

        mockMvc.perform(get("/api/employees/email/{email}", "john@example.com"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.email").value("john@example.com"));

        verify(employeeService, times(1)).getEmployeeByEmail("john@example.com");
    }

    // ---------------- GET /api/employees ----------------

    @Test
    void getAllEmployees_ShouldReturnOk() throws Exception {
        Page<EmployeeResponseDTO> page = new PageImpl<>(
                List.of(responseDTO),
                PageRequest.of(0, 10),
                1);

        when(employeeService.getAllEmployees(any(Pageable.class))).thenReturn(page);

        mockMvc.perform(get("/api/employees"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].name").value("John Doe"));

        verify(employeeService, times(1)).getAllEmployees(any(Pageable.class));
    }

    // ---------------- PUT /api/employees/{id} ----------------

    @Test
    void updateEmployee_ShouldReturnOk() throws Exception {
        EmployeeResponseDTO updated = new EmployeeResponseDTO(
                employeeId, "Jane Doe", "jane@example.com", "8888888888",
                BigDecimal.valueOf(60000), departmentId);

        when(employeeService.updateEmployee(eq(employeeId), any(EmployeeRequestDTO.class)))
                .thenReturn(updated);

        mockMvc.perform(put("/api/employees/{id}", employeeId)
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(
                                new EmployeeRequestDTO("Jane Doe", "jane@example.com",
                                        "8888888888", BigDecimal.valueOf(60000), departmentId))))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Jane Doe"));

        verify(employeeService, times(1))
                .updateEmployee(eq(employeeId), any(EmployeeRequestDTO.class));
    }

    // ---------------- PUT /api/employees/email/{email} ----------------

    @Test
    void updateEmployeeByEmail_ShouldReturnOk() throws Exception {
        EmployeeResponseDTO updated = new EmployeeResponseDTO(
                employeeId, "Jane Doe", "jane@example.com", "8888888888",
                BigDecimal.valueOf(60000), departmentId);

        when(employeeService.updateEmployeeByEmail(eq("john@example.com"), any(EmployeeRequestDTO.class)))
                .thenReturn(updated);

        mockMvc.perform(put("/api/employees/email/{email}", "john@example.com")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(
                                new EmployeeRequestDTO("Jane Doe", "jane@example.com",
                                        "8888888888", BigDecimal.valueOf(60000), departmentId))))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Jane Doe"));

        verify(employeeService, times(1))
                .updateEmployeeByEmail(eq("john@example.com"), any(EmployeeRequestDTO.class));
    }

    // ---------------- DELETE /api/employees/{id} ----------------

    @Test
    void deleteEmployee_ShouldReturnNoContent() throws Exception {
        doNothing().when(employeeService).deleteEmployee(employeeId);

        mockMvc.perform(delete("/api/employees/{id}", employeeId))
                .andExpect(status().isNoContent());

        verify(employeeService, times(1)).deleteEmployee(employeeId);
    }

    // ---------------- GET /api/employees/email/{email}/annual-salary ----------------

    @Test
    void getAnnualSalaryByEmail_WithoutBonusParam_ShouldReturnOk() throws Exception {
        when(employeeService.getAnnualSalaryByEmail("john@example.com", false))
                .thenReturn(BigDecimal.valueOf(600000));

        mockMvc.perform(get("/api/employees/email/{email}/annual-salary", "john@example.com"))
                .andExpect(status().isOk())
                .andExpect(content().string("600000"));

        verify(employeeService, times(1)).getAnnualSalaryByEmail("john@example.com", false);
    }

    @Test
    void getAnnualSalaryByEmail_WithBonusTrue_ShouldReturnOk() throws Exception {
        when(employeeService.getAnnualSalaryByEmail("john@example.com", true))
                .thenReturn(BigDecimal.valueOf(660000));

        mockMvc.perform(get("/api/employees/email/{email}/annual-salary", "john@example.com")
                        .param("bonus", "true"))
                .andExpect(status().isOk())
                .andExpect(content().string("660000"));

        verify(employeeService, times(1)).getAnnualSalaryByEmail("john@example.com", true);
    }

    // ---------------- GET /api/employees/high-earners ----------------

    @Test
    void getHighEarners_ShouldReturnOk() throws Exception {
        when(employeeService.getHighEarners(any(BigDecimal.class)))
                .thenReturn(List.of(responseDTO));

        mockMvc.perform(get("/api/employees/high-earners")
                        .param("minSalary", "40000"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].name").value("John Doe"));

        verify(employeeService, times(1)).getHighEarners(BigDecimal.valueOf(40000));
    }

    // ---------------- GET /api/employees/totalEmployee ----------------

    @Test
    void numberOfEmployee_ShouldReturnOk() throws Exception {
        when(employeeService.numberOfEmployees()).thenReturn(5L);

        mockMvc.perform(get("/api/employees/totalEmployee"))
                .andExpect(status().isOk())
                .andExpect(content().string("5"));

        verify(employeeService, times(1)).numberOfEmployees();
    }
}