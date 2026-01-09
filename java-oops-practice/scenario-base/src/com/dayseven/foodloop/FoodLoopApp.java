package com.dayseven.foodloop;
import java.util.Scanner;

public class FoodLoopApp {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        VegItem v1 = new VegItem("Veg Burger", 120, true);
        VegItem v2 = new VegItem("Veg Pizza", 200, true);
        NonVegItem n1 = new NonVegItem("Chicken Burger", 180, true);
        NonVegItem n2 = new NonVegItem("Chicken Pizza", 250, false); // unavailable

        Order order = new Order();
        int choice;

        do {
            System.out.println("\n--- FoodLoop Menu ---");
            System.out.println("1. Veg Burger - ₹120");
            System.out.println("2. Veg Pizza - ₹200");
            System.out.println("3. Chicken Burger - ₹180");
            System.out.println("4. Chicken Pizza - ₹250 (Not Available)");
            System.out.println("5. Place Order");
            System.out.println("6. Cancel Order");
            System.out.print("Choose: ");
            choice = sc.nextInt();

            switch (choice) {
                case 1: order.addItem(v1); break;
                case 2: order.addItem(v2); break;
                case 3: order.addItem(n1); break;
                case 4: order.addItem(n2); break;
                case 5: order.placeOrder(); break;
                case 6: order.cancelOrder(); break;
            }
        } while (choice != 5);

        sc.close();
    }
}
