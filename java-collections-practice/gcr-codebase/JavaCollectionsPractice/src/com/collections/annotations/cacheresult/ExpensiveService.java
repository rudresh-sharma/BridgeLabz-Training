package com.collections.annotations.cacheresult;

public class ExpensiveService {

    @CacheResult
    public long fibonacci(int n) {
        // Simulate expensive computation
        if (n <= 1) return n;
        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    public void normalMethod() {
        System.out.println("This is a normal method.");
    }
}
