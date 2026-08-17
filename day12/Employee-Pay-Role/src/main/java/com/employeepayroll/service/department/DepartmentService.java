package com.employeepayroll.service.department;

import com.employeepayroll.dto.department.*;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface DepartmentService {

    DepartmentResponseDTO createDepartment(DepartmentRequestDTO dto);

    DepartmentResponseDTO getDepartment(UUID id);

    Page<DepartmentResponseDTO> getAllDepartments(Pageable pageable);

    DepartmentResponseDTO updateDepartment(
            UUID id,
            DepartmentRequestDTO dto
    );

    void deleteDepartment(UUID id);

    DepartmentResponseDTO getDepartmentByName(String name);
    
    DepartmentResponseDTO	updateDeptByName(String name, DepartmentRequestDTO newDept);

}