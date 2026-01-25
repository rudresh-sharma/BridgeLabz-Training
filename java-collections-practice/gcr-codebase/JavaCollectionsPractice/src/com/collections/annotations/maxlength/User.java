package com.collections.annotations.maxlength;

import java.lang.reflect.Field;

public class User {

    @MaxLength(10)
    private String username;

    public User(String username) {
        this.username = username;
        validateMaxLength();
    }

    // Validation logic using Reflection
    private void validateMaxLength() {
        try {
            Class<?> clazz = this.getClass();
            Field[] fields = clazz.getDeclaredFields();

            for (Field field : fields) {

                if (field.isAnnotationPresent(MaxLength.class)) {

                    MaxLength maxLength = field.getAnnotation(MaxLength.class);
                    int max = maxLength.value();

                    field.setAccessible(true);
                    Object value = field.get(this);

                    if (value instanceof String) {
                        String str = (String) value;

                        if (str.length() > max) {
                            throw new IllegalArgumentException(
                                "Field '" + field.getName() + 
                                "' exceeds max length of " + max +
                                ". Actual length: " + str.length()
                            );
                        }
                    }
                }
            }

        } catch (IllegalArgumentException e) {
            throw e; // rethrow
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public String getUsername() {
        return username;
    }
}
