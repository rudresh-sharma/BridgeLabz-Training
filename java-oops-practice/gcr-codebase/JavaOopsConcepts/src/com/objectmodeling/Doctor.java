package com.objectmodeling;

import java.util.ArrayList;

public class Doctor {
    private String name;
    private ArrayList<Patient> patients;

    public Doctor(String name) {
        this.name = name;
        patients = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    // Communication
    public void consult(Patient patient) {
        patients.add(patient);
        patient.addDoctor(this);

        System.out.println("Dr. " + name + " is consulting patient " + patient.getName());
    }
}
