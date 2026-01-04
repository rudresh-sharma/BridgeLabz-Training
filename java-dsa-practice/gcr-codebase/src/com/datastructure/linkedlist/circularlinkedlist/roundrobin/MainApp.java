package com.datastructure.linkedlist.circularlinkedlist.roundrobin;

public class MainApp {
    public static void main(String[] args) {

        RoundRobinScheduler rr = new RoundRobinScheduler();

        rr.addProcess(new Process(1, 10, 1));
        rr.addProcess(new Process(2, 5, 2));
        rr.addProcess(new Process(3, 8, 1));
        rr.addProcess(new Process(4, 6, 3));

        int timeQuantum = 3;
        rr.execute(timeQuantum);
    }
}
