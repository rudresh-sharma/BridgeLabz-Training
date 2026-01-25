package com.collections.annotations.customannotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;

@Retention(RetentionPolicy.RUNTIME)   // Available at runtime
@Target(ElementType.METHOD)            // Can be applied to methods only
public @interface TaskInfo {

    int priority();        // Annotation field
    String assignedTo();  // Annotation field
}
