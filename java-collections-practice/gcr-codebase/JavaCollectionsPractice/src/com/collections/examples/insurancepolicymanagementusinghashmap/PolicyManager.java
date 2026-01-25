
package com.collections.examples.insurancepolicymanagementusinghashmap;

import java.time.LocalDate;
import java.util.*;

public class PolicyManager {

	// Fast lookup by policy number
    private final Map<String, Policy> hashMapStore = new HashMap<>();

    // Maintains insertion order
    private final Map<String, Policy> linkedHashMapStore = new LinkedHashMap<>();

    // Sorted by policy number (natural order of String keys)
    private final Map<String, Policy> treeMapStore = new TreeMap<>();

    // Add new policy to all map implementations
    public void addPolicy(Policy policy) {
        String key = policy.getPolicyNumber();
        hashMapStore.put(key, policy);
        linkedHashMapStore.put(key, policy);
        treeMapStore.put(key, policy);
    }

    public Policy getPolicyByNumber(String policyNumber) {
        return hashMapStore.get(policyNumber);
    }

    public List<Policy> getExpiringSoon(int days) {
        LocalDate today = LocalDate.now();
        LocalDate threshold = today.plusDays(days);

        List<Policy> result = new ArrayList<>();

        for (Policy policy : hashMapStore.values()) {
            LocalDate expiry = policy.getExpiryDate();
            if (!expiry.isBefore(today) && expiry.isBefore(threshold)) {
                result.add(policy);
            }
        }

        return result;
    }

    public List<Policy> getPoliciesByPolicyholder(String policyholderName) {
        List<Policy> result = new ArrayList<>();

        for (Policy policy : hashMapStore.values()) {
            if (policy.getPolicyholderName().equalsIgnoreCase(policyholderName)) {
                result.add(policy);
            }
        }

        return result;
    }

    public void removeExpiredPolicies() {
        LocalDate today = LocalDate.now();

        Iterator<Map.Entry<String, Policy>> iterator = hashMapStore.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Policy> entry = iterator.next();
            if (entry.getValue().getExpiryDate().isBefore(today)) {
                String key = entry.getKey();
                iterator.remove();
                linkedHashMapStore.remove(key);
                treeMapStore.remove(key);
            }
        }
    }

    public void printAllPolicies(String title) {
        System.out.println("\n" + title);
        System.out.println("HashMap (" + hashMapStore.size() + " policies):");
        hashMapStore.values().forEach(System.out::println);

        System.out.println("\nLinkedHashMap (insertion order):");
        linkedHashMapStore.values().forEach(System.out::println);

        System.out.println("\nTreeMap (sorted by policy number):");
        treeMapStore.values().forEach(System.out::println);
    }

    public Collection<Policy> getAllPolicies() {
        return hashMapStore.values();
    }
}
