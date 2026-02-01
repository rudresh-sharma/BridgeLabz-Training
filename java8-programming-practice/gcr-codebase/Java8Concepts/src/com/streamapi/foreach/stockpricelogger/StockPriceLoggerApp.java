package com.streamapi.foreach.stockpricelogger;

import java.util.ArrayList;
import java.util.List;

public class StockPriceLoggerApp {
    public static void main(String[] args) {
        List<Stock> liveFeed = new ArrayList<>();

        // sample live stock price updates
        liveFeed.add(new Stock("RELIANCE", 2950.45, "09:15 AM"));
        liveFeed.add(new Stock("TCS", 4250.80, "09:16 AM"));
        liveFeed.add(new Stock("HDFCBANK", 1680.25, "09:17 AM"));
        liveFeed.add(new Stock("INFY", 1855.10, "09:18 AM"));
        liveFeed.add(new Stock("ICICIBANK", 1255.60, "09:19 AM"));
        liveFeed.add(new Stock("BHARTIARTL", 1480.90, "09:20 AM"));

        System.out.println("Live Stock Price Logger");
        System.out.println("------------------------");

        liveFeed.stream()
                .forEach(System.out::println);

        System.out.println("------------------------");
        System.out.println("Total updates shown: " + liveFeed.size());
    }
}