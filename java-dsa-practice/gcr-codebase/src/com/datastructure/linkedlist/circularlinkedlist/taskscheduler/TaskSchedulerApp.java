package com.datastructure.linkedlist.circularlinkedlist.taskscheduler;

public class TaskSchedulerApp {
    public static void main(String[] args) {

        CircularTaskList scheduler = new CircularTaskList();

        scheduler.addAtEnd(new Task(1, "Design", 1, "10-Feb"));
        scheduler.addAtEnd(new Task(2, "Coding", 2, "12-Feb"));
        scheduler.addAtEnd(new Task(3, "Testing", 3, "14-Feb"));
        scheduler.addAtBeginning(new Task(4, "Planning", 1, "08-Feb"));

        System.out.println("All Tasks:");
        scheduler.displayAll();

        System.out.println("\nCurrent Tasks:");
        scheduler.nextTask();
        scheduler.nextTask();
        scheduler.nextTask();

        System.out.println("\nSearch Priority 1:");
        scheduler.searchByPriority(1);

        System.out.println("\nRemove Task 2:");
        scheduler.remove(2);
        scheduler.displayAll();
    }
}
