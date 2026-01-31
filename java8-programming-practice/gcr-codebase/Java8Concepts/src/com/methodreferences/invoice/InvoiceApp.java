package com.methodreferences.invoice;

import java.util.Arrays;
import java.util.List;

public class InvoiceApp {

    public static void main(String[] args) {

        List<String> transactionIds = Arrays.asList(
                "TXN101",
                "TXN102",
                "TXN103"
        );

        // ✅ Using Lambda Expression
        InvoiceFactory lambdaFactory = id -> new Invoice(id);

        System.out.println("---- Using Lambda ----");
        transactionIds.stream()
                .map(lambdaFactory::create)
                .forEach(System.out::println);


        // ✅ Using Constructor Reference
        InvoiceFactory constructorFactory = Invoice::new;

        System.out.println("---- Using Constructor Reference ----");
        transactionIds.stream()
                .map(constructorFactory::create)
                .forEach(System.out::println);
    }
}
