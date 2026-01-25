package com.collections.annotations.todo;

import java.lang.reflect.Method;

public class TodoDemo {

    public static void main(String[] args) {

        Class<ProjectTasks> clazz = ProjectTasks.class;

        Method[] methods = clazz.getDeclaredMethods();

        System.out.println("Pending Tasks Found:\n");

        for (Method method : methods) {

            // Check if method has @Todo annotation
            if (method.isAnnotationPresent(Todo.class)) {

                Todo todo = method.getAnnotation(Todo.class);

                System.out.println("Method Name : " + method.getName());
                System.out.println("Task        : " + todo.task());
                System.out.println("Assigned To : " + todo.assignedTo());
                System.out.println("Priority    : " + todo.priority());
                System.out.println("-------------------------------");
            }
        }
    }
}
