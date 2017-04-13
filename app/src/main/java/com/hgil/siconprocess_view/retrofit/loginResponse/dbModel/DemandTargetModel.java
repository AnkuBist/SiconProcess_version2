package com.hgil.siconprocess_view.retrofit.loginResponse.dbModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by mohan.giri on 13-04-2017.
 */

public class DemandTargetModel {

    @SerializedName("route_id")
    @Expose
    private String routeId;
    @SerializedName("item_id")
    @Expose
    private String itemId;
    @SerializedName("target_qty")
    @Expose
    private Integer targetQty;

    public String getRouteId() {
        return routeId;
    }

    public void setRouteId(String routeId) {
        this.routeId = routeId;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public Integer getTargetQty() {
        return targetQty;
    }

    public void setTargetQty(Integer targetQty) {
        this.targetQty = targetQty;
    }
}


