package com.dayfive.parkease;
public class ParkingSlot {
    private int slotId;
    private String location;
    private String vehicleTypeAllowed;
    private boolean isOccupied;
    private Vehicle parkedVehicle;

    public ParkingSlot(int slotId, String location, String vehicleTypeAllowed) {
        this.slotId = slotId;
        this.location = location;
        this.vehicleTypeAllowed = vehicleTypeAllowed;
        this.isOccupied = false;
    }

    public boolean isAvailable() {
        return !isOccupied;
    }

    public boolean parkVehicle(Vehicle vehicle) {
        if (isOccupied) {
            return false;
        }

        if (!vehicle.getClass().getSimpleName().equalsIgnoreCase(vehicleTypeAllowed)) {
            System.out.println("Vehicle type not allowed in this slot.");
            return false;
        }

        this.parkedVehicle = vehicle;
        this.isOccupied = true;
        return true;
    }

    public void removeVehicle() {
        this.parkedVehicle = null;
        this.isOccupied = false;
    }

    public Vehicle getParkedVehicle() {
        return parkedVehicle;
    }

    public int getSlotId() {
        return slotId;
    }
}
