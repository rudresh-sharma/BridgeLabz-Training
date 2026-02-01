package com.streamapi.insuranceclaimanalysis;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class InsuranceClaimAnalysisApp {
	public static void main(String[] args) {
		List<InsuranceClaim> claims = new ArrayList<>();

        claims.add(new InsuranceClaim("C101", "Health", 50000));
        claims.add(new InsuranceClaim("C102", "Vehicle", 20000));
        claims.add(new InsuranceClaim("C103", "Life", 150000));
        claims.add(new InsuranceClaim("C104", "Health", 30000));
        claims.add(new InsuranceClaim("C105", "Vehicle", 40000));
        claims.add(new InsuranceClaim("C106", "Travel", 25000));
        claims.add(new InsuranceClaim("C107", "Life", 100000));
        claims.add(new InsuranceClaim("C108", "Health", 45000));
        claims.add(new InsuranceClaim("C109", "Travel", 30000));
        claims.add(new InsuranceClaim("C110", "Vehicle", 35000));

        
        
        Map<String, Double> averageClaims = claims.stream()
                .collect(Collectors.groupingBy(
                        InsuranceClaim::getClaimType,
                        Collectors.averagingDouble(InsuranceClaim::getClaimAmount)
                ));

        // Print result
        averageClaims.forEach((type, avg) ->
                System.out.println(type + " → Average Claim: " + avg));
        
        
        
        
        
        
	}
}
