package com.employeepayroll.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.employeepayroll.entity.Employee;

public interface EmployeeRepository
        extends ListCrudRepository<Employee, UUID>,
                PagingAndSortingRepository<Employee, UUID> {
	
	   Optional<Employee> findByEmail(String email);

}