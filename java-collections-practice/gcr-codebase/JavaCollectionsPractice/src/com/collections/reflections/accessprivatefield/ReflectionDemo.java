package com.collections.reflections.accessprivatefield;

import java.lang.reflect.Field;

public class ReflectionDemo {
    public static void main(String[] args) {
        try {
            // Create object of Person normally
            Person person = new Person();

            // Get Class object
            Class<?> clazz = person.getClass();

            // Get the private field "age"
            Field ageField = clazz.getDeclaredField("age");

            // Make private field accessible
            ageField.setAccessible(true);

            // Get current value of age
            int currentAge = (int) ageField.get(person);
            System.out.println("Original age: " + currentAge);

            // Modify the private field value
            ageField.set(person, 30);

            // Get modified value
            int newAge = (int) ageField.get(person);
            System.out.println("Modified age: " + newAge);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
