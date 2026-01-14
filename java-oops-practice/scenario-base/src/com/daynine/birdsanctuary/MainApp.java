package com.daynine.birdsanctuary;
import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Sanctuary s = new Sanctuary();

        while (true) {
            System.out.println("\nWelcome to EcoWing Bird Sanctuary");
            System.out.println("1. Add Bird");
            System.out.println("2. Display All Birds");
            System.out.println("3. Display All Flying Birds");
            System.out.println("4. Display All Swimming Birds");
            System.out.println("5. Display Both Flying & Swimming Birds");
            System.out.println("6. Delete Bird by ID");
            System.out.println("7. Sanctuary Report");
            System.out.println("8. Exit");

            int ch = sc.nextInt();
            sc.nextLine();

            if (ch == 1) {
                System.out.print("Enter ID: ");
                String id = sc.nextLine();
                System.out.print("Enter Name: ");
                String name = sc.nextLine();
                System.out.print("Type (Eagle/Duck/Penguin/Kiwi): ");
                String type = sc.nextLine();

                if (type.equalsIgnoreCase("Eagle")) s.addBird(new Eagle(id, name));
                else if (type.equalsIgnoreCase("Duck")) s.addBird(new Duck(id, name));
                else if (type.equalsIgnoreCase("Penguin")) s.addBird(new Penguin(id, name));
                else if (type.equalsIgnoreCase("Kiwi")) s.addBird(new Kiwi(id, name));
            }
            else if (ch == 2) s.displayAll();
            else if (ch == 3) s.displayFlyers();
            else if (ch == 4) s.displaySwimmers();
            else if (ch == 5) s.displayBoth();
            else if (ch == 6) {
                System.out.print("Enter ID: ");
                s.deleteById(sc.nextLine());
            }
            else if (ch == 7) s.report();
            else if (ch == 8) {
                s.save();
                System.out.println("Data saved. Goodbye!");
                break;
            }
        }
    }
}
