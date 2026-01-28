package com.day1.healthcheckpro.annotations;
import com.day1.healthcheckpro.*;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface PublicAPI {
}
