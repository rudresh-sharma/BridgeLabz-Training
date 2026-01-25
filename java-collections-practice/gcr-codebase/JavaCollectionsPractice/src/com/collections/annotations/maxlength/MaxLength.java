package com.collections.annotations.maxlength;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;

@Retention(RetentionPolicy.RUNTIME)     // Needed for Reflection
@Target(ElementType.FIELD)              // Can be applied only to fields
public @interface MaxLength {

    int value();    // Maximum allowed length
}
