package com.healthclinicapp.model;

/** POJO for the {@code disease} table. */
public class Disease {

    private int    diseaseId;
    private String name;
    private String icdCode;
    private String description;
    private String category;

    public Disease() {}

    public Disease(int diseaseId, String name, String icdCode,
                   String description, String category) {
        this.diseaseId   = diseaseId;
        this.name        = name;
        this.icdCode     = icdCode;
        this.description = description;
        this.category    = category;
    }

    public int    getDiseaseId()                { return diseaseId; }
    public void   setDiseaseId(int id)          { this.diseaseId = id; }
    public String getName()                     { return name; }
    public void   setName(String n)             { this.name = n; }
    public String getIcdCode()                  { return icdCode; }
    public void   setIcdCode(String c)          { this.icdCode = c; }
    public String getDescription()              { return description; }
    public void   setDescription(String d)      { this.description = d; }
    public String getCategory()                 { return category; }
    public void   setCategory(String c)         { this.category = c; }

    @Override
    public String toString() {
        return "Disease{id=" + diseaseId + ", name='" + name + "', icd='" + icdCode + "'}";
    }
}
