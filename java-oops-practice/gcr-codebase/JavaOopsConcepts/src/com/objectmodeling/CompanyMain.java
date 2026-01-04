package com.objectmodeling;

public class CompanyMain {
    public static void main(String[] args) {

        // Create Company
        Company company = new Company("TechSoft");

        // Add Departments
        company.addDepartment("IT");
        company.addDepartment("HR");

        // Add Employees
        company.getDepartment(0).addEmployee("Ravi");
        company.getDepartment(0).addEmployee("Anita");

        company.getDepartment(1).addEmployee("Suresh");
        company.getDepartment(1).addEmployee("Neha");

        // Display Company structure
        company.showCompany();

        System.out.println("\nDeleting Company...");
        company = null;   // Composition: Departments & Employees also destroyed
    }
}
