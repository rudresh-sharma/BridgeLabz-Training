package com.datastructure.linkedlist.circularlinkedlist.taskscheduler;

public class Task {
    int taskId;
    String taskName;
    String dueDate;
    int priority;

    public Task(int taskId, String taskName, int priority, String dueDate) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.priority = priority;
        this.dueDate = dueDate;
    }

    public void display() {
        System.out.println("ID: " + taskId + ", Name: " + taskName +
                ", Priority: " + priority + ", Due: " + dueDate);
    }
}
