package com.hgil.siconprocess.activity.navFragments.invoiceSyncModel;

/**
 * Created by mohan.giri on 20-02-2017.
 */

public class VanStockCheck {

    private int items_loaded;
    private int items_sold;
    private int items_leftover;
    private int item_delivered;
    private int fresh_rejections;
    private int fresh_rejections_delivered;
    private int market_rejection;
    private int market_rejection_delivered;

    public int getItems_loaded() {
        return items_loaded;
    }

    public void setItems_loaded(int items_loaded) {
        this.items_loaded = items_loaded;
    }

    public int getItems_sold() {
        return items_sold;
    }

    public void setItems_sold(int items_sold) {
        this.items_sold = items_sold;
    }

    public int getItems_leftover() {
        return items_leftover;
    }

    public void setItems_leftover(int items_leftover) {
        this.items_leftover = items_leftover;
    }

    public int getItem_delivered() {
        return item_delivered;
    }

    public void setItem_delivered(int item_delivered) {
        this.item_delivered = item_delivered;
    }

    public int getFresh_rejections() {
        return fresh_rejections;
    }

    public void setFresh_rejections(int fresh_rejections) {
        this.fresh_rejections = fresh_rejections;
    }

    public int getFresh_rejections_delivered() {
        return fresh_rejections_delivered;
    }

    public void setFresh_rejections_delivered(int fresh_rejections_delivered) {
        this.fresh_rejections_delivered = fresh_rejections_delivered;
    }

    public int getMarket_rejection() {
        return market_rejection;
    }

    public void setMarket_rejection(int market_rejection) {
        this.market_rejection = market_rejection;
    }

    public int getMarket_rejection_delivered() {
        return market_rejection_delivered;
    }

    public void setMarket_rejection_delivered(int market_rejection_delivered) {
        this.market_rejection_delivered = market_rejection_delivered;
    }
}
