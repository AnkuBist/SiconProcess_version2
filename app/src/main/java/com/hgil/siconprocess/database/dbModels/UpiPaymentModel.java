package com.hgil.siconprocess.database.dbModels;

/**
 * Created by mohan.giri on 28-03-2017.
 */

public class UpiPaymentModel {

    private String paymentReferenceId;
    private double paidAmount;

    public double getPaidAmount() {
        return paidAmount;
    }

    public void setPaidAmount(double paidAmount) {
        this.paidAmount = paidAmount;
    }

    public String getPaymentReferenceId() {
        return paymentReferenceId;
    }

    public void setPaymentReferenceId(String paymentReferenceId) {
        this.paymentReferenceId = paymentReferenceId;
    }
}
