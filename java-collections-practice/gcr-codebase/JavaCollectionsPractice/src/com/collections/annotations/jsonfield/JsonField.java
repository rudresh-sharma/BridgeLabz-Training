package com.collections.annotations.jsonfield;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;

@Retention(RetentionPolicy.RUNTIME)     // Needed at runtime
@Target(ElementType.FIELD)               // Can be applied only to fields
public @interface JsonField {

    String name();  // Custom JSON key
}
