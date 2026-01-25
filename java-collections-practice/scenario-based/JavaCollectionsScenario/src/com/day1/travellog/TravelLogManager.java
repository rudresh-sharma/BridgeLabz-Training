package com.day1.travellog;

import java.io.*;
import java.util.*;
import java.util.regex.*;

public class TravelLogManager {

    private static final String FILE_NAME = "trips.dat";

    // Save a trip (append to file)
    public void saveTrip(Trip trip) {
        List<Trip> trips = loadAllTrips();
        trips.add(trip);

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(FILE_NAME))) {
            oos.writeObject(trips);
            System.out.println("✅ Trip saved successfully.");
        } catch (IOException e) {
            System.out.println("❌ Error saving trip: " + e.getMessage());
        }
    }

    // Load all trips from file
    @SuppressWarnings("unchecked")
    public List<Trip> loadAllTrips() {
        File file = new File(FILE_NAME);

        if (!file.exists()) {
            return new ArrayList<>();
        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file))) {
            return (List<Trip>) ois.readObject();
        } catch (Exception e) {
            System.out.println("⚠️ No existing trips found. Starting fresh.");
            return new ArrayList<>();
        }
    }

    // 🔹 Scenario 1: Find all cities using regex over description
    public void searchCitiesByRegex(String regex) {
        List<Trip> trips = loadAllTrips();
        Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);

        System.out.println("\nCities matching regex '" + regex + "':");

        for (Trip trip : trips) {
            Matcher matcher = pattern.matcher(trip.getDescription());
            if (matcher.find()) {
                System.out.println("✔️ " + trip.getCity() + " - " + trip.getDescription());
            }
        }
    }

    // 🔹 Scenario 2: Trips longer than 5 days
    public void showLongTrips() {
        List<Trip> trips = loadAllTrips();

        System.out.println("\nTrips longer than 5 days:");
        for (Trip trip : trips) {
            if (trip.getDurationDays() > 5) {
                System.out.println(trip);
            }
        }
    }

    // 🔹 Scenario 3: Unique countries using Set
    public void showUniqueCountries() {
        List<Trip> trips = loadAllTrips();
        Set<String> countries = new HashSet<>();

        for (Trip trip : trips) {
            countries.add(trip.getCountry());
        }

        System.out.println("\nUnique countries visited:");
        for (String country : countries) {
            System.out.println("🌍 " + country);
        }
    }

    // 🔹 Scenario 4: Top 3 most visited cities
    public void showTop3Cities() {
        List<Trip> trips = loadAllTrips();
        Map<String, Integer> cityCount = new HashMap<>();

        for (Trip trip : trips) {
            cityCount.put(trip.getCity(),
                    cityCount.getOrDefault(trip.getCity(), 0) + 1);
        }

        // Sort by visit count descending
        List<Map.Entry<String, Integer>> list = new ArrayList<>(cityCount.entrySet());
        list.sort((a, b) -> b.getValue() - a.getValue());

        System.out.println("\nTop 3 most visited cities:");
        int limit = Math.min(3, list.size());
        for (int i = 0; i < limit; i++) {
            Map.Entry<String, Integer> entry = list.get(i);
            System.out.println((i + 1) + ". " + entry.getKey() + " -> " + entry.getValue() + " visits");
        }
    }

    // Display all trips
    public void displayAllTrips() {
        List<Trip> trips = loadAllTrips();

        if (trips.isEmpty()) {
            System.out.println("⚠️ No trips recorded yet.");
            return;
        }

        System.out.println("\nAll Trips:");
        for (Trip trip : trips) {
            System.out.println(trip);
        }
    }
}
