package com.inheritance;

// Chef IS-A Person and also IS-A Worker
public class Chef extends Person implements Worker {

    private String specialization;

    public Chef(String name, int id, String specialization) {
        super(name, id);
        this.specialization = specialization;
    }

    @Override
    public void performDuties() {
        System.out.println("Chef prepares " + specialization + " dishes.");
    }
}
