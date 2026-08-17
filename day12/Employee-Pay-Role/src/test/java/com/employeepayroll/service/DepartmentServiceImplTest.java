package com.employeepayroll.service;

import com.employeepayroll.dto.department.DepartmentRequestDTO;
import com.employeepayroll.dto.department.DepartmentResponseDTO;
import com.employeepayroll.entity.Department;
import com.employeepayroll.exception.DepartmentNotFoundException;
import com.employeepayroll.repository.DepartmentRepository;
import com.employeepayroll.service.department.DepartmentServiceImpl;

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

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DepartmentServiceImplTest {

    @Mock
    private DepartmentRepository departmentRepository;

    @InjectMocks
    private DepartmentServiceImpl departmentService;

    private UUID departmentId;
    private Department department;
    private DepartmentRequestDTO requestDTO;

    @BeforeEach
    void setUp() {
        departmentId = UUID.randomUUID();

        department = new Department();
        department.setId(departmentId);
        department.setName("Engineering");
        department.setDescription("Engineering department");

        requestDTO = new DepartmentRequestDTO("Engineering", "Engineering department");
    }

    // ---------------- createDepartment ----------------

    @Test
    void createDepartment_ShouldReturnSavedDepartment() {
        when(departmentRepository.save(any(Department.class))).thenReturn(department);

        DepartmentResponseDTO result = departmentService.createDepartment(requestDTO);

        assertThat(result).isNotNull();
        assertThat(result.name()).isEqualTo("Engineering");
        assertThat(result.description()).isEqualTo("Engineering department");
        verify(departmentRepository, times(1)).save(any(Department.class));
    }

    // ---------------- getDepartment ----------------

    @Test
    void getDepartment_WhenExists_ShouldReturnDepartment() {
        when(departmentRepository.findById(departmentId)).thenReturn(Optional.of(department));

        DepartmentResponseDTO result = departmentService.getDepartment(departmentId);

        assertThat(result.id()).isEqualTo(departmentId);
        assertThat(result.name()).isEqualTo("Engineering");
        verify(departmentRepository, times(1)).findById(departmentId);
    }

    @Test
    void getDepartment_WhenNotExists_ShouldThrowException() {
        when(departmentRepository.findById(departmentId)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> departmentService.getDepartment(departmentId))
                .isInstanceOf(DepartmentNotFoundException.class)
                .hasMessageContaining(departmentId.toString());

        verify(departmentRepository, times(1)).findById(departmentId);
    }

    // ---------------- getDepartmentByName ----------------

    @Test
    void getDepartmentByName_WhenExists_ShouldReturnDepartment() {
        when(departmentRepository.findByNameIgnoreCase("Engineering"))
                .thenReturn(Optional.of(department));

        DepartmentResponseDTO result = departmentService.getDepartmentByName("Engineering");

        assertThat(result.name()).isEqualTo("Engineering");
        verify(departmentRepository, times(1)).findByNameIgnoreCase("Engineering");
    }

    @Test
    void getDepartmentByName_WhenNotExists_ShouldThrowException() {
        when(departmentRepository.findByNameIgnoreCase("Unknown"))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() -> departmentService.getDepartmentByName("Unknown"))
                .isInstanceOf(DepartmentNotFoundException.class)
                .hasMessageContaining("Unknown");
    }

    // ---------------- getAllDepartments ----------------

    @Test
    void getAllDepartments_ShouldReturnPageOfDepartments() {
        Pageable pageable = PageRequest.of(0, 10);
        Page<Department> page = new PageImpl<>(List.of(department));

        when(departmentRepository.findAll(pageable)).thenReturn(page);

        Page<DepartmentResponseDTO> result = departmentService.getAllDepartments(pageable);

        assertThat(result.getTotalElements()).isEqualTo(1);
        assertThat(result.getContent().get(0).name()).isEqualTo("Engineering");
        verify(departmentRepository, times(1)).findAll(pageable);
    }

    // ---------------- updateDepartment ----------------

    @Test
    void updateDepartment_WhenExists_ShouldReturnUpdatedDepartment() {
        DepartmentRequestDTO updateDTO = new DepartmentRequestDTO("HR", "Human Resources");

        when(departmentRepository.findById(departmentId)).thenReturn(Optional.of(department));
        when(departmentRepository.save(any(Department.class))).thenReturn(department);

        DepartmentResponseDTO result = departmentService.updateDepartment(departmentId, updateDTO);

        assertThat(result.name()).isEqualTo("HR");
        assertThat(result.description()).isEqualTo("Human Resources");
        verify(departmentRepository, times(1)).findById(departmentId);
        verify(departmentRepository, times(1)).save(department);
    }

    @Test
    void updateDepartment_WhenNotExists_ShouldThrowException() {
        when(departmentRepository.findById(departmentId)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> departmentService.updateDepartment(departmentId, requestDTO))
                .isInstanceOf(DepartmentNotFoundException.class);

        verify(departmentRepository, never()).save(any());
    }

    // ---------------- deleteDepartment ----------------

    @Test
    void deleteDepartment_WhenExists_ShouldDeleteSuccessfully() {
        when(departmentRepository.existsById(departmentId)).thenReturn(true);
        doNothing().when(departmentRepository).deleteById(departmentId);

        departmentService.deleteDepartment(departmentId);

        verify(departmentRepository, times(1)).existsById(departmentId);
        verify(departmentRepository, times(1)).deleteById(departmentId);
    }

    @Test
    void deleteDepartment_WhenNotExists_ShouldThrowException() {
        when(departmentRepository.existsById(departmentId)).thenReturn(false);

        assertThatThrownBy(() -> departmentService.deleteDepartment(departmentId))
                .isInstanceOf(DepartmentNotFoundException.class);

        verify(departmentRepository, never()).deleteById(any());
    }

    // ---------------- updateDeptByName ----------------

    @Test
    void updateDeptByName_WhenExists_ShouldReturnUpdatedDepartment() {
        DepartmentRequestDTO updateDTO = new DepartmentRequestDTO("Finance", "Finance department");

        when(departmentRepository.findByNameIgnoreCase("Engineering"))
                .thenReturn(Optional.of(department));
        when(departmentRepository.save(any(Department.class))).thenReturn(department);

        DepartmentResponseDTO result = departmentService.updateDeptByName("Engineering", updateDTO);

        assertThat(result.name()).isEqualTo("Finance");
        assertThat(result.description()).isEqualTo("Finance department");
        verify(departmentRepository, times(1)).findByNameIgnoreCase("Engineering");
        verify(departmentRepository, times(1)).save(department);
    }

    @Test
    void updateDeptByName_WhenNotExists_ShouldThrowException() {
        when(departmentRepository.findByNameIgnoreCase(anyString()))
                .thenReturn(Optional.empty());

        assertThatThrownBy(() -> departmentService.updateDeptByName("Unknown", requestDTO))
                .isInstanceOf(DepartmentNotFoundException.class);

        verify(departmentRepository, never()).save(any());
    }
}