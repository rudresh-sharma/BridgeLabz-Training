package com.day1.travellog;

import java.util.Scanner;

public class TravelLogDemo {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        TravelLogManager manager = new TravelLogManager();
        int choice = -1;

        System.out.println("🧳 Welcome to TravelLog - Trip Organizer!");

        while (choice != 0) {
            System.out.println("\nSelect an option:");
            System.out.println("1️ Add a new trip");
            System.out.println("2️ Display all trips");
            System.out.println("3️ Search cities using regex");
            System.out.println("4️ Show trips longer than 5 days");
            System.out.println("5️ Show unique countries visited");
            System.out.println("6️ Show top 3 most visited cities");
            System.out.println("0️ Exit");

            System.out.print("Enter choice: ");
            String input = sc.nextLine();

            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("❌ Invalid input! Enter a number.");
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.print("Enter city: ");
                    String city = sc.nextLine();

                    System.out.print("Enter country: ");
                    String country = sc.nextLine();

                    System.out.print("Enter duration (days): ");
                    int days = Integer.parseInt(sc.nextLine());

                    System.out.print("Enter description: ");
                    String desc = sc.nextLine();

                    Trip trip = new Trip(city, country, days, desc);
                    manager.saveTrip(trip);
                    break;

                case 2:
                    manager.displayAllTrips();
                    break;

                case 3:
                    System.out.print("Enter regex to search in descriptions: ");
                    String regex = sc.nextLine();
                    manager.searchCitiesByRegex(regex);
                    break;

                case 4:
                    manager.showLongTrips();
                    break;

                case 5:
                    manager.showUniqueCountries();
                    break;

                case 6:
                    manager.showTop3Cities();
                    break;

                case 0:
                    System.out.println("👋 Exiting TravelLog. Happy Traveling!");
                    break;

                default:
                    System.out.println("❌ Invalid choice! Try again.");
            }
        }

        sc.close();
    }
}
