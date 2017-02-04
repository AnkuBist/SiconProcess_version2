package com.hgil.siconprocess.adapter.invoiceRejection;

/**
 * Created by mohan.giri on 04-02-2017.
 */

public class CRejectionModel {

    private String item_id;
    private String item_name;
    private String customer_id;
    private String customer_name;
    private int van_stock;
    private int rej_qty;
    private double price;
    private double total;

    public String getItem_id() {
        return item_id;
    }

    public void setItem_id(String item_id) {
        this.item_id = item_id;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public int getVan_stock() {
        return van_stock;
    }

    public void setVan_stock(int van_stock) {
        this.van_stock = van_stock;
    }

    public int getRej_qty() {
        return rej_qty;
    }

    public void setRej_qty(int rej_qty) {
        this.rej_qty = rej_qty;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }
}
