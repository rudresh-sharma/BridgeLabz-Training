package com.multithreading.restaurantorders;

public class RestaurantManager {

    public static void main(String[] args) {

        System.out.println("Restaurant Order Processing System Started\n");

        // Create chefs with different dishes and preparation times
        Chef chef1 = new Chef("Chef-1", "Pizza",  3);
        Chef chef2 = new Chef("Chef-2", "Pasta",  2);
        Chef chef3 = new Chef("Chef-3", "Salad",  1);
        Chef chef4 = new Chef("Chef-4", "Burger", 3);

        // Start all chefs (concurrent preparation)
        chef1.start();
        chef2.start();
        chef3.start();
        chef4.start();

        // Wait for all chefs to complete their dishes
        try {
            chef1.join();
            chef2.join();
            chef3.join();
            chef4.join();
        } catch (InterruptedException e) {
            System.out.println("Manager interrupted while waiting for chefs.");
            Thread.currentThread().interrupt();
        }

        System.out.println("\nAll orders completed successfully!");
        System.out.println("Kitchen closed - All orders completed");
    }
}