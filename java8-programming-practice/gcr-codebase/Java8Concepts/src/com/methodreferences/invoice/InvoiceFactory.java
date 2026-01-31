package com.methodreferences.invoice;

@FunctionalInterface
public interface InvoiceFactory {
    Invoice create(String transactionId);
}
