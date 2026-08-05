package com.healthclinicapp.model;

/** POJO for the {@code emergency_contact} table (1-1 with patient). */
public class EmergencyContact {

    private int    contactId;
    private int    patientId;
    private String name;
    private String relationship;
    private String phone;

    public EmergencyContact() {}

    public EmergencyContact(int contactId, int patientId, String name,
                            String relationship, String phone) {
        this.contactId    = contactId;
        this.patientId    = patientId;
        this.name         = name;
        this.relationship = relationship;
        this.phone        = phone;
    }

    public int    getContactId()                { return contactId; }
    public void   setContactId(int id)          { this.contactId = id; }
    public int    getPatientId()                { return patientId; }
    public void   setPatientId(int id)          { this.patientId = id; }
    public String getName()                     { return name; }
    public void   setName(String n)             { this.name = n; }
    public String getRelationship()             { return relationship; }
    public void   setRelationship(String r)     { this.relationship = r; }
    public String getPhone()                    { return phone; }
    public void   setPhone(String p)            { this.phone = p; }

    @Override
    public String toString() {
        return "EmergencyContact{id=" + contactId + ", patientId=" + patientId +
               ", name='" + name + "', rel='" + relationship + "'}";
    }
}
