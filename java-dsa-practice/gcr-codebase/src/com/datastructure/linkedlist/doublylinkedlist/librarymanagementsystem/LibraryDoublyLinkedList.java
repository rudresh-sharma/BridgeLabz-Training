package com.datastructure.linkedlist.doublylinkedlist.librarymanagementsystem;

public class LibraryDoublyLinkedList {
    DNode head;
    DNode tail;

    // Add at Beginning
    public void addAtBeginning(Book b) {
        DNode newNode = new DNode(b);

        if (head == null) {
            head = tail = newNode;
        } else {
            newNode.next = head;
            head.prev = newNode;
            head = newNode;
        }
    }

    // Add at End
    public void addAtEnd(Book b) {
        DNode newNode = new DNode(b);

        if (tail == null) {
            head = tail = newNode;
        } else {
            tail.next = newNode;
            newNode.prev = tail;
            tail = newNode;
        }
    }

    // Add at Position
    public void addAtPosition(Book b, int pos) {
        if (pos == 1) {
            addAtBeginning(b);
            return;
        }

        DNode temp = head;
        for (int i = 1; i < pos - 1 && temp != null; i++)
            temp = temp.next;

        if (temp == null || temp == tail) {
            addAtEnd(b);
            return;
        }

        DNode newNode = new DNode(b);
        newNode.next = temp.next;
        newNode.prev = temp;
        temp.next.prev = newNode;
        temp.next = newNode;
    }

    // Remove by Book ID
    public void removeById(int id) {
        DNode temp = head;

        while (temp != null) {
            if (temp.data.bookId == id) {

                if (temp == head)
                    head = temp.next;

                if (temp == tail)
                    tail = temp.prev;

                if (temp.prev != null)
                    temp.prev.next = temp.next;

                if (temp.next != null)
                    temp.next.prev = temp.prev;

                System.out.println("Book removed");
                return;
            }
            temp = temp.next;
        }
        System.out.println("Book not found");
    }

    // Search by Title
    public void searchByTitle(String title) {
        DNode temp = head;
        boolean found = false;

        while (temp != null) {
            if (temp.data.title.equalsIgnoreCase(title)) {
                temp.data.display();
                found = true;
            }
            temp = temp.next;
        }

        if (!found)
            System.out.println("No book found");
    }

    // Search by Author
    public void searchByAuthor(String author) {
        DNode temp = head;
        boolean found = false;

        while (temp != null) {
            if (temp.data.author.equalsIgnoreCase(author)) {
                temp.data.display();
                found = true;
            }
            temp = temp.next;
        }

        if (!found)
            System.out.println("No book found");
    }

    // Update Availability
    public void updateAvailability(int id, boolean status) {
        DNode temp = head;

        while (temp != null) {
            if (temp.data.bookId == id) {
                temp.data.available = status;
                System.out.println("Availability updated");
                return;
            }
            temp = temp.next;
        }
        System.out.println("Book not found");
    }

    // Display Forward
    public void displayForward() {
        DNode temp = head;

        if (temp == null) {
            System.out.println("Library is empty");
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
            System.out.println("Library is empty");
            return;
        }

        while (temp != null) {
            temp.data.display();
            temp = temp.prev;
        }
    }

    // Count Books
    public void countBooks() {
        int count = 0;
        DNode temp = head;

        while (temp != null) {
            count++;
            temp = temp.next;
        }

        System.out.println("Total books = " + count);
    }
}
