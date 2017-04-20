package com.hgil.siconprocess_view.retrofit.loginResponse.dbModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by mohan.giri on 20-04-2017.
 */

public class TodaySaleModel {

    @SerializedName("route_id")
    @Expose
    private String routeId;
    @SerializedName("outlet_code")
    @Expose
    private String outletCode;
    @SerializedName("item_code")
    @Expose
    private String itemCode;
    @SerializedName("loading")
    @Expose
    private Integer loading;
    @SerializedName("otherRej")
    @Expose
    private Integer otherRej;
    @SerializedName("freshRej")
    @Expose
    private Integer freshRej;
    @SerializedName("sampleQty")
    @Expose
    private Integer sampleQty;

    public String getRouteId() {
        return routeId;
    }

    public void setRouteId(String routeId) {
        this.routeId = routeId;
    }

    public String getOutletCode() {
        return outletCode;
    }

    public void setOutletCode(String outletCode) {
        this.outletCode = outletCode;
    }

    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public Integer getLoading() {
        return loading;
    }

    public void setLoading(Integer loading) {
        this.loading = loading;
    }

    public Integer getOtherRej() {
        return otherRej;
    }

    public void setOtherRej(Integer otherRej) {
        this.otherRej = otherRej;
    }

    public Integer getFreshRej() {
        return freshRej;
    }

    public void setFreshRej(Integer freshRej) {
        this.freshRej = freshRej;
    }

    public Integer getSampleQty() {
        return sampleQty;
    }

    public void setSampleQty(Integer sampleQty) {
        this.sampleQty = sampleQty;
    }

}