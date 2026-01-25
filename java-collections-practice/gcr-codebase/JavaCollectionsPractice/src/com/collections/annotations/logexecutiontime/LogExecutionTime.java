package com.collections.annotations.logexecutiontime;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;

@Retention(RetentionPolicy.RUNTIME)      // Needed at runtime
@Target(ElementType.METHOD)               // Can be applied to methods only
public @interface LogExecutionTime {
}
