package com.collections.reflections.generateaJSONrepresentation;
import java.lang.reflect.Field;

public class JsonGeneratorDemo {
    public static void main(String[] args) throws Exception {

        JsonPerson p = new JsonPerson("Rudresh", 22);

        Class<?> clazz = p.getClass();
        Field[] fields = clazz.getDeclaredFields();

        StringBuilder json = new StringBuilder("{");

        for (int i = 0; i < fields.length; i++) {
            fields[i].setAccessible(true);

            json.append("\"").append(fields[i].getName()).append("\": ");
            json.append("\"").append(fields[i].get(p)).append("\"");

            if (i < fields.length - 1) json.append(", ");
        }

        json.append("}");

        System.out.println(json.toString());
    }
}
