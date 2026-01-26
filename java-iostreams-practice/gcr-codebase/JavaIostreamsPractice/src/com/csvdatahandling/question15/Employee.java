package com.csvdatahandling.question15;

public class Employee {

    private int employeeId;
    private String name;
    private String department;
    private String salary;   // keep as String for encryption

    public Employee() {}

    public int getEmployeeId() { return employeeId; }
    public void setEmployeeId(int employeeId) { this.employeeId = employeeId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDepartment() { return department; }
    public void setDepartment(String department) { this.department = department; }

    public String getSalary() { return salary; }
    public void setSalary(String salary) { this.salary = salary; }
}
