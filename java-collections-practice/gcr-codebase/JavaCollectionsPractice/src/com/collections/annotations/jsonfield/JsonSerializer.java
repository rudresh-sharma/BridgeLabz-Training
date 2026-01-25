package com.collections.annotations.jsonfield;

import java.lang.reflect.Field;

public class JsonSerializer {

    public static String toJson(Object obj) {
        StringBuilder json = new StringBuilder();
        json.append("{");

        Class<?> clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields();

        boolean first = true;

        for (Field field : fields) {
            if (field.isAnnotationPresent(JsonField.class)) {

                if (!first) {
                    json.append(", ");
                }
                first = false;

                field.setAccessible(true);
                JsonField annotation = field.getAnnotation(JsonField.class);
                String key = annotation.name();

                try {
                    Object value = field.get(obj);
                    json.append("\"").append(key).append("\": ");

                    if (value instanceof String) {
                        json.append("\"").append(value).append("\"");
                    } else {
                        json.append(value);
                    }

                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }

        json.append("}");
        return json.toString();
    }

    // Demo main
    public static void main(String[] args) {
        User user = new User("Rudresh", "rudresh@example.com", 25);
        String jsonString = JsonSerializer.toJson(user);
        System.out.println(jsonString);
    }
}
