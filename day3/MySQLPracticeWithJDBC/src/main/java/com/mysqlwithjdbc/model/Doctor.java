package com.mysqlwithjdbc.model;

public class Doctor {

    private int doctorId;
    private String name;
    private String specialty;
    private int experience;
    private double consultationFee;

    public Doctor() {
    }

    public Doctor(String name,
            String specialty,
            int experience,
            double consultationFee) {

  this.name = name;
  this.specialty = specialty;
  this.experience = experience;
  this.consultationFee = consultationFee;
}
    
    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public int getExperience() {
        return experience;
    }

    public void setExperience(int experience) {
        this.experience = experience;
    }

    public double getConsultationFee() {
        return consultationFee;
    }

    public void setConsultationFee(double consultationFee) {
        this.consultationFee = consultationFee;
    }

    @Override
    public String toString() {
        return "Doctor{" +
                "doctorId=" + doctorId +
                ", name='" + name + '\'' +
                ", specialty='" + specialty + '\'' +
                ", experience=" + experience +
                ", consultationFee=" + consultationFee +
                '}';
    }
}