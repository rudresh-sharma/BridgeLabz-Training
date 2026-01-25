package com.day2.dealtracker;

import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.*;
import java.util.regex.*;
import java.util.stream.Collectors;

public class DealTrackerManager<T extends Deal> {

    private Map<String, T> dealMap = new HashMap<>();
    private Set<String> dealCodes = new HashSet<>();

    private static final Pattern CODE_PATTERN = Pattern.compile("^DealCode:\\s*(\\w+)");
    private static final Pattern VALID_TILL_PATTERN = Pattern.compile("^ValidTill:\\s*(\\d{2}-\\d{2}-\\d{4})");
    private static final Pattern DISCOUNT_PATTERN = Pattern.compile("^Discount:\\s*(\\d+)%");
    private static final Pattern MIN_PURCHASE_PATTERN = Pattern.compile("^MinimumPurchase:\\s*(\\d+)");

    private DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    // 🔹 Load deals from folder
    public void loadDealsFromFolder(String folderPath) {
        dealMap.clear();
        dealCodes.clear();

        File folder = new File(folderPath);

        if (!folder.exists() || !folder.isDirectory()) {
            System.out.println("❌ Folder does not exist: " + folderPath);
            return;
        }

        File[] files = folder.listFiles((dir, name) -> name.endsWith(".txt"));

        if (files == null || files.length == 0) {
            System.out.println("⚠️ No .txt deal files found in folder.");
            return;
        }

        for (File file : files) {
            try {
                processDealFile(file);
            } catch (Exception e) {
                System.out.println("⚠️ Skipping invalid deal file: " + file.getName());
            }
        }

        System.out.println("\n✅ Loaded " + dealMap.size() + " valid deals.");
    }

    private void processDealFile(File file) throws IOException {
        String code = null;
        LocalDate validTill = null;
        int discount = -1;
        int minPurchase = -1;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                line = line.trim();
                Matcher m;

                m = CODE_PATTERN.matcher(line);
                if (m.matches()) { code = m.group(1); continue; }

                m = VALID_TILL_PATTERN.matcher(line);
                if (m.matches()) {
                    try { validTill = LocalDate.parse(m.group(1), dtf); } 
                    catch (DateTimeParseException e) { validTill = null; }
                    continue;
                }

                m = DISCOUNT_PATTERN.matcher(line);
                if (m.matches()) { discount = Integer.parseInt(m.group(1)); continue; }

                m = MIN_PURCHASE_PATTERN.matcher(line);
                if (m.matches()) { minPurchase = Integer.parseInt(m.group(1)); }
            }
        }

        // Validate
        if (code == null || validTill == null || discount < 0 || minPurchase < 0) {
            throw new IllegalArgumentException("Missing/Invalid fields in " + file.getName());
        }

        // Skip expired deals
        if (validTill.isBefore(LocalDate.now())) {
            System.out.println("⚠️ Skipping expired deal: " + code);
            return;
        }

        // Skip duplicate deal codes
        if (dealCodes.contains(code)) return;

        @SuppressWarnings("unchecked")
        T deal = (T) new Deal(code, validTill, discount, minPurchase);

        dealMap.put(code, deal);
        dealCodes.add(code);

        System.out.println("Loaded deal: " + code);
    }

    // 🔹 Display all deals
    public void displayAllDeals() {
        System.out.println("\n📄 All Valid Deals:");
        dealMap.values().forEach(System.out::println);
    }

    // 🔹 Sort deals by discount descending
    public void displayDealsSortedByDiscount() {
        System.out.println("\n💰 Deals Sorted by Discount:");
        dealMap.values().stream()
                .sorted(Comparator.comparingInt(Deal::getDiscountPercent).reversed())
                .forEach(System.out::println);
    }
}
