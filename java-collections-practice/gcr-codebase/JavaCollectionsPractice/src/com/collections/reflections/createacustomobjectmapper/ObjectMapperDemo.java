package com.collections.reflections.createacustomobjectmapper;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

public class ObjectMapperDemo {

    public static <T> T toObject(Class<T> clazz, Map<String, Object> properties) throws Exception {
        T obj = clazz.getDeclaredConstructor().newInstance();

        for (Map.Entry<String, Object> entry : properties.entrySet()) {
            Field field = clazz.getDeclaredField(entry.getKey());
            field.setAccessible(true);
            field.set(obj, entry.getValue());
        }

        return obj;
    }

    public static void main(String[] args) throws Exception {
        Map<String, Object> map = new HashMap<>();
        map.put("name", "Rudresh");
        map.put("age", 22);

        Person p = toObject(Person.class, map);

        System.out.println(p.name + " - " + p.age);
    }
}
