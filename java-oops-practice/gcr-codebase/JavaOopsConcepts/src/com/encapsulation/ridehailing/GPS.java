package com.encapsulation.ridehailing;

public interface GPS {
    String getCurrentLocation();
    void updateLocation(String newLocation);
}
