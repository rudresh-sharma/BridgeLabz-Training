package com.methodreferences.nameuppercasing;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("E1001", "John Doe"));
        employees.add(new Employee("E1002", "Jane Smith"));
        employees.add(new Employee("E1003", "Alice Johnson"));
        employees.add(new Employee("E1004", "Bob Williams"));

        HRService hrService = new HRService();
        List<String> uppercaseNames = hrService.getUppercaseNames(employees);

        System.out.println("Employee Names in Uppercase:");
        uppercaseNames.forEach(System.out::println);
    }
}
