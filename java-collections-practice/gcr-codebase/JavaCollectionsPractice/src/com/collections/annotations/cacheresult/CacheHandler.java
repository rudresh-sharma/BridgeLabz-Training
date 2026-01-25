package com.collections.annotations.cacheresult;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Arrays;

public class CacheHandler {

    // Cache key: method name + parameters
    private Map<String, Object> cache = new HashMap<>();

    private Object target;

    public CacheHandler(Object target) {
        this.target = target;
    }

    public Object invoke(String methodName, Object... args) throws Exception {

        Class<?> clazz = target.getClass();
        Method method = null;

        // Find method by name and number of arguments
        for (Method m : clazz.getDeclaredMethods()) {
            if (m.getName().equals(methodName) && m.getParameterCount() == args.length) {
                method = m;
                break;
            }
        }

        if (method == null) {
            throw new NoSuchMethodException(methodName);
        }

        // Check if method is annotated with @CacheResult
        if (method.isAnnotationPresent(CacheResult.class)) {
            // Build cache key using method name + arguments
            String key = methodName + Arrays.toString(args);

            if (cache.containsKey(key)) {
                System.out.println("Returning cached result for: " + key);
                return cache.get(key);
            } else {
                Object result = method.invoke(target, args);
                cache.put(key, result);
                System.out.println("Computed and cached result for: " + key);
                return result;
            }
        } else {
            // No caching, just invoke
            return method.invoke(target, args);
        }
    }
}
