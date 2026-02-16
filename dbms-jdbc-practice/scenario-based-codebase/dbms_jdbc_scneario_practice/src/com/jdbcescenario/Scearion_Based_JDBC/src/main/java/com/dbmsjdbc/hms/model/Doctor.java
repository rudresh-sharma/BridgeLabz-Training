package com.dbmsjdbc.hms.model;

public class Doctor {

    private int id;
    private String name;
    private int specialtyId;
    private String contact;
    private double consultationFee;

    public Doctor(String name, int specialtyId,
                  String contact, double consultationFee) {
        this.name = name;
        this.specialtyId = specialtyId;
        this.contact = contact;
        this.consultationFee = consultationFee;
    }

    public String getName() { return name; }
    public int getSpecialtyId() { return specialtyId; }
    public String getContact() { return contact; }
    public double getConsultationFee() { return consultationFee; }
}
