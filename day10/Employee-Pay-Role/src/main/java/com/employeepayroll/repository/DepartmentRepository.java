package com.employeepayroll.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.employeepayroll.entity.Department;

public interface DepartmentRepository
        extends ListCrudRepository<Department, UUID>,
                PagingAndSortingRepository<Department, UUID> {

	Optional<Department> findByName(String name);
}