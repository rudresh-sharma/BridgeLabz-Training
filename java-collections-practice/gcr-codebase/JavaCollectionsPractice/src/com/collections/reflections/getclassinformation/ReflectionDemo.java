package com.collections.reflections.getclassinformation;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Scanner;

public class ReflectionDemo {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the fully qualified class name (e.g., java.util.ArrayList): ");
        String className = sc.nextLine();

        try {
            // Load the class dynamically
            Class<?> clazz = Class.forName(className);

            // Display class name
            System.out.println("\nClass: " + clazz.getName());

            // Display fields
            Field[] fields = clazz.getDeclaredFields();
            System.out.println("\nFields:");
            if (fields.length == 0) {
                System.out.println("  No fields found.");
            } else {
                for (Field field : fields) {
                    System.out.println("  " + field);
                }
            }

            // Display methods
            Method[] methods = clazz.getDeclaredMethods();
            System.out.println("\nMethods:");
            if (methods.length == 0) {
                System.out.println("  No methods found.");
            } else {
                for (Method method : methods) {
                    System.out.println("  " + method);
                }
            }

            // Display constructors
            Constructor<?>[] constructors = clazz.getDeclaredConstructors();
            System.out.println("\nConstructors:");
            if (constructors.length == 0) {
                System.out.println("  No constructors found.");
            } else {
                for (Constructor<?> constructor : constructors) {
                    System.out.println("  " + constructor);
                }
            }

        } catch (ClassNotFoundException e) {
            System.out.println("Class not found: " + className);
        }

        sc.close();
    }
}
