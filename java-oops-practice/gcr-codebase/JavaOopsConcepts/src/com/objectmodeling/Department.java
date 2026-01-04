package com.objectmodeling;

import java.util.ArrayList;

public class Department {
    private String departmentName;
    private ArrayList<Employee> employees;

    public Department(String departmentName) {
        this.departmentName = departmentName;
        employees = new ArrayList<>();
    }

    public void addEmployee(String name) {
        employees.add(new Employee(name));   // Employees created INSIDE Department
    }

    public void showDepartment() {
        System.out.println("\nDepartment: " + departmentName);
        for (Employee emp : employees) {
            emp.showEmployee();
        }
    }
}
