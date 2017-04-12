package com.hgil.siconprocess_view.adapter.vanStock;

/**
 * Created by mohan.giri on 06-02-2017.
 */

public class VanStockModel {

    private String item_id;
    private String item_name;
    private int loadQty;
    private int gross_sale;
    private int sample;
    private int market_rejection;
    private int fresh_rejection;
    private int left_over;

    public int getLeft_over() {
        return left_over;
    }

    public void setLeft_over(int left_over) {
        this.left_over = left_over;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public int getLoadQty() {
        return loadQty;
    }

    public void setLoadQty(int loadQty) {
        this.loadQty = loadQty;
    }

    public int getGross_sale() {
        return gross_sale;
    }

    public void setGross_sale(int gross_sale) {
        this.gross_sale = gross_sale;
    }

    public int getSample() {
        return sample;
    }

    public void setSample(int sample) {
        this.sample = sample;
    }

    public int getMarket_rejection() {
        return market_rejection;
    }

    public void setMarket_rejection(int market_rejection) {
        this.market_rejection = market_rejection;
    }

    public int getFresh_rejection() {
        return fresh_rejection;
    }

    public void setFresh_rejection(int fresh_rejection) {
        this.fresh_rejection = fresh_rejection;
    }

    public String getItem_id() {
        return item_id;
    }

    public void setItem_id(String item_id) {
        this.item_id = item_id;
    }

}
