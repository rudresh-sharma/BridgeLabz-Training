package com.collections.annotations.roleallowed;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;

@Retention(RetentionPolicy.RUNTIME)               // Needed for Reflection
@Target({ElementType.METHOD, ElementType.TYPE})  // Can be on class or method
public @interface RoleAllowed {

    String value();   // Required role (e.g., "ADMIN")
}
