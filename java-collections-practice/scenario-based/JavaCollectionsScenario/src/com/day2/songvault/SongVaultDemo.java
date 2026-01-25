package com.day2.songvault;

import java.util.Scanner;

public class SongVaultDemo {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        SongVaultManager<Song> manager = new SongVaultManager<>();

        int choice = -1;

        System.out.println(" Welcome to SongVault");

        while (choice != 0) {
            System.out.println("\nSelect an option:");
            System.out.println("1️ Load Songs from Folder");
            System.out.println("2️ Display All Songs");
            System.out.println("3️ Display Songs by Genre");
            System.out.println("4️ Display Unique Artists");
            System.out.println("5️ Filter Songs by Artist");
            System.out.println("6️ Sort Songs by Title");
            System.out.println("0️ Exit");

            System.out.print("Enter choice: ");
            String input = sc.nextLine();

            try {
                choice = Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.println("❌ Invalid input!");
                continue;
            }

            switch (choice) {
                case 1:
                    System.out.print("Enter folder path containing song files: ");
                    String path = sc.nextLine();
                    manager.loadSongsFromFolder(path);
                    break;

                case 2:
                    manager.displayAllSongs();
                    break;

                case 3:
                    manager.displayByGenre();
                    break;

                case 4:
                    manager.displayUniqueArtists();
                    break;

                case 5:
                    System.out.print("Enter artist name: ");
                    String artist = sc.nextLine();
                    manager.filterByArtist(artist);
                    break;

                case 6:
                    manager.sortByTitle();
                    break;

                case 0:
                    System.out.println("👋 Exiting SongVault. Goodbye!");
                    break;

                default:
                    System.out.println("❌ Invalid choice!");
            }
        }

        sc.close();
    }
}
