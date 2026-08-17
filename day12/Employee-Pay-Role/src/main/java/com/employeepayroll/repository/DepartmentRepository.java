package com.employeepayroll.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.employeepayroll.entity.Department;

public interface DepartmentRepository
        extends JpaRepository<Department, UUID> {

	Optional<Department> findByNameIgnoreCase(String name);
	
}