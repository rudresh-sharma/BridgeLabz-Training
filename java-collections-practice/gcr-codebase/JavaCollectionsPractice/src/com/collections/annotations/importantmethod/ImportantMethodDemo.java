package com.collections.annotations.importantmethod;

import java.lang.reflect.Method;

public class ImportantMethodDemo {

    public static void main(String[] args) {

        Class<ServiceClass> clazz = ServiceClass.class;

        Method[] methods = clazz.getDeclaredMethods();

        System.out.println("Important Methods Found:\n");

        for (Method method : methods) {

            // Check if method is annotated with @ImportantMethod
            if (method.isAnnotationPresent(ImportantMethod.class)) {

                ImportantMethod info = method.getAnnotation(ImportantMethod.class);

                System.out.println("Method Name: " + method.getName());
                System.out.println("Importance Level: " + info.level());
                System.out.println("---------------------------");
            }
        }
    }
}
