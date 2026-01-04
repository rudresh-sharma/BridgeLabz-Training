package com.dayone.hospitalpatientmanagementsystem;

public class Patient {

    private int patientId;
    private String name;
    private int age;
    private String medicalHistory;

    // Normal patient constructor
    public Patient(int patientId, String name, int age) {
        this.patientId = patientId;
        this.name = name;
        this.age = age;
        this.medicalHistory = "Not Provided";
    }

    // Emergency patient constructor (overloaded)
    public Patient(int patientId, String name, int age, String medicalHistory) {
        this.patientId = patientId;
        this.name = name;
        this.age = age;
        this.medicalHistory = medicalHistory;
    }

    // Getters & Setters (Encapsulation)
    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getMedicalHistory() {
        return medicalHistory;
    }

    public void setMedicalHistory(String medicalHistory) {
        this.medicalHistory = medicalHistory;
    }

    // Public safe summary (no sensitive data)
    public void getSummary() {
        System.out.println("Patient ID   : " + patientId);
        System.out.println("Patient Name : " + name);
        System.out.println("Patient Age  : " + age);
    }

    // Polymorphic method (will be overridden)
    public void displayInfo() {
        System.out.println("---- Patient Information ----");
        getSummary();
    }
}
