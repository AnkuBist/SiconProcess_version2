package com.hgil.siconprocess.adapter.invoiceRejection;

import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.widget.EditText;

import com.hgil.siconprocess.R;

import java.io.Serializable;

import butterknife.BindView;

/**
 * Created by mohan.giri on 07-02-2017.
 */

public class MarketRejectionModel implements Serializable {
    private int damaged;
    private int expired;
    private int ratEaten;
    private int total;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getRatEaten() {
        return ratEaten;
    }

    public void setRatEaten(int ratEaten) {
        this.ratEaten = ratEaten;
    }

    public int getDamaged() {
        return damaged;
    }

    public void setDamaged(int damaged) {
        this.damaged = damaged;
    }

    public int getExpired() {
        return expired;
    }

    public void setExpired(int expired) {
        this.expired = expired;
    }

}
