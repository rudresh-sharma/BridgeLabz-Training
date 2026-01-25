package com.collections.reflections.accessandmodifystaticfields;
import java.lang.reflect.Field;

public class StaticFieldReflectionDemo {
    public static void main(String[] args) {
        try {
            Class<?> clazz = Configuration.class;

            Field field = clazz.getDeclaredField("API_KEY");
            field.setAccessible(true);

            // Get old value
            System.out.println("Old API_KEY: " + field.get(null));

            // Modify static field (object = null for static)
            field.set(null, "NEW_SECRET_KEY");

            // Get new value
            System.out.println("New API_KEY: " + field.get(null));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
