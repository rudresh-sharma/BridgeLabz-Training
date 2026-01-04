package com.datastructure.linkedlist.circularlinkedlist.taskscheduler;

public class CircularTaskList {
    private Node head;
    private Node current;

    // Add at Beginning
    public void addAtBeginning(Task task) {
        Node newNode = new Node(task);

        if (head == null) {
            head = newNode;
            newNode.next = head;
            current = head;
            return;
        }

        Node temp = head;
        while (temp.next != head)
            temp = temp.next;

        newNode.next = head;
        temp.next = newNode;
        head = newNode;
    }

    // Add at End
    public void addAtEnd(Task task) {
        Node newNode = new Node(task);

        if (head == null) {
            head = newNode;
            newNode.next = head;
            current = head;
            return;
        }

        Node temp = head;
        while (temp.next != head)
            temp = temp.next;

        temp.next = newNode;
        newNode.next = head;
    }

    // Add at Position
    public void addAtPosition(Task task, int pos) {
        if (pos == 1) {
            addAtBeginning(task);
            return;
        }

        Node temp = head;
        for (int i = 1; i < pos - 1 && temp.next != head; i++)
            temp = temp.next;

        Node newNode = new Node(task);
        newNode.next = temp.next;
        temp.next = newNode;
    }

    // Remove by Task ID
    public void remove(int id) {
        if (head == null) return;

        Node curr = head;
        Node prev = null;

        do {
            if (curr.data.taskId == id) {
                if (curr == head) {
                    Node temp = head;
                    while (temp.next != head)
                        temp = temp.next;
                    head = head.next;
                    temp.next = head;
                } else {
                    prev.next = curr.next;
                }
                System.out.println("Task removed");
                return;
            }
            prev = curr;
            curr = curr.next;
        } while (curr != head);

        System.out.println("Task not found");
    }

    // View Current Task and Move Next
    public void nextTask() {
        if (current == null) {
            System.out.println("No tasks");
            return;
        }

        current.data.display();
        current = current.next;
    }

    // Display All Tasks
    public void displayAll() {
        if (head == null) {
            System.out.println("No tasks");
            return;
        }

        Node temp = head;
        do {
            temp.data.display();
            temp = temp.next;
        } while (temp != head);
    }

    // Search by Priority
    public void searchByPriority(int priority) {
        if (head == null) return;

        Node temp = head;
        boolean found = false;

        do {
            if (temp.data.priority == priority) {
                temp.data.display();
                found = true;
            }
            temp = temp.next;
        } while (temp != head);

        if (!found)
            System.out.println("No task found");
    }
}

