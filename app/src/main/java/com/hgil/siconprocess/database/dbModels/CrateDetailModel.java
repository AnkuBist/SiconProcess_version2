package com.hgil.siconprocess.database.dbModels;

/**
 * Created by mohan.giri on 08-02-2017.
 */

public class CrateDetailModel {

    private String customer_id;
    private int openingCrates;
    private int issuedCrates;
    private int receivedCrates;
    private int balanceCrates;

    public int getBalanceCrates() {
        return balanceCrates;
    }

    public void setBalanceCrates(int balanceCrates) {
        this.balanceCrates = balanceCrates;
    }

    public int getOpeningCrates() {
        return openingCrates;
    }

    public void setOpeningCrates(int openingCrates) {
        this.openingCrates = openingCrates;
    }

    public int getIssuedCrates() {
        return issuedCrates;
    }

    public void setIssuedCrates(int issuedCrates) {
        this.issuedCrates = issuedCrates;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public int getReceivedCrates() {
        return receivedCrates;
    }

    public void setReceivedCrates(int receivedCrates) {
        this.receivedCrates = receivedCrates;
    }
}
