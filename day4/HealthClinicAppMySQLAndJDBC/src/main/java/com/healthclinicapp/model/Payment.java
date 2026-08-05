package com.healthclinicapp.model;

import java.sql.Date;

/** POJO for the {@code payment} table. */
public class Payment {

    private int    paymentId;
    private int    billId;
    private double amount;
    private Date   paymentDate;
    private String paymentMethod;   // Cash / Card / Online / Insurance
    private String referenceNumber;

    public Payment() {}

    public Payment(int paymentId, int billId, double amount, Date paymentDate,
                   String paymentMethod, String referenceNumber) {
        this.paymentId       = paymentId;
        this.billId          = billId;
        this.amount          = amount;
        this.paymentDate     = paymentDate;
        this.paymentMethod   = paymentMethod;
        this.referenceNumber = referenceNumber;
    }

    public int    getPaymentId()                    { return paymentId; }
    public void   setPaymentId(int id)              { this.paymentId = id; }
    public int    getBillId()                       { return billId; }
    public void   setBillId(int id)                 { this.billId = id; }
    public double getAmount()                       { return amount; }
    public void   setAmount(double a)               { this.amount = a; }
    public Date   getPaymentDate()                  { return paymentDate; }
    public void   setPaymentDate(Date d)            { this.paymentDate = d; }
    public String getPaymentMethod()                { return paymentMethod; }
    public void   setPaymentMethod(String m)        { this.paymentMethod = m; }
    public String getReferenceNumber()              { return referenceNumber; }
    public void   setReferenceNumber(String r)      { this.referenceNumber = r; }

    @Override
    public String toString() {
        return "Payment{id=" + paymentId + ", billId=" + billId +
               ", amount=" + amount + ", method='" + paymentMethod + "'}";
    }
}
