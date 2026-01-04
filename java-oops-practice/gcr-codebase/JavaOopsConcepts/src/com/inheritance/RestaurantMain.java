package com.inheritance;

public class RestaurantMain {

    public static void main(String[] args) {

        Worker w1 = new Chef("Ramesh", 101, "Italian");
        Worker w2 = new Waiter("Suresh", 102, 8);

        performWork(w1);
        System.out.println();

        performWork(w2);
    }

    // Polymorphism with interface
    public static void performWork(Worker worker) {
        worker.performDuties();
    }
}
