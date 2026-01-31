package com.methodreferences.nameuppercasing;
import java.util.List;
import java.util.stream.Collectors;

public class HRService {

    // Convert all employee names to uppercase
    public List<String> getUppercaseNames(List<Employee> employees) {
        return employees.stream()
                        .map(Employee::getName)   // Extract names
                        .map(String::toUpperCase) // Convert to uppercase
                        .collect(Collectors.toList());
    }
}
