package com.collections.reflections.dynamicallycreateobjects;
public class DynamicObjectCreationDemo {
    public static void main(String[] args) {
        try {
            Class<?> clazz = Class.forName("Student");

            Object obj = clazz.getDeclaredConstructor().newInstance();

            System.out.println("Object type: " + obj.getClass().getName());

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
