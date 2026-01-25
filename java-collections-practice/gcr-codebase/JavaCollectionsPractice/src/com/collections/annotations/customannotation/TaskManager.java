package com.collections.annotations.customannotation;

public class TaskManager {

    @TaskInfo(priority = 1, assignedTo = "Rudresh")
    public void completeTask() {
        System.out.println("Task is being completed...");
    }
}
