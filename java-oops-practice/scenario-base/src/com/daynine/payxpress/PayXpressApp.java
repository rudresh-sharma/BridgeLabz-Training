package com.daynine.payxpress;
import java.util.Scanner;

public class PayXpressApp {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter bill type (Electricity / Water / Internet): ");
        String type = sc.nextLine();

        System.out.print("Enter amount: ");
        double amount = sc.nextDouble();
        sc.nextLine();

        System.out.print("Enter due date: ");
        String date = sc.nextLine();

        Bill bill = null;

        if (type.equalsIgnoreCase("Electricity"))
            bill = new ElectricityBill(amount, date);
        else if (type.equalsIgnoreCase("Water"))
            bill = new WaterBill(amount, date);
        else if (type.equalsIgnoreCase("Internet"))
            bill = new InternetBill(amount, date);
        else {
            System.out.println("Invalid bill type");
            return;
        }

        while (true) {
            System.out.println("\n--- PayXpress Menu ---");
            System.out.println("1. View Reminder");
            System.out.println("2. Pay Bill");
            System.out.println("3. Late Fee");
            System.out.println("4. Exit");
            System.out.print("Choose: ");
            int choice = sc.nextInt();

            if (choice == 1)
                bill.sendReminder();

            else if (choice == 2)
                bill.pay();

            else if (choice == 3) {
                System.out.print("Enter penalty amount: ");
                double penalty = sc.nextDouble();
                System.out.println("Total with Late Fee: ₹" + bill.calculateLateFee(penalty));
            }

            else if (choice == 4) {
                System.out.println("Thank you for using PayXpress!");
                break;
            }
        }
    }
}
