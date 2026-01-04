package com.datastructure.linkedlist.singlylinkedlist.inventorymanagementsystem;

public class InventoryLinkedList {
    Node head;

    // Add at beginning
    public void addAtBeginning(Item item) {
        Node newNode = new Node(item);
        newNode.next = head;
        head = newNode;
    }

    // Add at end
    public void addAtEnd(Item item) {
        Node newNode = new Node(item);

        if (head == null) {
            head = newNode;
            return;
        }

        Node temp = head;
        while (temp.next != null)
            temp = temp.next;

        temp.next = newNode;
    }

    // Add at specific position
    public void addAtPosition(Item item, int pos) {
        if (pos == 1) {
            addAtBeginning(item);
            return;
        }

        Node newNode = new Node(item);
        Node temp = head;

        for (int i = 1; i < pos - 1 && temp != null; i++)
            temp = temp.next;

        if (temp == null) {
            System.out.println("Invalid Position");
            return;
        }

        newNode.next = temp.next;
        temp.next = newNode;
    }

    // Remove by Item ID
    public void removeById(int id) {
        if (head == null) return;

        if (head.data.itemId == id) {
            head = head.next;
            System.out.println("Item removed");
            return;
        }

        Node temp = head;
        while (temp.next != null && temp.next.data.itemId != id)
            temp = temp.next;

        if (temp.next == null)
            System.out.println("Item not found");
        else {
            temp.next = temp.next.next;
            System.out.println("Item removed");
        }
    }

    // Update quantity
    public void updateQuantity(int id, int newQty) {
        Node temp = head;

        while (temp != null) {
            if (temp.data.itemId == id) {
                temp.data.quantity = newQty;
                System.out.println("Quantity updated");
                return;
            }
            temp = temp.next;
        }
        System.out.println("Item not found");
    }

    // Search by ID
    public void searchById(int id) {
        Node temp = head;

        while (temp != null) {
            if (temp.data.itemId == id) {
                temp.data.display();
                return;
            }
            temp = temp.next;
        }
        System.out.println("Item not found");
    }

    // Search by Name
    public void searchByName(String name) {
        Node temp = head;
        boolean found = false;

        while (temp != null) {
            if (temp.data.name.equalsIgnoreCase(name)) {
                temp.data.display();
                found = true;
            }
            temp = temp.next;
        }

        if (!found)
            System.out.println("No item found");
    }

    // Display all
    public void displayAll() {
        Node temp = head;
        if (temp == null) {
            System.out.println("Inventory empty");
            return;
        }

        while (temp != null) {
            temp.data.display();
            temp = temp.next;
        }
    }

    // Total inventory value
    public void totalValue() {
        double sum = 0;
        Node temp = head;

        while (temp != null) {
            sum += temp.data.getTotalValue();
            temp = temp.next;
        }

        System.out.println("Total Inventory Value = " + sum);
    }

    // ------------------ SORTING ------------------

    // Sort by Name (Bubble sort)
    public void sortByName(boolean asc) {
        if (head == null) return;

        for (Node i = head; i.next != null; i = i.next) {
            for (Node j = i.next; j != null; j = j.next) {
                if ((asc && i.data.name.compareToIgnoreCase(j.data.name) > 0) ||
                    (!asc && i.data.name.compareToIgnoreCase(j.data.name) < 0)) {
                    Item temp = i.data;
                    i.data = j.data;
                    j.data = temp;
                }
            }
        }
        System.out.println("Sorted by Name");
    }

    // Sort by Price
    public void sortByPrice(boolean asc) {
        if (head == null) return;

        for (Node i = head; i.next != null; i = i.next) {
            for (Node j = i.next; j != null; j = j.next) {
                if ((asc && i.data.price > j.data.price) ||
                    (!asc && i.data.price < j.data.price)) {
                    Item temp = i.data;
                    i.data = j.data;
                    j.data = temp;
                }
            }
        }
        System.out.println("Sorted by Price");
    }
}
