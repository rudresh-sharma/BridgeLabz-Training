package com.healthclinicapp.model;

import java.sql.Date;

/** POJO for the {@code inventory} table (medicine purchase records). */
public class Inventory {

    private int    inventoryId;
    private int    medicineId;
    private Integer supplierId;
    private int    quantity;
    private Date   purchaseDate;
    private double unitCost;
    private Date   expiryDate;
    private String batchNumber;

    // ── Transient ─────────────────────────────────────────────────────────────
    private String medicineName;
    private String supplierName;

    public Inventory() {}

    public Inventory(int inventoryId, int medicineId, Integer supplierId,
                     int quantity, Date purchaseDate, double unitCost,
                     Date expiryDate, String batchNumber) {
        this.inventoryId  = inventoryId;
        this.medicineId   = medicineId;
        this.supplierId   = supplierId;
        this.quantity     = quantity;
        this.purchaseDate = purchaseDate;
        this.unitCost     = unitCost;
        this.expiryDate   = expiryDate;
        this.batchNumber  = batchNumber;
    }

    public int    getInventoryId()              { return inventoryId; }
    public void   setInventoryId(int id)        { this.inventoryId = id; }
    public int    getMedicineId()               { return medicineId; }
    public void   setMedicineId(int id)         { this.medicineId = id; }
    public Integer getSupplierId()              { return supplierId; }
    public void    setSupplierId(Integer id)    { this.supplierId = id; }
    public int    getQuantity()                 { return quantity; }
    public void   setQuantity(int q)            { this.quantity = q; }
    public Date   getPurchaseDate()             { return purchaseDate; }
    public void   setPurchaseDate(Date d)       { this.purchaseDate = d; }
    public double getUnitCost()                 { return unitCost; }
    public void   setUnitCost(double c)         { this.unitCost = c; }
    public Date   getExpiryDate()               { return expiryDate; }
    public void   setExpiryDate(Date d)         { this.expiryDate = d; }
    public String getBatchNumber()              { return batchNumber; }
    public void   setBatchNumber(String b)      { this.batchNumber = b; }
    public String getMedicineName()             { return medicineName; }
    public void   setMedicineName(String n)     { this.medicineName = n; }
    public String getSupplierName()             { return supplierName; }
    public void   setSupplierName(String n)     { this.supplierName = n; }

    @Override
    public String toString() {
        return "Inventory{id=" + inventoryId + ", medicineId=" + medicineId +
               ", qty=" + quantity + ", batch='" + batchNumber + "'}";
    }
}
