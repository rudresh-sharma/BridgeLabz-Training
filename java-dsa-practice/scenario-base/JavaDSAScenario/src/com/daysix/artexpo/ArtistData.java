package com.daysix.artexpo;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class ArtistData {

    private String artistName;
    private LocalTime registrationTime;

    public ArtistData(String artistName, LocalTime registrationTime) {
        this.artistName = artistName;
        this.registrationTime = registrationTime;
    }

    public String getArtistName() {
        return artistName;
    }

    public LocalTime getRegistrationTime() {
        return registrationTime;
    }

    public String getFormattedTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");
        return registrationTime.format(formatter);
    }

    @Override
    public String toString() {
        return artistName + " -> " + getFormattedTime();
    }
}
