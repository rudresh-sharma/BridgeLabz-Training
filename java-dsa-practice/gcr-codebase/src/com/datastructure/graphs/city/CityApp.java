package com.datastructure.graphs.city;

public class CityApp {

    public static void main(String[] args) {

        CityService service = new CityService();
        service.buildCity();
        service.process();
    }
}
