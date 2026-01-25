package com.collections.annotations.repeatable;

import java.lang.annotation.Repeatable;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;

@Repeatable(BugReports.class)          // Link to container annotation
@Retention(RetentionPolicy.RUNTIME)   // Needed for Reflection
@Target(ElementType.METHOD)            // Can be applied to methods
public @interface BugReport {
    String description();
}
