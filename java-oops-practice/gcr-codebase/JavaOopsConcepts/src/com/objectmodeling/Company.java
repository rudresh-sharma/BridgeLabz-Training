package com.objectmodeling;

import java.util.ArrayList;

public class Company {
    private String companyName;
    private ArrayList<Department> departments;

    public Company(String companyName) {
        this.companyName = companyName;
        departments = new ArrayList<>();
    }

    public void addDepartment(String deptName) {
        departments.add(new Department(deptName));   // Departments created INSIDE Company
    }

    public Department getDepartment(int index) {
        return departments.get(index);
    }

    public void showCompany() {
        System.out.println("\nCompany: " + companyName);
        for (Department dept : departments) {
            dept.showDepartment();
        }
    }
}
