package com.employeepayroll.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.ListCrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;

import com.employeepayroll.entity.Employee;

public interface EmployeeRepository
        extends JpaRepository<Employee, UUID> {
	
	   Optional<Employee> findByEmail(String email);
	   
	   

	    @Query("SELECT e FROM Employee e WHERE e.salary > :minSalary ORDER BY e.salary DESC")
	    List<Employee> findHighEarners(@Param("minSalary") BigDecimal minSalary);
	    
//	    List<Employee> findBySalaryGreaterThanOrderBySalaryDesc(BigDecimal minSalary);
}