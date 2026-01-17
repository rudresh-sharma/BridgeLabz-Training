package com.daysix.artexpo;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class ArtExpoMain {

    public static void main(String[] args) {

        Scanner in = new Scanner(System.in);
        ArrayList<ArtistData> artists = new ArrayList<>();

        String choice;

        do {
            System.out.print("Enter Artist Name: ");
            String name = in.nextLine();

            System.out.print("Enter Registration Time (HH:mm): ");
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("H:mm");
            LocalTime time = LocalTime.parse(in.nextLine(), formatter);

            // add artist
            artists.add(new ArtistData(name, time));

            // insert newly added artist into sorted position
            InsertionSort.insertSorted(artists, artists.size() - 1);

            System.out.print("Do you want to add another artist? (yes/no): ");
            choice = in.nextLine();

        } while (choice.equalsIgnoreCase("yes"));

        System.out.println("\nSorted Artist Registration List:");
        for (ArtistData a : artists) {
            System.out.println(a);
        }

        in.close();
    }
}
