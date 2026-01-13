package com.dayeight.homenest;

public abstract class Device implements IControllable {

    private String deviceId;
    private boolean status;            // encapsulated
    protected double energyUsage;      // accessible to subclasses
    private String firmwareLog = "FW-1.0";

    public Device(String deviceId, double energyUsage) {
        this.deviceId = deviceId;
        this.energyUsage = energyUsage;
        this.status = false;
    }

    // Encapsulated status
    public boolean isOn() {
        return status;
    }

    protected void setStatus(boolean value) {
        this.status = value;
    }

    public String getDeviceId() {
        return deviceId;
    }

    protected void updateFirmware(String version) {
        firmwareLog = version;
        System.out.println("Firmware updated to " + firmwareLog);
    }

    // operator usage
    public double getMonthlyEnergy(int hoursPerDay) {
        return energyUsage * hoursPerDay * 30;
    }
}
