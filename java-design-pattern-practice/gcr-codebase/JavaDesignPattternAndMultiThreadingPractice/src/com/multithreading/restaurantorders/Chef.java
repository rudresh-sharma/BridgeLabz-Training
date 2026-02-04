package com.multithreading.restaurantorders;

public class Chef extends Thread {

    private final String dishName;
    private final int totalTimeSeconds;  // in seconds

    public Chef(String chefName, String dishName, int totalTimeSeconds) {
        super(chefName);  // sets thread name
        this.dishName = dishName;
        this.totalTimeSeconds = totalTimeSeconds;
    }

    @Override
    public void run() {
        String chefName = getName();

        System.out.println(chefName + " started preparing " + dishName);

        // Total sleep time in milliseconds
        long totalSleepMs = totalTimeSeconds * 1000L;
        long sleepPerStep = totalSleepMs / 4;  // 4 steps: 25%,50%,75%,100%

        try {
            // 25%
            Thread.sleep(sleepPerStep);
            System.out.println(chefName + " preparing " + dishName + ": 25% complete");

            // 50%
            Thread.sleep(sleepPerStep);
            System.out.println(chefName + " preparing " + dishName + ": 50% complete");

            // 75%
            Thread.sleep(sleepPerStep);
            System.out.println(chefName + " preparing " + dishName + ": 75% complete");

            // 100%
            Thread.sleep(sleepPerStep);
            System.out.println(chefName + " finished preparing " + dishName + " (100% complete)");

        } catch (InterruptedException e) {
            System.out.println(chefName + " was interrupted while preparing " + dishName);
            Thread.currentThread().interrupt();
        }
    }
}