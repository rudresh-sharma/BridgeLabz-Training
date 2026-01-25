package com.collections.reflections.methodexecutiontiming;
import java.lang.reflect.Method;

public class MethodTimingDemo {
    public static void main(String[] args) throws Exception {

        TimingOperations obj = new TimingOperations();

        Method method = obj.getClass().getMethod("task");

        long start = System.nanoTime();
        method.invoke(obj);
        long end = System.nanoTime();

        System.out.println("Execution time (ns): " + (end - start));
    }
}
