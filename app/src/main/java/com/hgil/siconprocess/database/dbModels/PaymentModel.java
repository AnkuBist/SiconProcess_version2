package com.hgil.siconprocess.database.dbModels;

/**
 * Created by mohan.giri on 08-02-2017.
 */

public class PaymentModel {

    private String customerId;
    private String customerName;
    private double saleAmount;
    // paid amount
    private double cashPaid;
    private ChequeDetailsModel chequeDetail;
    private double totalPaidAmount;

    // crate data
    private CrateDetailModel crateDetail;

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public double getSaleAmount() {
        return saleAmount;
    }

    public void setSaleAmount(double saleAmount) {
        this.saleAmount = saleAmount;
    }

    public double getCashPaid() {
        return cashPaid;
    }

    public void setCashPaid(double cashPaid) {
        this.cashPaid = cashPaid;
    }

    public ChequeDetailsModel getChequeDetail() {
        return chequeDetail;
    }

    public void setChequeDetail(ChequeDetailsModel chequeDetail) {
        this.chequeDetail = chequeDetail;
    }

    public double getTotalPaidAmount() {
        return totalPaidAmount;
    }

    public void setTotalPaidAmount(double totalPaidAmount) {
        this.totalPaidAmount = totalPaidAmount;
    }

    public CrateDetailModel getCrateDetail() {
        return crateDetail;
    }

    public void setCrateDetail(CrateDetailModel crateDetail) {
        this.crateDetail = crateDetail;
    }
}
