package com.healthclinicapp.model;

import java.sql.Date;

/** POJO for the {@code staff} table. */
public class Staff {

    private int    staffId;
    private String firstName;
    private String lastName;
    private String role;
    private Integer departmentId;
    private String phone;
    private String email;
    private double salary;
    private Date   joinDate;
    private boolean isActive;

    // ── Transient ─────────────────────────────────────────────────────────────
    private String departmentName;

    public Staff() {}

    public Staff(int staffId, String firstName, String lastName, String role,
                 Integer departmentId, String phone, String email,
                 double salary, Date joinDate, boolean isActive) {
        this.staffId      = staffId;
        this.firstName    = firstName;
        this.lastName     = lastName;
        this.role         = role;
        this.departmentId = departmentId;
        this.phone        = phone;
        this.email        = email;
        this.salary       = salary;
        this.joinDate     = joinDate;
        this.isActive     = isActive;
    }

    public int    getStaffId()                  { return staffId; }
    public void   setStaffId(int id)            { this.staffId = id; }
    public String getFirstName()                { return firstName; }
    public void   setFirstName(String n)        { this.firstName = n; }
    public String getLastName()                 { return lastName; }
    public void   setLastName(String n)         { this.lastName = n; }
    public String getFullName()                 { return firstName + " " + lastName; }
    public String getRole()                     { return role; }
    public void   setRole(String r)             { this.role = r; }
    public Integer getDepartmentId()            { return departmentId; }
    public void    setDepartmentId(Integer id)  { this.departmentId = id; }
    public String getPhone()                    { return phone; }
    public void   setPhone(String p)            { this.phone = p; }
    public String getEmail()                    { return email; }
    public void   setEmail(String e)            { this.email = e; }
    public double getSalary()                   { return salary; }
    public void   setSalary(double s)           { this.salary = s; }
    public Date   getJoinDate()                 { return joinDate; }
    public void   setJoinDate(Date d)           { this.joinDate = d; }
    public boolean isActive()                   { return isActive; }
    public void    setActive(boolean a)         { this.isActive = a; }
    public String getDepartmentName()           { return departmentName; }
    public void   setDepartmentName(String n)   { this.departmentName = n; }

    @Override
    public String toString() {
        return "Staff{id=" + staffId + ", name='" + firstName + " " + lastName + "', role='" + role + "'}";
    }
}
