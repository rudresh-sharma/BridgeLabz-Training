package com.collections.annotations.todo;

public class ProjectTasks {

    @Todo(
        task = "Implement login feature",
        assignedTo = "Rudresh",
        priority = "HIGH"
    )
    public void loginFeature() {
        // pending implementation
    }

    @Todo(
        task = "Add validation to registration form",
        assignedTo = "Ankit"
        // priority not given → default "MEDIUM"
    )
    public void registrationValidation() {
        // pending implementation
    }

    @Todo(
        task = "Optimize database queries",
        assignedTo = "Neha",
        priority = "LOW"
    )
    public void optimizeDatabase() {
        // pending implementation
    }

    public void completedMethod() {
        System.out.println("This task is already completed.");
    }
}
