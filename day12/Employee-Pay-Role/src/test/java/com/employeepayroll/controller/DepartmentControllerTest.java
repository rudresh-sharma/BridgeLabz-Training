package com.employeepayroll.controller;
import org.springframework.data.domain.PageRequest;
import com.employeepayroll.controller.department.DepartmentController;
import com.employeepayroll.dto.department.DepartmentRequestDTO;
import com.employeepayroll.dto.department.DepartmentResponseDTO;
import com.employeepayroll.service.department.DepartmentService;
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

import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@ExtendWith(MockitoExtension.class)
class DepartmentControllerTest {

    private MockMvc mockMvc;

    @Mock
    private DepartmentService departmentService;

    @InjectMocks
    private DepartmentController departmentController;

    private final ObjectMapper objectMapper = new ObjectMapper();

    private UUID departmentId;
    private DepartmentResponseDTO responseDTO;
    private DepartmentRequestDTO requestDTO;

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(departmentController)
                .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
                .build();

        departmentId = UUID.randomUUID();
        responseDTO = new DepartmentResponseDTO(departmentId, "Engineering", "Engineering department");
        requestDTO = new DepartmentRequestDTO("Engineering", "Engineering department");
    }

    // ---------------- POST /api/departments ----------------

    @Test
    void createDepartment_ShouldReturnCreated() throws Exception {
        when(departmentService.createDepartment(any(DepartmentRequestDTO.class)))
                .thenReturn(responseDTO);

        mockMvc.perform(post("/api/departments")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(requestDTO)))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "/api/departments/" + departmentId))
                .andExpect(jsonPath("$.id").value(departmentId.toString()))
                .andExpect(jsonPath("$.name").value("Engineering"));

        verify(departmentService, times(1)).createDepartment(any(DepartmentRequestDTO.class));
    }

    // ---------------- GET /api/departments/{id} ----------------

    @Test
    void getDepartment_ShouldReturnOk() throws Exception {
        when(departmentService.getDepartment(departmentId)).thenReturn(responseDTO);

        mockMvc.perform(get("/api/departments/{id}", departmentId))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Engineering"));

        verify(departmentService, times(1)).getDepartment(departmentId);
    }

    // ---------------- GET /api/departments/by-name/{name} ----------------

    @Test
    void getDepartmentByName_ShouldReturnOk() throws Exception {
        when(departmentService.getDepartmentByName("Engineering")).thenReturn(responseDTO);

        mockMvc.perform(get("/api/departments/by-name/{name}", "Engineering"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Engineering"));

        verify(departmentService, times(1)).getDepartmentByName("Engineering");
    }

    // ---------------- GET /api/departments ----------------

    @Test
    void getAllDepartments_ShouldReturnOk() throws Exception {
    	Page<DepartmentResponseDTO> page = new PageImpl<>(
    	        List.of(responseDTO),
    	        PageRequest.of(0, 10),
    	        1);
        when(departmentService.getAllDepartments(any(Pageable.class))).thenReturn(page);

        mockMvc.perform(get("/api/departments"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.content[0].name").value("Engineering"));

        verify(departmentService, times(1)).getAllDepartments(any(Pageable.class));
    }

    // ---------------- PUT /api/departments/{id} ----------------

    @Test
    void updateDepartment_ShouldReturnOk() throws Exception {
        DepartmentResponseDTO updated = new DepartmentResponseDTO(departmentId, "HR", "Human Resources");

        when(departmentService.updateDepartment(eq(departmentId), any(DepartmentRequestDTO.class)))
                .thenReturn(updated);

        mockMvc.perform(put("/api/departments/{id}", departmentId)
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(
                                new DepartmentRequestDTO("HR", "Human Resources"))))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("HR"));

        verify(departmentService, times(1))
                .updateDepartment(eq(departmentId), any(DepartmentRequestDTO.class));
    }

    // ---------------- DELETE /api/departments/{id} ----------------

    @Test
    void deleteDepartment_ShouldReturnNoContent() throws Exception {
        doNothing().when(departmentService).deleteDepartment(departmentId);

        mockMvc.perform(delete("/api/departments/{id}", departmentId))
                .andExpect(status().isNoContent());

        verify(departmentService, times(1)).deleteDepartment(departmentId);
    }

    // ---------------- PUT /api/departments/by-name/{name} ----------------

    @Test
    void updateDepartmentByName_ShouldReturnOk() throws Exception {
        DepartmentResponseDTO updated = new DepartmentResponseDTO(departmentId, "Finance", "Finance department");

        when(departmentService.updateDeptByName(eq("Engineering"), any(DepartmentRequestDTO.class)))
                .thenReturn(updated);

        mockMvc.perform(put("/api/departments/by-name/{name}", "Engineering")
                        .contentType("application/json")
                        .content(objectMapper.writeValueAsString(
                                new DepartmentRequestDTO("Finance", "Finance department"))))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Finance"));

        verify(departmentService, times(1))
                .updateDeptByName(eq("Engineering"), any(DepartmentRequestDTO.class));
    }
}