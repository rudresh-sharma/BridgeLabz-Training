package com.employeepayroll.controller.department;

import com.employeepayroll.dto.department.*;
import com.employeepayroll.service.department.DepartmentService;

import jakarta.validation.Valid;

import java.net.URI;
import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {
	
	
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @PostMapping
    public ResponseEntity<DepartmentResponseDTO> createDepartment(
            @Valid @RequestBody DepartmentRequestDTO dto) {

        DepartmentResponseDTO created = departmentService.createDepartment(dto);

        return ResponseEntity
                .created(URI.create("/api/departments/" + created.id()))
                .body(created);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartmentResponseDTO> getDepartment(
            @PathVariable UUID id) {

        return ResponseEntity.ok(departmentService.getDepartment(id));
    }
    
    @GetMapping("/by-name/{name}")
    public ResponseEntity<DepartmentResponseDTO> getDepartmentByName(
            @PathVariable String name) {
        return ResponseEntity.ok(departmentService.getDepartmentByName(name));
    }
    
    
    @GetMapping
    public ResponseEntity<Page<DepartmentResponseDTO>> getAllDepartments(
            @PageableDefault(size = 10, sort = "name") Pageable pageable) {

        return ResponseEntity.ok(departmentService.getAllDepartments(pageable));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DepartmentResponseDTO> updateDepartment(
            @PathVariable UUID id,
            @Valid @RequestBody DepartmentRequestDTO dto) {

        return ResponseEntity.ok(departmentService.updateDepartment(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDepartment(@PathVariable UUID id) {

        departmentService.deleteDepartment(id);

        return ResponseEntity.noContent().build();
    }
    
    
    @PutMapping("by-name/{name}")
    public ResponseEntity<DepartmentResponseDTO> updateDepartmentByName(@PathVariable("name")  String name, @Valid @RequestBody DepartmentRequestDTO newdept){
    	return ResponseEntity.ok(departmentService.updateDeptByName(name,newdept));
    }
}