package com.dayten.avltree.gamingapp;

import java.util.Scanner;

public class LeaderboardDemo {

    public static void main(String[] args) {

        Leaderboard lb = new Leaderboard();
        Scanner sc = new Scanner(System.in);
        int choice = -1;

        System.out.println(" Welcome to Real-Time Leaderboard!");

        while (choice != 0) {
            System.out.println("\nSelect an option:");
            System.out.println("1️ Insert/Update Player");
            System.out.println("2️ Display Top N Players");
            System.out.println("3️ Remove Player");
            System.out.println("0️ Exit");

            System.out.print("Enter choice: ");
            choice = sc.nextInt();
            sc.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    System.out.print("Enter player name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter player points: ");
                    int points = sc.nextInt();
                    sc.nextLine();
                    lb.addOrUpdatePlayer(name, points);
                    System.out.println("✅ Player added/updated successfully.");
                    break;

                case 2:
                    System.out.print("Enter number of top players to display: ");
                    int n = sc.nextInt();
                    sc.nextLine();
                    lb.displayTopPlayers(n);
                    break;

                case 3:
                    System.out.print("Enter player name to remove: ");
                    String removeName = sc.nextLine();
                    System.out.print("Enter player points to remove: ");
                    int removePoints = sc.nextInt();
                    sc.nextLine();
                    lb.removePlayer(removeName, removePoints);
                    System.out.println(" Player removed if existed.");
                    break;

                case 0:
                    System.out.println(" Exiting leaderboard. Goodbye!");
                    break;

                default:
                    System.out.println(" Invalid choice! Try again.");
            }
        }

        sc.close();
    }
}
