package com.collections.annotations.roleallowed;

import java.lang.reflect.Method;

public class RoleBasedAccessDemo {

    public static void main(String[] args) throws Exception {

        // Simulate current user role
        SecurityContext.setCurrentRole("USER");   // Try "ADMIN" also

        AdminService service = new AdminService();
        Class<?> clazz = service.getClass();

        Method[] methods = clazz.getDeclaredMethods();

        for (Method method : methods) {

            // Check if method is protected by @RoleAllowed
            if (method.isAnnotationPresent(RoleAllowed.class)) {

                RoleAllowed roleAllowed = method.getAnnotation(RoleAllowed.class);
                String requiredRole = roleAllowed.value();

                String currentRole = SecurityContext.getCurrentRole();

                System.out.println("Trying to execute: " + method.getName());
                System.out.println("Required Role: " + requiredRole);
                System.out.println("Current Role : " + currentRole);

                if (requiredRole.equals(currentRole)) {
                    method.invoke(service);
                } else {
                    System.out.println("Access Denied!");
                }

                System.out.println("------------------------------");
            }
        }
    }
}
