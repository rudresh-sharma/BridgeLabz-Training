package com.dayone.smarthome;

import java.util.*;

/**
 * User controller to manage all appliances
 * Demonstrates polymorphism: same turnOn()/turnOff() works differently per appliance
 */
public class UserController {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        List<Appliance> devices = new ArrayList<>();
        devices.add(new Light("Living Room", 60));
        devices.add(new Fan("Bedroom", 75));
        devices.add(new AC("Office", 1500, 24));

        while(true) {
            System.out.println("\n--- SMART HOME CONTROL ---");
            System.out.println("1. Show Device Status");
            System.out.println("2. Turn ON Device");
            System.out.println("3. Turn OFF Device");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");
            int choice = sc.nextInt(); sc.nextLine();

            switch(choice) {
                case 1:
                    for(int i=0; i<devices.size(); i++) {
                        System.out.print((i+1) + ". ");
                        devices.get(i).displayStatus();
                    }
                    break;

                case 2:
                    System.out.print("Select device to turn ON (index): ");
                    int onIdx = sc.nextInt(); sc.nextLine();
                    devices.get(onIdx-1).turnOn();
                    break;

                case 3:
                    System.out.print("Select device to turn OFF (index): ");
                    int offIdx = sc.nextInt(); sc.nextLine();
                    devices.get(offIdx-1).turnOff();
                    break;

                case 4:
                    System.out.println("Exiting Smart Home System.");
                    System.exit(0);

                default:
                    System.out.println("Invalid choice!");
            }
        }
    }
}
