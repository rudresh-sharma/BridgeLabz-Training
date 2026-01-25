package com.collections.reflections.invokeprivatemethod;
import java.lang.reflect.Method;

public class InvokePrivateMethodDemo {
    public static void main(String[] args) {
        try {
            Calculator calc = new Calculator();

            Class<?> clazz = calc.getClass();
            Method method = clazz.getDeclaredMethod("multiply", int.class, int.class);

            method.setAccessible(true); // access private method

            Object result = method.invoke(calc, 5, 4);
            System.out.println("Result: " + result);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
