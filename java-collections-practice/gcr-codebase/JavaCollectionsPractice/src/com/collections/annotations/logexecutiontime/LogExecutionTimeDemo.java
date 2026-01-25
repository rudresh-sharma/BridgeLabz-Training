package com.collections.annotations.logexecutiontime;

import java.lang.reflect.Method;

public class LogExecutionTimeDemo {

    public static void main(String[] args) throws Exception {

        PerformanceService service = new PerformanceService();
        Class<?> clazz = service.getClass();

        Method[] methods = clazz.getDeclaredMethods();

        for (Method method : methods) {

            // Check if method is annotated with @LogExecutionTime
            if (method.isAnnotationPresent(LogExecutionTime.class)) {

                System.out.println("Executing: " + method.getName());

                long startTime = System.nanoTime();

                // Invoke the method
                method.invoke(service);

                long endTime = System.nanoTime();

                long duration = endTime - startTime;
                
                double durationSeconds = duration / 1_000_000_000.0;


                System.out.println("Execution Time (seconds): " + durationSeconds);
                System.out.println("----------------------------------");
            }
        }
    }
}
