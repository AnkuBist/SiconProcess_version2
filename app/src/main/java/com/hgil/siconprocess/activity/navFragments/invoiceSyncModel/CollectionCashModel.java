package com.hgil.siconprocess.activity.navFragments.invoiceSyncModel;

/**
 * Created by mohan.giri on 20-02-2017.
 */

public class CollectionCashModel {
    private String customer_id;
    private double opening;
    private double sale;
    private double receive;
    private double balance;

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public double getOpening() {
        return opening;
    }

    public void setOpening(double opening) {
        this.opening = opening;
    }

    public double getSale() {
        return sale;
    }

    public void setSale(double sale) {
        this.sale = sale;
    }

    public double getReceive() {
        return receive;
    }

    public void setReceive(double receive) {
        this.receive = receive;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }
}
