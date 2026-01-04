package com.objectmodeling;

import java.util.ArrayList;

public class Patient {
    private String name;
    private ArrayList<Doctor> doctors;

    public Patient(String name) {
        this.name = name;
        doctors = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void addDoctor(Doctor doctor) {
        doctors.add(doctor);
    }

    public void showDoctors() {
        System.out.println("\nDoctors consulted by " + name + ":");
        for (Doctor d : doctors) {
            System.out.println("- Dr. " + d.getName());
        }
    }
}
