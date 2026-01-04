package com.datastructure.linkedlist.doublylinkedlist.moviemanagementsystem;

public class MovieDoublyLinkedList {
    DNode head;
    DNode tail;

    // Add at Beginning
    public void addAtBeginning(Movie m) {
        DNode newNode = new DNode(m);

        if (head == null) {
            head = tail = newNode;
        } else {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }
    }

    // Add at End
    public void addAtEnd(Movie m) {
        DNode newNode = new DNode(m);

        if (tail == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
    }

    // Add at Specific Position
    public void addAtPosition(Movie m, int pos) {
        if (pos == 1) {
            addAtBeginning(m);
            return;
        }

        DNode temp = head;

        for (int i = 1; i < pos - 1 && temp != null; i++)
            temp = temp.next;

        if (temp == null || temp == tail) {
            addAtEnd(m);
            return;
        }

        DNode newNode = new DNode(m);
        newNode.next = temp.next;
        newNode.prev = temp;
        temp.next.prev = newNode;
        temp.next = newNode;
    }

    // Remove by Title
    public void removeByTitle(String title) {
        DNode temp = head;

        while (temp != null) {
            if (temp.data.title.equalsIgnoreCase(title)) {

                if (temp == head)
                    head = temp.next;

                if (temp == tail)
                    tail = temp.prev;

                if (temp.prev != null)
                    temp.prev.next = temp.next;

                if (temp.next != null)
                    temp.next.prev = temp.prev;

                System.out.println("Movie removed");
                return;
            }
            temp = temp.next;
        }
        System.out.println("Movie not found");
    }

    // Search by Director
    public void searchByDirector(String director) {
        DNode temp = head;
        boolean found = false;

        while (temp != null) {
            if (temp.data.director.equalsIgnoreCase(director)) {
                temp.data.display();
                found = true;
            }
            temp = temp.next;
        }

        if (!found)
            System.out.println("No movies found");
    }

    // Search by Rating
    public void searchByRating(double rating) {
        DNode temp = head;
        boolean found = false;

        while (temp != null) {
            if (temp.data.rating == rating) {
                temp.data.display();
                found = true;
            }
            temp = temp.next;
        }

        if (!found)
            System.out.println("No movies found");
    }

    // Update Rating
    public void updateRating(String title, double newRating) {
        DNode temp = head;

        while (temp != null) {
            if (temp.data.title.equalsIgnoreCase(title)) {
                temp.data.rating = newRating;
                System.out.println("Rating updated");
                return;
            }
            temp = temp.next;
        }
        System.out.println("Movie not found");
    }

    // Display Forward
    public void displayForward() {
        DNode temp = head;
        if (temp == null) {
            System.out.println("No movies");
            return;
        }

        while (temp != null) {
            temp.data.display();
            temp = temp.next;
        }
    }

    // Display Reverse
    public void displayReverse() {
        DNode temp = tail;
        if (temp == null) {
            System.out.println("No movies");
            return;
        }

        while (temp != null) {
            temp.data.display();
            temp = temp.prev;
        }
    }
}
