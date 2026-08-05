package com.healthclinicapp.model;

/** POJO for the {@code medicine} table. */
public class Medicine {

    private int    medicineId;
    private String name;
    private String genericName;
    private String category;
    private String unit;           // tablet, ml, mg, …
    private double unitPrice;
    private int    stockQuantity;
    private int    minStockLevel;
    private String description;
    private boolean isActive;

    // ── Constructors ─────────────────────────────────────────────────────────
    public Medicine() {}

    public Medicine(int medicineId, String name, String genericName, String category,
                    String unit, double unitPrice, int stockQuantity, int minStockLevel,
                    String description, boolean isActive) {
        this.medicineId    = medicineId;
        this.name          = name;
        this.genericName   = genericName;
        this.category      = category;
        this.unit          = unit;
        this.unitPrice     = unitPrice;
        this.stockQuantity = stockQuantity;
        this.minStockLevel = minStockLevel;
        this.description   = description;
        this.isActive      = isActive;
    }

    // ── Getters & Setters ────────────────────────────────────────────────────
    public int    getMedicineId()               { return medicineId; }
    public void   setMedicineId(int id)         { this.medicineId = id; }
    public String getName()                     { return name; }
    public void   setName(String n)             { this.name = n; }
    public String getGenericName()              { return genericName; }
    public void   setGenericName(String g)      { this.genericName = g; }
    public String getCategory()                 { return category; }
    public void   setCategory(String c)         { this.category = c; }
    public String getUnit()                     { return unit; }
    public void   setUnit(String u)             { this.unit = u; }
    public double getUnitPrice()                { return unitPrice; }
    public void   setUnitPrice(double p)        { this.unitPrice = p; }
    public int    getStockQuantity()            { return stockQuantity; }
    public void   setStockQuantity(int q)       { this.stockQuantity = q; }
    public int    getMinStockLevel()            { return minStockLevel; }
    public void   setMinStockLevel(int m)       { this.minStockLevel = m; }
    public String getDescription()              { return description; }
    public void   setDescription(String d)      { this.description = d; }
    public boolean isActive()                   { return isActive; }
    public void    setActive(boolean a)         { this.isActive = a; }

    public boolean isLowStock() { return stockQuantity <= minStockLevel; }

    @Override
    public String toString() {
        return "Medicine{id=" + medicineId + ", name='" + name + "', stock=" + stockQuantity + "}";
    }
}
