package com.collections.queueinterface;
import java.util.Scanner;

public class CircularBuffer {

    private int[] buffer;
    private int size;
    private int front = 0;
    private int count = 0;

    public CircularBuffer(int size) {
        this.size = size;
        buffer = new int[size];
    }

    public void insert(int value) {
        int index = (front + count) % size;

        if (count == size) {
            front = (front + 1) % size;
            index = (front + count - 1) % size;
        } else {
            count++;
        }

        buffer[index] = value;
    }

    public void display() {
        System.out.print("Buffer: [");
        for (int i = 0; i < count; i++) {
            System.out.print(buffer[(front + i) % size]);
            if (i < count - 1) {
                System.out.print(", ");
            }
        }
        System.out.println("]");
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter buffer size: ");
        int size = sc.nextInt();

        CircularBuffer cb = new CircularBuffer(size);

        System.out.print("Enter number of elements to insert: ");
        int n = sc.nextInt();

        System.out.println("Enter elements:");
        for (int i = 0; i < n; i++) {
            cb.insert(sc.nextInt());
        }

        cb.display();
        sc.close();
    }
}
