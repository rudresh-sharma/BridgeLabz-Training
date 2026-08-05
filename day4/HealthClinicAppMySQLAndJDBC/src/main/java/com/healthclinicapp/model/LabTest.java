package com.healthclinicapp.model;

/** POJO for the {@code lab_test} table. */
public class LabTest {

    private int    testId;
    private String testName;
    private String description;
    private String normalRange;
    private String unit;
    private double price;

    public LabTest() {}

    public LabTest(int testId, String testName, String description,
                   String normalRange, String unit, double price) {
        this.testId      = testId;
        this.testName    = testName;
        this.description = description;
        this.normalRange = normalRange;
        this.unit        = unit;
        this.price       = price;
    }

    public int    getTestId()                  { return testId; }
    public void   setTestId(int id)            { this.testId = id; }
    public String getTestName()                { return testName; }
    public void   setTestName(String n)        { this.testName = n; }
    public String getDescription()             { return description; }
    public void   setDescription(String d)     { this.description = d; }
    public String getNormalRange()             { return normalRange; }
    public void   setNormalRange(String r)     { this.normalRange = r; }
    public String getUnit()                    { return unit; }
    public void   setUnit(String u)            { this.unit = u; }
    public double getPrice()                   { return price; }
    public void   setPrice(double p)           { this.price = p; }

    @Override
    public String toString() {
        return "LabTest{id=" + testId + ", name='" + testName + "'}";
    }
}
