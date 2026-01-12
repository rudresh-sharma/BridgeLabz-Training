package com.daytwo.traincompanion;

public class Train {

    private Compartment head;
    private Compartment tail;
    private Compartment current;

    // Add compartment at end
    public void addCompartment(String name) {
        Compartment newNode = new Compartment(name);

        if (head == null) {
            head = tail = current = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }

        System.out.println(name + " compartment added.");
    }

    // Move forward
    public void moveNext() {
        if (current != null && current.next != null) {
            current = current.next;
            System.out.println("Moved to " + current.name);
        } else {
            System.out.println("No next compartment.");
        }
    }

    // Move backward
    public void movePrev() {
        if (current != null && current.prev != null) {
            current = current.prev;
            System.out.println("Moved to " + current.name);
        } else {
            System.out.println("No previous compartment.");
        }
    }

    // Remove current compartment
    public void removeCurrent() {
        if (current == null) return;

        System.out.println("Removing " + current.name);

        if (current == head) {
            head = current.next;
        }
        if (current == tail) {
            tail = current.prev;
        }
        if (current.prev != null) {
            current.prev.next = current.next;
        }
        if (current.next != null) {
            current.next.prev = current.prev;
        }

        current = (current.next != null) ? current.next : current.prev;
    }

    // Show adjacent compartments
    public void showAdjacent() {
        if (current == null) return;

        System.out.println("Current: " + current.name);
        System.out.println("Previous: " + (current.prev != null ? current.prev.name : "None"));
        System.out.println("Next: " + (current.next != null ? current.next.name : "None"));
    }

    // Display all compartments
    public void displayTrain() {
        Compartment temp = head;
        System.out.print("Train: ");
        while (temp != null) {
            System.out.print(temp.name + " <-> ");
            temp = temp.next;
        }
        System.out.println("NULL");
    }
}
