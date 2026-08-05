package com.healthclinicapp.model;

import java.sql.Date;
import java.sql.Timestamp;

/**
 * POJO representing a row in the {@code patient} table.
 */
public class Patient {

    private int     patientId;
    private String  firstName;
    private String  lastName;
    private Date    dateOfBirth;
    private String  gender;       // Male / Female / Other
    private String  bloodGroup;
    private String  phone;
    private String  email;
    private String  address;
    private String  city;
    private boolean isActive;
    private Timestamp registeredAt;
    private Timestamp updatedAt;

    // ── Constructors ─────────────────────────────────────────────────────────

    public Patient() {}

    public Patient(int patientId, String firstName, String lastName,
                   Date dateOfBirth, String gender, String bloodGroup,
                   String phone, String email, String address, String city,
                   boolean isActive, Timestamp registeredAt, Timestamp updatedAt) {
        this.patientId    = patientId;
        this.firstName    = firstName;
        this.lastName     = lastName;
        this.dateOfBirth  = dateOfBirth;
        this.gender       = gender;
        this.bloodGroup   = bloodGroup;
        this.phone        = phone;
        this.email        = email;
        this.address      = address;
        this.city         = city;
        this.isActive     = isActive;
        this.registeredAt = registeredAt;
        this.updatedAt    = updatedAt;
    }

    // ── Getters & Setters ────────────────────────────────────────────────────

    public int    getPatientId()                   { return patientId; }
    public void   setPatientId(int id)             { this.patientId = id; }

    public String getFirstName()                   { return firstName; }
    public void   setFirstName(String n)           { this.firstName = n; }

    public String getLastName()                    { return lastName; }
    public void   setLastName(String n)            { this.lastName = n; }

    public String getFullName()                    { return firstName + " " + lastName; }

    public Date   getDateOfBirth()                 { return dateOfBirth; }
    public void   setDateOfBirth(Date d)           { this.dateOfBirth = d; }

    public String getGender()                      { return gender; }
    public void   setGender(String g)              { this.gender = g; }

    public String getBloodGroup()                  { return bloodGroup; }
    public void   setBloodGroup(String bg)         { this.bloodGroup = bg; }

    public String getPhone()                       { return phone; }
    public void   setPhone(String p)               { this.phone = p; }

    public String getEmail()                       { return email; }
    public void   setEmail(String e)               { this.email = e; }

    public String getAddress()                     { return address; }
    public void   setAddress(String a)             { this.address = a; }

    public String getCity()                        { return city; }
    public void   setCity(String c)                { this.city = c; }

    public boolean isActive()                      { return isActive; }
    public void    setActive(boolean active)       { this.isActive = active; }

    public Timestamp getRegisteredAt()             { return registeredAt; }
    public void      setRegisteredAt(Timestamp t)  { this.registeredAt = t; }

    public Timestamp getUpdatedAt()                { return updatedAt; }
    public void      setUpdatedAt(Timestamp t)     { this.updatedAt = t; }

    // ── toString ─────────────────────────────────────────────────────────────

    @Override
    public String toString() {
        return "Patient{id=" + patientId + ", name='" + firstName + " " + lastName +
               "', phone='" + phone + "'}";
    }
}
