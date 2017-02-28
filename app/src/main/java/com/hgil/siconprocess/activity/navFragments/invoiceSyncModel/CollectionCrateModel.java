package com.hgil.siconprocess.activity.navFragments.invoiceSyncModel;

/**
 * Created by mohan.giri on 20-02-2017.
 */

public class CollectionCrateModel {

    private String customer_id;
    private int opening;
    private int issued;
    private int receive;
    private int balance;

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public int getOpening() {
        return opening;
    }

    public void setOpening(int opening) {
        this.opening = opening;
    }

    public int getIssued() {
        return issued;
    }

    public void setIssued(int issued) {
        this.issued = issued;
    }

    public int getReceive() {
        return receive;
    }

    public void setReceive(int receive) {
        this.receive = receive;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}
