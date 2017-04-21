package com.hgil.siconprocess_view.retrofit.loginResponse.dbModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by mohan.giri on 21-04-2017.
 */

public class SHRouteVanLoadingModel {

    @SerializedName("route_id")
    @Expose
    private String routeId;
    @SerializedName("stock_date")
    @Expose
    private String stockDate;
    @SerializedName("count")
    @Expose
    private int count;

    public String getRouteId() {
        return routeId;
    }

    public void setRouteId(String routeId) {
        this.routeId = routeId;
    }

    public String getStockDate() {
        return stockDate;
    }

    public void setStockDate(String stockDate) {
        this.stockDate = stockDate;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

}