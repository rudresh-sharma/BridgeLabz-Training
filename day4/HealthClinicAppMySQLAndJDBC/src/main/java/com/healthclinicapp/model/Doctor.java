package com.healthclinicapp.model;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * POJO representing a row in the {@code doctor} table.
 */
public class Doctor {

    private int     doctorId;
    private String  firstName;
    private String  lastName;
    private String  specialization;
    private int     departmentId;
    private String  phone;
    private String  email;
    private double  salary;
    private Date    joinDate;
    private boolean isActive;
    private Timestamp createdAt;
    private Timestamp updatedAt;

    // ── Transient / computed fields (not persisted) ───────────────────────────
    /** Department name — populated by JOIN queries. */
    private String departmentName;

    // ── Constructors ─────────────────────────────────────────────────────────

    public Doctor() {}

    public Doctor(int doctorId, String firstName, String lastName,
                  String specialization, int departmentId, String phone,
                  String email, double salary, Date joinDate, boolean isActive,
                  Timestamp createdAt, Timestamp updatedAt) {
        this.doctorId       = doctorId;
        this.firstName      = firstName;
        this.lastName       = lastName;
        this.specialization = specialization;
        this.departmentId   = departmentId;
        this.phone          = phone;
        this.email          = email;
        this.salary         = salary;
        this.joinDate       = joinDate;
        this.isActive       = isActive;
        this.createdAt      = createdAt;
        this.updatedAt      = updatedAt;
    }

    // ── Getters & Setters ────────────────────────────────────────────────────

    public int    getDoctorId()                  { return doctorId; }
    public void   setDoctorId(int id)            { this.doctorId = id; }

    public String getFirstName()                 { return firstName; }
    public void   setFirstName(String n)         { this.firstName = n; }

    public String getLastName()                  { return lastName; }
    public void   setLastName(String n)          { this.lastName = n; }

    public String getFullName()                  { return firstName + " " + lastName; }

    public String getSpecialization()            { return specialization; }
    public void   setSpecialization(String s)    { this.specialization = s; }

    public int    getDepartmentId()              { return departmentId; }
    public void   setDepartmentId(int id)        { this.departmentId = id; }

    public String getPhone()                     { return phone; }
    public void   setPhone(String p)             { this.phone = p; }

    public String getEmail()                     { return email; }
    public void   setEmail(String e)             { this.email = e; }

    public double getSalary()                    { return salary; }
    public void   setSalary(double s)            { this.salary = s; }

    public Date   getJoinDate()                  { return joinDate; }
    public void   setJoinDate(Date d)            { this.joinDate = d; }

    public boolean isActive()                    { return isActive; }
    public void    setActive(boolean active)     { this.isActive = active; }

    public Timestamp getCreatedAt()              { return createdAt; }
    public void      setCreatedAt(Timestamp t)   { this.createdAt = t; }

    public Timestamp getUpdatedAt()              { return updatedAt; }
    public void      setUpdatedAt(Timestamp t)   { this.updatedAt = t; }

    public String getDepartmentName()            { return departmentName; }
    public void   setDepartmentName(String n)    { this.departmentName = n; }

    // ── toString ─────────────────────────────────────────────────────────────

    @Override
    public String toString() {
        return "Doctor{id=" + doctorId + ", name='Dr. " + firstName + " " + lastName +
               "', spec='" + specialization + "'}";
    }
}
