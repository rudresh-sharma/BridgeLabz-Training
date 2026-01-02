package com.encapsulation.hospitalmanagement;

import java.util.ArrayList;

public abstract class Patient {

    private String patientId;
    private String name;
    private int age;

    // Sensitive data (encapsulated)
    private ArrayList<String> medicalHistory = new ArrayList<>();

    public Patient(String id, String name, int age) {
        this.patientId = id;
        this.name = name;
        this.age = age;
    }

    // Encapsulation (getters only)
    public String getPatientId() {
        return patientId;
    }

    public String getName() {
        return name;
    }

    protected void addToHistory(String record) {
        medicalHistory.add(record);
    }

    protected ArrayList<String> getHistory() {
        return medicalHistory;
    }

    // Concrete method
    public void getPatientDetails() {
        System.out.println("ID   : " + patientId);
        System.out.println("Name : " + name);
        System.out.println("Age  : " + age);
    }

    // Polymorphism
    public abstract double calculateBill();
}
