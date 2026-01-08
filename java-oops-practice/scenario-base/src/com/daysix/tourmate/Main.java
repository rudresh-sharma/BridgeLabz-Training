package com.daysix.tourmate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter trip type (Domestic/International): ");
        String tripType = sc.nextLine();

        System.out.print("Enter destination: ");
        String dest = sc.nextLine();

        System.out.print("Enter duration (days): ");
        int dur = sc.nextInt();
        sc.nextLine(); // consume newline

        System.out.print("Enter transport type: ");
        String tType = sc.nextLine();
        System.out.print("Enter transport cost: ");
        double tCost = sc.nextDouble();
        sc.nextLine();

        System.out.print("Enter hotel name: ");
        String hName = sc.nextLine();
        System.out.print("Enter hotel cost: ");
        double hCost = sc.nextDouble();
        sc.nextLine();

        System.out.print("Enter activity name: ");
        String aName = sc.nextLine();
        System.out.print("Enter activity cost: ");
        double aCost = sc.nextDouble();

        Transport transport = new Transport(tType, tCost);
        Hotel hotel = new Hotel(hName, hCost);
        Activity activity = new Activity(aName, aCost);

        Trip trip;

        if (tripType.equalsIgnoreCase("Domestic")) {
            trip = new DomesticTrip(dest, dur, transport, hotel, activity);
        } else {
            trip = new InternationalTrip(dest, dur, transport, hotel, activity);
        }

        trip.showDetails();
        transport.showTransport();
        hotel.showHotel();
        activity.showActivity();

        if (trip instanceof IBookable) {
            ((IBookable) trip).book();
        }
    }
}
