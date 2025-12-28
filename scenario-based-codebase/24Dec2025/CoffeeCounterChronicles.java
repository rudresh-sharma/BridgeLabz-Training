/*

Problem 1: The Coffee Counter Chronicles

Ravi runs a café where customers order different types of coffee with specific quantities.

Write a Java program that asks the user for the coffee type using switch, accepts quantity, calculates the total bill using price multiplied by quantity, adds GST using arithmetic operators, and continues serving customers using a while loop. The program should stop when the user types “exit”.

*/

import java.util.Scanner;
public class CoffeeCounterChronicles {
    public static void main(String[] args) {


	// Taking input
        Scanner in = new Scanner(System.in);
        final double GST_RATE = 0.10;   // 10% GST

        while (true) {

            System.out.println("\n☕ Types of Coffee Available:");
            System.out.println("Espresso   - 90 per unit");
            System.out.println("Latte      - 70 per unit");
            System.out.println("Cappuccino - 100 per unit");
            System.out.println("Americano  - 20 per unit");
            System.out.println("Type 'exit' to quit");

            System.out.print("\nEnter coffee type: ");
            String coffee = in.next();   // take coffee name or exit

            if (coffee.equalsIgnoreCase("exit")) {
                System.out.println("Thank you! Cafe closed ☕");
                break;
            }

            double price = 0;

            switch (coffee.toLowerCase()) {
                case "espresso":
                    price = 90;
                    break;
                case "latte":
                    price = 70;
                    break;
                case "cappuccino":
                    price = 100;
                    break;
                case "americano":
                    price = 20;
                    break;
                default:
                    System.out.println("Invalid coffee type. Try again.");
                    continue;
            }

            System.out.print("Enter quantity: ");
            int quantity = in.nextInt();

            double cost = price * quantity;
            double gst = cost * GST_RATE;
            double total = cost + gst;

            System.out.println("\n----- BILL -----");
            System.out.println("Coffee     : " + coffee);
            System.out.println("Quantity   : " + quantity);
            System.out.println("Price      : " + price);
            System.out.println("Cost       : " + cost);
            System.out.println("GST (10%)  : " + gst);
            System.out.println("Total Bill : " + total);
            System.out.println("----------------");
        }

        in.close();
    }
}













