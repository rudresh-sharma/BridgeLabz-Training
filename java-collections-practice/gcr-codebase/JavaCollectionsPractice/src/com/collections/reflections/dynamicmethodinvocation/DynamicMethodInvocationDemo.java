package com.collections.reflections.dynamicmethodinvocation;
import java.lang.reflect.Method;
import java.util.Scanner;

public class DynamicMethodInvocationDemo {
    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(System.in);

            System.out.print("Enter method name (add/subtract/multiply): ");
            String methodName = sc.nextLine();

            MathOperations obj = new MathOperations();
            Class<?> clazz = obj.getClass();

            Method method = clazz.getMethod(methodName, int.class, int.class);

            Object result = method.invoke(obj, 10, 5);
            System.out.println("Result: " + result);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
