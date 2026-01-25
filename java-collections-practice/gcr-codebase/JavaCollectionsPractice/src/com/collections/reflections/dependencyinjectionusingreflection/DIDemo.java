package com.collections.reflections.dependencyinjectionusingreflection;
import java.lang.reflect.Field;

public class DIDemo {
    public static void main(String[] args) throws Exception {

        Client client = new Client();

        for (Field field : Client.class.getDeclaredFields()) {
            if (field.isAnnotationPresent(Inject.class)) {
                field.setAccessible(true);
                Object dependency = field.getType().getDeclaredConstructor().newInstance();
                field.set(client, dependency);
            }
        }

        client.doWork();
    }
}
