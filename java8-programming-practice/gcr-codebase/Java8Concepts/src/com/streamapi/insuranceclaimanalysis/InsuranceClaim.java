package com.streamapi.insuranceclaimanalysis;

public class InsuranceClaim {

    private String claimId;
    private String claimType;     
    private double claimAmount;

    public InsuranceClaim(String claimId, String claimType, double claimAmount) {
        this.claimId = claimId;
        this.claimType = claimType;
        this.claimAmount = claimAmount;
    }

    public String getClaimId() {
        return claimId;
    }

    public String getClaimType() {
    	
        return claimType;
    }

    public double getClaimAmount() {
        return claimAmount;
    }

    @Override
    public String toString() {
        return "InsuranceClaim{" +
                "claimId='" + claimId + '\'' +
                ", claimType='" + claimType + '\'' +
                ", claimAmount=" + claimAmount +
                '}';
    }
}
