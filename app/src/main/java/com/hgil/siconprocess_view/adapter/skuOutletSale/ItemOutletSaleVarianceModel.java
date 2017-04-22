package com.hgil.siconprocess_view.adapter.skuOutletSale;

/**
 * Created by mohan.giri on 22-04-2017.
 */

public class ItemOutletSaleVarianceModel {

    private String item_name;
    private int total_customers;
    private int item_access_count;
    private int item_sequence;

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public int getTotal_customers() {
        return total_customers;
    }

    public void setTotal_customers(int total_customers) {
        this.total_customers = total_customers;
    }

    public int getItem_access_count() {
        return item_access_count;
    }

    public void setItem_access_count(int item_access_count) {
        this.item_access_count = item_access_count;
    }

    public int getItem_sequence() {
        return item_sequence;
    }

    public void setItem_sequence(int item_sequence) {
        this.item_sequence = item_sequence;
    }
}
