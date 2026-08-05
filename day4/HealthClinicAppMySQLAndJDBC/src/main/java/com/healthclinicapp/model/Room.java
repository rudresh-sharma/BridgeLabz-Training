package com.healthclinicapp.model;

/** POJO for the {@code room} table. */
public class Room {

    private int    roomId;
    private String roomNumber;
    private String roomType;      // General / Private / ICU / Emergency
    private Integer departmentId;
    private int    capacity;
    private boolean isAvailable;
    private double dailyRate;

    // ── Transient ─────────────────────────────────────────────────────────────
    private String departmentName;

    public Room() {}

    public Room(int roomId, String roomNumber, String roomType, Integer departmentId,
                int capacity, boolean isAvailable, double dailyRate) {
        this.roomId       = roomId;
        this.roomNumber   = roomNumber;
        this.roomType     = roomType;
        this.departmentId = departmentId;
        this.capacity     = capacity;
        this.isAvailable  = isAvailable;
        this.dailyRate    = dailyRate;
    }

    public int    getRoomId()                   { return roomId; }
    public void   setRoomId(int id)             { this.roomId = id; }
    public String getRoomNumber()               { return roomNumber; }
    public void   setRoomNumber(String n)       { this.roomNumber = n; }
    public String getRoomType()                 { return roomType; }
    public void   setRoomType(String t)         { this.roomType = t; }
    public Integer getDepartmentId()            { return departmentId; }
    public void    setDepartmentId(Integer id)  { this.departmentId = id; }
    public int    getCapacity()                 { return capacity; }
    public void   setCapacity(int c)            { this.capacity = c; }
    public boolean isAvailable()                { return isAvailable; }
    public void    setAvailable(boolean a)      { this.isAvailable = a; }
    public double getDailyRate()                { return dailyRate; }
    public void   setDailyRate(double r)        { this.dailyRate = r; }
    public String getDepartmentName()           { return departmentName; }
    public void   setDepartmentName(String n)   { this.departmentName = n; }

    @Override
    public String toString() {
        return "Room{id=" + roomId + ", no='" + roomNumber + "', type='" + roomType +
               "', available=" + isAvailable + "}";
    }
}
