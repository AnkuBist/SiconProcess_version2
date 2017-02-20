package com.hgil.siconprocess.activity.navFragments.invoiceSync;

/**
 * Created by mohan.giri on 20-02-2017.
 */

public class SyncInvoiceDetailModel {

    private String invoice_no;
    private String invoice_date;
    private String customer_id;
    private String route_id;
    //private String route_management_id;
    private String cashier_code;
    private String item_id;
    private int loading;
    private int sale;
    private int fresh_rej;
    private int market_rej;
    private int sample;

    public String getInvoice_no() {
        return invoice_no;
    }

    public void setInvoice_no(String invoice_no) {
        this.invoice_no = invoice_no;
    }

    public String getInvoice_date() {
        return invoice_date;
    }

    public void setInvoice_date(String invoice_date) {
        this.invoice_date = invoice_date;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public String getRoute_id() {
        return route_id;
    }

    public void setRoute_id(String route_id) {
        this.route_id = route_id;
    }

   /* public String getRoute_management_id() {
        return route_management_id;
    }

    public void setRoute_management_id(String route_management_id) {
        this.route_management_id = route_management_id;
    }
*/
    public String getCashier_code() {
        return cashier_code;
    }

    public void setCashier_code(String cashier_code) {
        this.cashier_code = cashier_code;
    }

    public String getItem_id() {
        return item_id;
    }

    public void setItem_id(String item_id) {
        this.item_id = item_id;
    }

    public int getLoading() {
        return loading;
    }

    public void setLoading(int loading) {
        this.loading = loading;
    }

    public int getSale() {
        return sale;
    }

    public void setSale(int sale) {
        this.sale = sale;
    }

    public int getFresh_rej() {
        return fresh_rej;
    }

    public void setFresh_rej(int fresh_rej) {
        this.fresh_rej = fresh_rej;
    }

    public int getMarket_rej() {
        return market_rej;
    }

    public void setMarket_rej(int market_rej) {
        this.market_rej = market_rej;
    }

    public int getSample() {
        return sample;
    }

    public void setSample(int sample) {
        this.sample = sample;
    }
}
