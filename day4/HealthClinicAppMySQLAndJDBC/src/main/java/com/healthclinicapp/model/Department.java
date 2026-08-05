package com.healthclinicapp.model;

import java.sql.Timestamp;

/**
 * POJO representing a row in the {@code department} table.
 */
public class Department {

    private int    departmentId;
    private String name;
    private String description;
    private Integer headDoctorId;    // nullable FK → doctor
    private Timestamp createdAt;
    private Timestamp updatedAt;

    // ── Constructors ─────────────────────────────────────────────────────────

    public Department() {}

    public Department(int departmentId, String name, String description,
                      Integer headDoctorId, Timestamp createdAt, Timestamp updatedAt) {
        this.departmentId = departmentId;
        this.name         = name;
        this.description  = description;
        this.headDoctorId = headDoctorId;
        this.createdAt    = createdAt;
        this.updatedAt    = updatedAt;
    }

    // ── Getters & Setters ────────────────────────────────────────────────────

    public int    getDepartmentId()            { return departmentId; }
    public void   setDepartmentId(int id)      { this.departmentId = id; }

    public String getName()                    { return name; }
    public void   setName(String name)         { this.name = name; }

    public String getDescription()             { return description; }
    public void   setDescription(String d)     { this.description = d; }

    public Integer getHeadDoctorId()           { return headDoctorId; }
    public void    setHeadDoctorId(Integer id) { this.headDoctorId = id; }

    public Timestamp getCreatedAt()            { return createdAt; }
    public void      setCreatedAt(Timestamp t) { this.createdAt = t; }

    public Timestamp getUpdatedAt()            { return updatedAt; }
    public void      setUpdatedAt(Timestamp t) { this.updatedAt = t; }

    // ── toString ─────────────────────────────────────────────────────────────

    @Override
    public String toString() {
        return "Department{id=" + departmentId + ", name='" + name + "'}";
    }
}
