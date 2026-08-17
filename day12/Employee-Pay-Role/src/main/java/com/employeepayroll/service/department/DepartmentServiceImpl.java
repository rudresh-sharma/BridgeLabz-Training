package com.employeepayroll.service.department;

import com.employeepayroll.dto.department.*;
import com.employeepayroll.entity.Department;
import com.employeepayroll.exception.DepartmentNotFoundException;
import com.employeepayroll.repository.DepartmentRepository;
import com.employeepayroll.service.department.DepartmentService;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepository;

    public DepartmentServiceImpl(
            DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
    }

    @Override
    public DepartmentResponseDTO createDepartment(
            DepartmentRequestDTO dto) {

    	Department department = new Department();
    	department.setId(UUID.randomUUID());
    	department.setName(dto.name());
    	department.setDescription(dto.description());
    	

        Department saved = departmentRepository.save(department);

        return new DepartmentResponseDTO(
                saved.getId(),
                saved.getName(),
                saved.getDescription()
        );
    }

    @Override
    public DepartmentResponseDTO getDepartment(UUID id) {

        Department department = departmentRepository.findById(id)
                .orElseThrow(() ->
                	new DepartmentNotFoundException(
            	        "Department not found with id: " + id
            	));

        return toResponse(department);
    }

    @Override
    public Page<DepartmentResponseDTO> getAllDepartments(Pageable pageable) {

        return departmentRepository.findAll(pageable)
                .map(this::toResponse);
    }

    @Override
    public DepartmentResponseDTO updateDepartment(
            UUID id,
            DepartmentRequestDTO dto) {

        Department department = departmentRepository.findById(id)
                .orElseThrow(() ->
                new DepartmentNotFoundException(
            	        "Department not found with id: " + id
            	));

        department.setName(dto.name());
        department.setDescription(dto.description());

        Department updated = departmentRepository.save(department);

        return toResponse(updated);
    }

    @Override
    public void deleteDepartment(UUID id) {

        if (!departmentRepository.existsById(id)) {
        	throw new DepartmentNotFoundException(
        	        "Department not found with id: " + id
        	);
        }

        departmentRepository.deleteById(id);
    }

    private DepartmentResponseDTO toResponse(
            Department department) {

        return new DepartmentResponseDTO(
                department.getId(),
                department.getName(),
                department.getDescription()
        );
    }
    
    @Override
    public DepartmentResponseDTO getDepartmentByName(String name) {
        Department department = departmentRepository.findByNameIgnoreCase(name)
                .orElseThrow(() -> new DepartmentNotFoundException(
                    "Department not found with name: " + name
                ));
        return toResponse(department);
    }

	@Override
	public DepartmentResponseDTO updateDeptByName(String name, DepartmentRequestDTO newDept) {
		// TODO Auto-generated method stub
		
		// checking is department exist or not
		
		  Department department = departmentRepository.findByNameIgnoreCase(name)
	                .orElseThrow(() -> new DepartmentNotFoundException(
	                    "Department not found with name: " + name
	                ));
		  
		  department.setName(newDept.name());
		  department.setDescription(newDept.description());
		  
		  
		  departmentRepository.save(department);
		  
		  
		
		return toResponse(department);
	}
}