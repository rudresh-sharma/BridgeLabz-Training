package com.collections.annotations.importantmethod;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;

@Retention(RetentionPolicy.RUNTIME)      // Needed for Reflection
@Target(ElementType.METHOD)               // Can be applied to methods only
public @interface ImportantMethod {

    // Optional parameter with default value
    String level() default "HIGH";
}
