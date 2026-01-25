package com.collections.annotations.cacheresult;

public class CacheDemo {

    public static void main(String[] args) throws Exception {

        ExpensiveService service = new ExpensiveService();
        CacheHandler handler = new CacheHandler(service);

        // First call → computed
        System.out.println("Fibonacci(10) = " + handler.invoke("fibonacci", 10));

        // Second call → should return cached result
        System.out.println("Fibonacci(10) = " + handler.invoke("fibonacci", 10));

        // Another call with different input → computed
        System.out.println("Fibonacci(8) = " + handler.invoke("fibonacci", 8));
    }
}
