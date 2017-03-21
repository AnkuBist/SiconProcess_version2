package com.hgil.siconprocess.activity.fragments.invoiceSyncModel;

/**
 * Created by mohan.giri on 20-02-2017.
 */

public class CollectionCashModel {
    private String invoice_no;
    private String customer_id;
    private double opening;
    private double sale;
    private double receive;
    private double balance;
    private String date;

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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getInvoice_no() {
        return invoice_no;
    }

    public void setInvoice_no(String invoice_no) {
        this.invoice_no = invoice_no;
    }
}
