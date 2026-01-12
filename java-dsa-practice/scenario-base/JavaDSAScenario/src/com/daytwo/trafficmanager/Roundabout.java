package com.daytwo.trafficmanager;

import java.util.*;

public class Roundabout {

    private Vehicle tail = null;   // Circular linked list tail
    private Queue<Vehicle> waitingQueue = new LinkedList<>();
    private final int MAX_QUEUE = 5;

    // Add vehicle to waiting queue
    public void addToQueue(String number) {
        if (waitingQueue.size() == MAX_QUEUE) {
            System.out.println("Queue Overflow! Vehicle must wait.");
            return;
        }
        waitingQueue.add(new Vehicle(number));
        System.out.println(number + " added to waiting queue.");
    }

    // Move vehicle from queue to roundabout
    public void enterRoundabout() {
        if (waitingQueue.isEmpty()) {
            System.out.println("Queue Underflow! No vehicles waiting.");
            return;
        }

        Vehicle v = waitingQueue.poll();

        if (tail == null) { // first vehicle
            tail = v;
            tail.next = tail;
        } else {
            v.next = tail.next;
            tail.next = v;
            tail = v;
        }

        System.out.println(v.number + " entered the roundabout.");
    }

    // Remove vehicle from roundabout
    public void exitRoundabout() {
        if (tail == null) {
            System.out.println("No vehicles in roundabout.");
            return;
        }

        Vehicle head = tail.next;

        if (head == tail) {
            System.out.println(head.number + " exited the roundabout.");
            tail = null;
        } else {
            System.out.println(head.number + " exited the roundabout.");
            tail.next = head.next;
        }
    }

    // Display roundabout
    public void displayRoundabout() {
        if (tail == null) {
            System.out.println("Roundabout is empty.");
            return;
        }

        Vehicle temp = tail.next;
        System.out.print("Roundabout: ");

        do {
            System.out.print(temp.number + " -> ");
            temp = temp.next;
        } while (temp != tail.next);

        System.out.println("(back to start)");
    }

    // Display waiting queue
    public void displayQueue() {
        System.out.print("Waiting Queue: ");
        for (Vehicle v : waitingQueue) {
            System.out.print(v.number + " ");
        }
        System.out.println();
    }
}
