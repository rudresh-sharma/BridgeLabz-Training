package com.healthclinicapp.model;

/** POJO for the {@code prescription_item} bridge table (Prescription ↔ Medicine M-M). */
public class PrescriptionItem {

    private int    itemId;
    private int    prescriptionId;
    private int    medicineId;
    private String dosage;
    private String frequency;
    private int    durationDays;
    private int    quantity;

    // ── Transient ────────────────────────────────────────────────────────────
    private String medicineName;

    // ── Constructors ─────────────────────────────────────────────────────────
    public PrescriptionItem() {}

    public PrescriptionItem(int itemId, int prescriptionId, int medicineId,
                            String dosage, String frequency, int durationDays, int quantity) {
        this.itemId         = itemId;
        this.prescriptionId = prescriptionId;
        this.medicineId     = medicineId;
        this.dosage         = dosage;
        this.frequency      = frequency;
        this.durationDays   = durationDays;
        this.quantity       = quantity;
    }

    // ── Getters & Setters ────────────────────────────────────────────────────
    public int    getItemId()                        { return itemId; }
    public void   setItemId(int id)                  { this.itemId = id; }
    public int    getPrescriptionId()                { return prescriptionId; }
    public void   setPrescriptionId(int id)          { this.prescriptionId = id; }
    public int    getMedicineId()                    { return medicineId; }
    public void   setMedicineId(int id)              { this.medicineId = id; }
    public String getDosage()                        { return dosage; }
    public void   setDosage(String d)                { this.dosage = d; }
    public String getFrequency()                     { return frequency; }
    public void   setFrequency(String f)             { this.frequency = f; }
    public int    getDurationDays()                  { return durationDays; }
    public void   setDurationDays(int d)             { this.durationDays = d; }
    public int    getQuantity()                      { return quantity; }
    public void   setQuantity(int q)                 { this.quantity = q; }
    public String getMedicineName()                  { return medicineName; }
    public void   setMedicineName(String n)          { this.medicineName = n; }

    @Override
    public String toString() {
        return "PrescriptionItem{id=" + itemId + ", prescId=" + prescriptionId +
               ", medicineId=" + medicineId + ", qty=" + quantity + "}";
    }
}
