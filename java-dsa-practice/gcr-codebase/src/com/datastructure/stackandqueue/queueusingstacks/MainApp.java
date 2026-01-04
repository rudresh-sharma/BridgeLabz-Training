package com.datastructure.stackandqueue.queueusingstacks;

public class MainApp {
    public static void main(String[] args) {

        QueueUsingStacks q = new QueueUsingStacks();

        q.enqueue(10);
        q.enqueue(20);
        q.enqueue(30);

        q.display();

        System.out.println("Deleted: " + q.dequeue());
        q.display();

        q.enqueue(40);
        q.display();

        System.out.println("Deleted: " + q.dequeue());
        q.display();
    }
}
