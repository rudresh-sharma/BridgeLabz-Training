package com.dbmsjdbc.hms.model;

public class Specialty {

    private int id;
    private String name;

    public Specialty(String name) {
        this.name = name;
    }

    public String getName() { return name; }
}
