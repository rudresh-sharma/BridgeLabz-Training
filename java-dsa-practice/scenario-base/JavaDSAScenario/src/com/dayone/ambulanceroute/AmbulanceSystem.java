package com.dayone.ambulanceroute;

import java.util.Scanner;

public class AmbulanceSystem {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        AmbulanceRoute route = new AmbulanceRoute();
        boolean exit = false;

        // Hardcode some initial units
        route.addUnit("Emergency", true);
        route.addUnit("Radiology", false);
        route.addUnit("Surgery", true);
        route.addUnit("ICU", false);

        while (!exit) {
            System.out.println("\n===== Ambulance Route Menu =====");
            System.out.println("1. Show Units");
            System.out.println("2. Dispatch Patient");
            System.out.println("3. Add Unit");
            System.out.println("4. Remove Unit (Under Maintenance)");
            System.out.println("5. Exit");
            System.out.print("Enter your choice: ");
            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    route.showUnits();
                    break;
                case 2:
                    route.dispatchPatient();
                    break;
                case 3:
                    System.out.print("Enter unit name: ");
                    String name = sc.nextLine();
                    System.out.print("Is unit available? (true/false): ");
                    boolean avail = sc.nextBoolean();
                    sc.nextLine(); // consume newline
                    route.addUnit(name, avail);
                    break;
                case 4:
                    System.out.print("Enter unit name to remove: ");
                    String removeName = sc.nextLine();
                    route.removeUnit(removeName);
                    break;
                case 5:
                    exit = true;
                    System.out.println("Exiting Ambulance System. Stay safe!");
                    break;
                default:
                    System.out.println("Invalid choice!");
                    break;
            }
        }

        sc.close();
    }
}
