package com.objectmodeling;

import java.util.ArrayList;

public class Hospital {
    private String hospitalName;
    private ArrayList<Doctor> doctors;
    private ArrayList<Patient> patients;

    public Hospital(String hospitalName) {
        this.hospitalName = hospitalName;
        doctors = new ArrayList<>();
        patients = new ArrayList<>();
    }

    public void addDoctor(Doctor doctor) {
        doctors.add(doctor);
    }

    public void addPatient(Patient patient) {
        patients.add(patient);
    }

    public void showHospital() {
        System.out.println("\nHospital: " + hospitalName);
        System.out.println("Doctors: " + doctors.size());
        System.out.println("Patients: " + patients.size());
    }
}
