package com.datastructure.linkedlist.circularlinkedlist.roundrobin;

public class RoundRobinScheduler {

    private Node head = null;
    private Node tail = null;
    private int totalProcesses = 0;

    // Add process at end
    public void addProcess(Process p) {
        Node newNode = new Node(p);

        if (head == null) {
            head = tail = newNode;
            newNode.next = head;
        } else {
            tail.next = newNode;
            newNode.next = head;
            tail = newNode;
        }
        totalProcesses++;
    }

    // Remove completed process
    private void remove(Node prev, Node curr) {
        if (curr == head && curr == tail) {
            head = tail = null;
        }
        else if (curr == head) {
            head = head.next;
            tail.next = head;
        }
        else if (curr == tail) {
            tail = prev;
            tail.next = head;
        }
        else {
            prev.next = curr.next;
        }
    }

    // Display circular list
    private void displayQueue() {
        if (head == null) return;

        Node temp = head;
        do {
            System.out.print("P" + temp.process.pid + "(" + temp.process.remainingTime + ")  ");
            temp = temp.next;
        } while (temp != head);
        System.out.println();
    }

    // Round Robin Execution
    public void execute(int timeQuantum) {
        if (head == null) return;

        int time = 0;
        int completed = 0;
        double totalWT = 0, totalTAT = 0;

        Node curr = head;
        Node prev = tail;

        while (completed < totalProcesses) {
            System.out.println("\nQueue:");
            displayQueue();

            if (curr.process.remainingTime > 0) {
                int execTime = Math.min(timeQuantum, curr.process.remainingTime);
                time += execTime;
                curr.process.remainingTime -= execTime;

                // If process finishes
                if (curr.process.remainingTime == 0) {
                    completed++;
                    curr.process.turnaroundTime = time;
                    curr.process.waitingTime = time - curr.process.burstTime;

                    totalWT += curr.process.waitingTime;
                    totalTAT += curr.process.turnaroundTime;

                    remove(prev, curr);
                    curr = prev.next;
                    continue;
                }
            }

            prev = curr;
            curr = curr.next;
        }

        System.out.println("\n--- Scheduling Complete ---");
        System.out.println("Average Waiting Time: " + (totalWT / totalProcesses));
        System.out.println("Average Turnaround Time: " + (totalTAT / totalProcesses));
    }
}
