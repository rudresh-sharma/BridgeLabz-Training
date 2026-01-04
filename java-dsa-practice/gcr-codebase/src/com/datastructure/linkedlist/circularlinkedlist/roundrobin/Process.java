package com.datastructure.linkedlist.circularlinkedlist.roundrobin;

public class Process {
    int pid;
    int burstTime;
    int remainingTime;
    int waitingTime;
    int turnaroundTime;
    int priority;

    public Process(int pid, int burstTime, int priority) {
        this.pid = pid;
        this.burstTime = burstTime;
        this.remainingTime = burstTime;
        this.priority = priority;
    }
}
