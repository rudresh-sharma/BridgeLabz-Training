package com.inheritance;

public class Device {

    private String deviceId;
    private String status;   // ON or OFF

    // Constructor
    public Device(String deviceId, String status) {
        this.deviceId = deviceId;
        this.status = status;
    }

    // Getters
    public String getDeviceId() {
        return deviceId;
    }

    public String getStatus() {
        return status;
    }

    // Method to display device status
    public void displayStatus() {
        System.out.println("Device ID : " + deviceId);
        System.out.println("Status : " + status);
    }
}
