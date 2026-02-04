package com.multithreading.threadstatemonitor;

public class ThreadStateApp {
    public static void main(String[] args) {
        // Create two task runners
        TaskRunner task1 = new TaskRunner("Task-1");
        TaskRunner task2 = new TaskRunner("Task-2");

        Thread t1 = new Thread(task1, "Task-1");
        Thread t2 = new Thread(task2, "Task-2");

        // Create monitor
        StateMonitor monitor = new StateMonitor(t1, t2);
        Thread monitorThread = new Thread(monitor, "Monitor");

        System.out.println("====== Thread State Monitor ======\n");

        // Start monitor first so it can catch NEW state
        monitorThread.start();

        // Small delay so monitor can see NEW state
        try {
            Thread.sleep(200);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // Start the task threads
        t1.start();
        t2.start();

        // Wait for everything to finish
        try {
            t1.join();
            t2.join();
            monitorThread.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            System.out.println("Main thread interrupted");
        }

        System.out.println("\nAll threads have completed.");
    }
}