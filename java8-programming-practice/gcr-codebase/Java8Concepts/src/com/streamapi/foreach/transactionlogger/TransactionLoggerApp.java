package com.streamapi.foreach.transactionlogger;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

public class TransactionLoggerApp {
    public static void main(String[] args) {
        // sample transaction IDs (as if coming from payment system)
        List<String> transactionIds = Arrays.asList(
                "TXN-20260130-00123",
                "TXN-20260130-00456",
                "TXN-20260130-00789",
                "TXN-20260130-01012",
                "TXN-20260130-01345"
        );

        System.out.println("Transaction Logging System");
        System.out.println("Each transaction is logged with current timestamp");
        System.out.println("-----------------------------------------------");

        transactionIds.stream()
                      .forEach(id -> {
                          String timestamp = LocalDateTime.now().toString();
                          System.out.println(timestamp + " - Transaction: " + id);
                      });

        System.out.println("-----------------------------------------------");
        System.out.println("Total transactions logged: " + transactionIds.size());
    }
}