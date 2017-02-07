package com.hgil.siconprocess.adapter.invoiceRejection;

import java.io.Serializable;

/**
 * Created by mohan.giri on 07-02-2017.
 */

public class FreshRejectionModel implements Serializable {

    private int mShaped;
    private int tornPolly;
    private int fungus;
    private int wetBread;
    private int others;
    private int total;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getmShaped() {
        return mShaped;
    }

    public void setmShaped(int mShaped) {
        this.mShaped = mShaped;
    }

    public int getTornPolly() {
        return tornPolly;
    }

    public void setTornPolly(int tornPolly) {
        this.tornPolly = tornPolly;
    }

    public int getFungus() {
        return fungus;
    }

    public void setFungus(int fungus) {
        this.fungus = fungus;
    }

    public int getWetBread() {
        return wetBread;
    }

    public void setWetBread(int wetBread) {
        this.wetBread = wetBread;
    }

    public int getOthers() {
        return others;
    }

    public void setOthers(int others) {
        this.others = others;
    }
}
