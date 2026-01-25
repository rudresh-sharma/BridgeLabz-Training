package com.collections.reflections.dependencyinjectionusingreflection;
public class Client {

    @Inject
    private Service service;

    public void doWork() {
        service.serve();
    }
}
