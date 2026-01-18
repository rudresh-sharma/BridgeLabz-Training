package com.datastructure.graphs.network;

public class NetworkApp {

    public static void main(String[] args) {

        NetworkService service = new NetworkService();
        service.buildNetwork();
        service.process();
    }
}
