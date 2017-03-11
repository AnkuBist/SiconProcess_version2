package com.hgil.siconprocess.activity.fragments.invoiceSyncModel;

/**
 * Created by mohan.giri on 20-02-2017.
 */

public class CashCheck {

    private double cash_collected;
    private double cash_delivered;
    private double cheque_amount;
    private double cheque_amount_delivered;

    public double getCash_collected() {
        return cash_collected;
    }

    public void setCash_collected(double cash_collected) {
        this.cash_collected = cash_collected;
    }

    public double getCash_delivered() {
        return cash_delivered;
    }

    public void setCash_delivered(double cash_delivered) {
        this.cash_delivered = cash_delivered;
    }

    public double getCheque_amount() {
        return cheque_amount;
    }

    public void setCheque_amount(double cheque_amount) {
        this.cheque_amount = cheque_amount;
    }

    public double getCheque_amount_delivered() {
        return cheque_amount_delivered;
    }

    public void setCheque_amount_delivered(double cheque_amount_delivered) {
        this.cheque_amount_delivered = cheque_amount_delivered;
    }
}
