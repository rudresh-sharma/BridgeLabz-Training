package com.collections.annotations.todo;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;

@Retention(RetentionPolicy.RUNTIME)     // Required for Reflection
@Target(ElementType.METHOD)              // Can be applied only to methods
public @interface Todo {

    String task();                       // Description of the task

    String assignedTo();                 // Developer responsible

    String priority() default "MEDIUM";  // Default value
}
