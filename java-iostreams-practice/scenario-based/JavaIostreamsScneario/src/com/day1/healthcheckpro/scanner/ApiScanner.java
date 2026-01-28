package com.day1.healthcheckpro.scanner;

import com.day1.healthcheckpro.*;
import com.day1.healthcheckpro.annotations.ApiInfo;
import com.day1.healthcheckpro.annotations.PublicAPI;
import com.day1.healthcheckpro.annotations.RequiresAuth;

import java.lang.reflect.Method;

public class ApiScanner {

    public static void scanController(Class<?> controllerClass) {

        System.out.println("\n🔍 Scanning Controller: " + controllerClass.getSimpleName());
        System.out.println("--------------------------------------------------");

        Method[] methods = controllerClass.getDeclaredMethods();

        for (Method method : methods) {

            boolean isPublicApi = method.isAnnotationPresent(PublicAPI.class);
            boolean requiresAuth = method.isAnnotationPresent(RequiresAuth.class);
            ApiInfo apiInfo = method.getAnnotation(ApiInfo.class);

            // Validation check
            if (!isPublicApi && !requiresAuth) {
                System.out.println("❌ WARNING: Method '" + method.getName()
                        + "' is missing security annotation");
            }

            if (apiInfo == null) {
                System.out.println("❌ WARNING: Method '" + method.getName()
                        + "' is missing @ApiInfo");
                continue;
            }

            // Auto documentation
            System.out.println("✅ API Method: " + method.getName());
            System.out.println("   Endpoint    : " + apiInfo.endpoint());
            System.out.println("   Description : " + apiInfo.description());
            System.out.println("   Access      : "
                    + (isPublicApi ? "Public" : "Authenticated"));
            System.out.println();
        }
    }
}
