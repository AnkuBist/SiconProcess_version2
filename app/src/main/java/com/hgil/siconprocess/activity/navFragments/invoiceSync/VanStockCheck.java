package com.hgil.siconprocess.activity.navFragments.invoiceSync;

/**
 * Created by mohan.giri on 20-02-2017.
 */

public class VanStockCheck {

    private int items_loaded;
    private int fresh_rejections;
    private int market_rejection;
    private int item_delivered;

    public int getItems_loaded() {
        return items_loaded;
    }

    public void setItems_loaded(int items_loaded) {
        this.items_loaded = items_loaded;
    }

    public int getFresh_rejections() {
        return fresh_rejections;
    }

    public void setFresh_rejections(int fresh_rejections) {
        this.fresh_rejections = fresh_rejections;
    }

    public int getMarket_rejection() {
        return market_rejection;
    }

    public void setMarket_rejection(int market_rejection) {
        this.market_rejection = market_rejection;
    }

    public int getItem_delivered() {
        return item_delivered;
    }

    public void setItem_delivered(int item_delivered) {
        this.item_delivered = item_delivered;
    }
}
