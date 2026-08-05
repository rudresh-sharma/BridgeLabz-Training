package com.healthclinicapp.model;

/** POJO for the {@code supplier} table. */
public class Supplier {

    private int    supplierId;
    private String name;
    private String contactPerson;
    private String phone;
    private String email;
    private String address;
    private boolean isActive;

    public Supplier() {}

    public Supplier(int supplierId, String name, String contactPerson,
                    String phone, String email, String address, boolean isActive) {
        this.supplierId    = supplierId;
        this.name          = name;
        this.contactPerson = contactPerson;
        this.phone         = phone;
        this.email         = email;
        this.address       = address;
        this.isActive      = isActive;
    }

    public int    getSupplierId()               { return supplierId; }
    public void   setSupplierId(int id)         { this.supplierId = id; }
    public String getName()                     { return name; }
    public void   setName(String n)             { this.name = n; }
    public String getContactPerson()            { return contactPerson; }
    public void   setContactPerson(String c)    { this.contactPerson = c; }
    public String getPhone()                    { return phone; }
    public void   setPhone(String p)            { this.phone = p; }
    public String getEmail()                    { return email; }
    public void   setEmail(String e)            { this.email = e; }
    public String getAddress()                  { return address; }
    public void   setAddress(String a)          { this.address = a; }
    public boolean isActive()                   { return isActive; }
    public void    setActive(boolean a)         { this.isActive = a; }

    @Override
    public String toString() {
        return "Supplier{id=" + supplierId + ", name='" + name + "'}";
    }
}
