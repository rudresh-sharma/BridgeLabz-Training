/*



3. ZipZipMart – Daily Sales Summary Report (Merge Sort)
Story: ZipZipMart compiles thousands of daily sales records from all branches. To generate
reports, the system sorts transactions by date and amount using Merge Sort, which ensures
stability and efficiency with large datasets.
Key Concepts:
● Divide and conquer
● Large-scale sorting
● Preserves order of equal items (stable)

*/package com.dayfour.zipzipmart;
public class ZipZipMart {

    public static void main(String[] args) {

        Sale[] sales = {
                new Sale("TXN101", "2026-01-14", 450),
                new Sale("TXN102", "2026-01-13", 1200),
                new Sale("TXN103", "2026-01-14", 200),
                new Sale("TXN104", "2026-01-13", 1200),
                new Sale("TXN105", "2026-01-15", 500)
        };

        System.out.println("📦 Before Sorting:");
        for (Sale s : sales) {
            System.out.println(s);
        }

        SalesMergeSort.mergeSort(sales, 0, sales.length - 1);

        System.out.println("\n📊 After Sorting (By Date → Amount):");
        for (Sale s : sales) {
            System.out.println(s);
        }
    }
}
