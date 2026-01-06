package com.dayfour.fittrack;

import java.util.Scanner;

public class FitTrackApp {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Name: ");
        String name = sc.nextLine();

        System.out.print("Enter Age: ");
        int age = sc.nextInt();

        System.out.print("Enter Weight: ");
        double weight = sc.nextDouble();

        System.out.println("1. Default Goal (500 cal)");
        System.out.println("2. Custom Goal");
        int g = sc.nextInt();

        UserProfile user;
        if (g == 2) {
            System.out.print("Enter Daily Calorie Target: ");
            int target = sc.nextInt();
            user = new UserProfile(name, age, weight, target);
        } else {
            user = new UserProfile(name, age, weight);
        }

        int totalBurned = 0;

        while (true) {
            System.out.println("\n--- FITTRACK MENU ---");
            System.out.println("1. Cardio Workout");
            System.out.println("2. Strength Workout");
            System.out.println("3. View Progress");
            System.out.println("4. Exit");

            int choice = sc.nextInt();

            if (choice == 4) break;

            if (choice == 1 || choice == 2) {
                System.out.print("Enter duration (minutes): ");
                int d = sc.nextInt();

                Workout w;
                if (choice == 1)
                    w = new CardioWorkout(d);
                else
                    w = new StrengthWorkout(d);

                w.startWorkout();
                w.calculateCalories();
                w.stopWorkout();

                totalBurned += w.getCaloriesBurned();

                System.out.println("Calories burned: " + w.getCaloriesBurned());
            }
            else if (choice == 3) {
                int remaining = user.getDailyTarget() - totalBurned; // operator usage
                System.out.println("Total Burned: " + totalBurned);
                System.out.println("Remaining Calories: " + remaining);
            }
        }

        System.out.println("Thank you for using FitTrack 💪");
    }
}
