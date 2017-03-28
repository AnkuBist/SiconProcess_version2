package com.hgil.siconprocess.activity.fragments.dashboard;

/**
 * Created by mohan.giri on 28-03-2017.
 */

public class DaySummaryAmountCollectionModel {

    private double totalCollection;
    private double cashCollected;
    private double UPIAmount;
    private double chequeAmount;

    public double getTotalCollection() {
        return totalCollection;
    }

    public void setTotalCollection(double totalCollection) {
        this.totalCollection = totalCollection;
    }

    public double getCashCollected() {
        return cashCollected;
    }

    public void setCashCollected(double cashCollected) {
        this.cashCollected = cashCollected;
    }

    public double getUPIAmount() {
        return UPIAmount;
    }

    public void setUPIAmount(double UPIAmount) {
        this.UPIAmount = UPIAmount;
    }

    public double getChequeAmount() {
        return chequeAmount;
    }

    public void setChequeAmount(double chequeAmount) {
        this.chequeAmount = chequeAmount;
    }
}
