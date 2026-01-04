package com.datastructure.linkedlist.singlylinkedlist.studentrecordmanagement;

public class StudentLinkedList {
    Node head;

    // Add at beginning
    public void addAtBeginning(Student s) {
        Node newNode = new Node(s);
        newNode.next = head;
        head = newNode;
    }

    // Add at end
    public void addAtEnd(Student s) {
        Node newNode = new Node(s);

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
    public void addAtPosition(Student s, int pos) {
        if (pos == 1) {
            addAtBeginning(s);
            return;
        }

        Node newNode = new Node(s);
        Node temp = head;

        for (int i = 1; i < pos - 1 && temp != null; i++)
            temp = temp.next;

        if (temp == null) {
            System.out.println("Invalid Position!");
            return;
        }

        newNode.next = temp.next;
        temp.next = newNode;
    }

    // Delete by roll number
    public void delete(int rollNo) {
        if (head == null) return;

        if (head.data.rollNo == rollNo) {
            head = head.next;
            System.out.println("Student Deleted");
            return;
        }

        Node temp = head;
        while (temp.next != null && temp.next.data.rollNo != rollNo)
            temp = temp.next;

        if (temp.next == null) {
            System.out.println("Student not found");
        } else {
            temp.next = temp.next.next;
            System.out.println("Student Deleted");
        }
    }

    // Search student
    public void search(int rollNo) {
        Node temp = head;

        while (temp != null) {
            if (temp.data.rollNo == rollNo) {
                temp.data.display();
                return;
            }
            temp = temp.next;
        }

        System.out.println("Student not found");
    }

    // Update grade
    public void updateGrade(int rollNo, String newGrade) {
        Node temp = head;

        while (temp != null) {
            if (temp.data.rollNo == rollNo) {
                temp.data.grade = newGrade;
                System.out.println("Grade Updated");
                return;
            }
            temp = temp.next;
        }

        System.out.println("Student not found");
    }

    // Display all
    public void displayAll() {
        Node temp = head;

        if (temp == null) {
            System.out.println("No records found");
            return;
        }

        while (temp != null) {
            temp.data.display();
            temp = temp.next;
        }
    }
}
