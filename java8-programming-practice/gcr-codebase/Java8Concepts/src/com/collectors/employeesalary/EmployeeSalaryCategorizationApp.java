package com.collectors.employeesalary;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class EmployeeSalaryCategorizationApp {
    public static void main(String[] args) {
        List<Employee> employees = new ArrayList<>();

        employees.add(new Employee("Rohan", "Engineering", 85000));
        employees.add(new Employee("Priya", "Marketing", 62000));
        employees.add(new Employee("Amit", "Engineering", 92000));
        employees.add(new Employee("Sneha", "HR", 58000));
        employees.add(new Employee("Vikram", "Finance", 75000));
        employees.add(new Employee("Neha", "Marketing", 68000));
        employees.add(new Employee("Rahul", "Engineering", 88000));
        employees.add(new Employee("Anjali", "HR", 61000));

        System.out.println("Employee Salary Categorization");
        System.out.println("Average Salary by Department");
        System.out.println("--------------------------------");

        Map<String, Double> avgSalaryByDept = employees.stream()
                .collect(Collectors.groupingBy(
                        Employee::getDepartment,
                        Collectors.averagingDouble(Employee::getSalary)
                ));

        avgSalaryByDept.forEach((dept, avgSalary) -> {
            System.out.printf("%-12s : Rs. %.2f%n", dept, avgSalary);
        });

        System.out.println("--------------------------------");
        System.out.println("Total employees: " + employees.size());
    }
}