package com.objectmodeling;

public class HospitalMain {
    public static void main(String[] args) {

        // Create Hospital
        Hospital hospital = new Hospital("City Hospital");

        // Create Doctors
        Doctor d1 = new Doctor("Sharma");
        Doctor d2 = new Doctor("Mehta");

        // Create Patients
        Patient p1 = new Patient("Ravi");
        Patient p2 = new Patient("Anita");

        // Add to hospital
        hospital.addDoctor(d1);
        hospital.addDoctor(d2);
        hospital.addPatient(p1);
        hospital.addPatient(p2);

        // Consultations (Association + Communication)
        d1.consult(p1);
        d1.consult(p2);
        d2.consult(p1);

        // Show patient info
        p1.showDoctors();
        p2.showDoctors();
    }
}
