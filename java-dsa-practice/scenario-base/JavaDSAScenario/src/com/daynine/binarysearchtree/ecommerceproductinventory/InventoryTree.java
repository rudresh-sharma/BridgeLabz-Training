package com.daynine.binarysearchtree.ecommerceproductinventory;

public class InventoryTree {

    //  Insert product into BST
    public static Product insert(Product root, Product p) {
        if (root == null) {
            return new Product(p);
        }

        int cmp = root.getSku().compareToIgnoreCase(p.getSku());

        if (cmp > 0) {
            root.setLeft(insert(root.getLeft(), p));
        }  
        else if (cmp < 0) {
            root.setRight(insert(root.getRight(), p));
        }
        // if equal SKU, do nothing (no duplicates)

        return root;
    }

    //  Lookup product by SKU
    public static Product search(Product root, String sku) {
        if (root == null) return null;

        int cmp = sku.compareToIgnoreCase(root.getSku());

        if (cmp == 0) {
            return root;   // Product found
        } 
        else if (cmp < 0) {
            return search(root.getLeft(), sku);
        } 
        else {
            return search(root.getRight(), sku);
        }
    }

    //  Update price using SKU
    public static boolean updatePrice(Product root, String sku, double newPrice) {
        Product p = search(root, sku);

        if (p != null) {
            p.setPrice(newPrice);
            return true;   // Update successful
        }

        return false;      // Product not found
    }

    // Display all products in sorted SKU order
    public static void displaySorted(Product root) {
        if (root == null) return;

        displaySorted(root.getLeft());

        System.out.printf("%-15s %-15s %-10.2f%n",
                root.getSku(),
                root.getName(),
                root.getPrice());

        displaySorted(root.getRight());
    }
}
