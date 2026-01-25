package com.collections.annotations.customannotation;

import java.lang.reflect.Method;

public class TaskInfoDemo {

    public static void main(String[] args) throws Exception {

        Class<TaskManager> clazz = TaskManager.class;

        // Get all methods of TaskManager
        Method[] methods = clazz.getDeclaredMethods();

        for (Method method : methods) {

            // Check if method has TaskInfo annotation
            if (method.isAnnotationPresent(TaskInfo.class)) {

                TaskInfo info = method.getAnnotation(TaskInfo.class);

                System.out.println("Method Name: " + method.getName());
                System.out.println("Priority: " + info.priority());
                System.out.println("Assigned To: " + info.assignedTo());
            }
        }
    }
}
