package com.daynine.binarysearchtree.musicapp;

import java.util.Scanner;

public class MainApp {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Track root = null;
        int choice;

        do {
            System.out.println("\n=== Music App ===");
            System.out.println("1. Insert Song");
            System.out.println("2. Search a song using TrackId");
            System.out.println("3. Display Playlist (Alphabetically sorted)");
            System.out.println("0. Exit");

            System.out.print("Enter your choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {

                case 1:
                    System.out.print("Enter Song Title: ");
                    String title = sc.nextLine();

                    System.out.print("Enter Track Id: ");
                    String trackId = sc.nextLine();

                    // IMPORTANT: order is (trackId, title)
                    root = MusicBST.insert(root, new Track(trackId, title));
                    System.out.println("Song inserted successfully!");
                    break;

                case 2:
                    System.out.print("Enter TrackID to search: ");
                    String searchTrack = sc.nextLine();

                    Track found = MusicBST.search(root, searchTrack);

                    if (found != null) {
                        System.out.println("Song Found:");
                        System.out.println("Title    : " + found.getTitle());
                        System.out.println("Track Id : " + found.getTrackId());
                    } else {
                        System.out.println("Song not found!");
                    }
                    break;

                case 3:
                    System.out.println("\nSorted Playlist (Alphabetically)");
                    System.out.println("-------------------------------------------");
                    System.out.printf("%-15s %-20s%n", "TrackID", "Title");
                    System.out.println("-------------------------------------------");

                    MusicBST.displaySorted(root);
                    break;

                case 0:
                    System.out.println("Exiting Music App...");
                    break;

                default:
                    System.out.println("Invalid choice! Try again.");
            }

        } while (choice != 0);

        sc.close();
    }
}
