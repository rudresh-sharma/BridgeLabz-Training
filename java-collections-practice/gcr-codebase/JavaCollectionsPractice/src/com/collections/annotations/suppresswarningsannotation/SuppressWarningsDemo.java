package com.collections.annotations.suppresswarningsannotation;

import java.util.ArrayList;

public class SuppressWarningsDemo {

    @SuppressWarnings("unchecked")
    public static void main(String[] args) {

        // Raw type ArrayList (without generics)
        ArrayList list = new ArrayList();

        list.add("Hello");
        list.add(100);      // Mixing types (unsafe)
        list.add(3.14);

        for (Object obj : list) {
            System.out.println(obj);
        }
    }
}
