package com.hgil.siconprocess_view.retrofit.loginResponse.dbModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by mohan.giri on 20-04-2017.
 */

public class TodaySaleModel {

    private String item_name;
    private int item_sequence;

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
    private int loading;
    @SerializedName("otherRej")
    @Expose
    private int otherRej;
    @SerializedName("freshRej")
    @Expose
    private int freshRej;
    @SerializedName("sampleQty")
    @Expose
    private int sampleQty;

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public int getItem_sequence() {
        return item_sequence;
    }

    public void setItem_sequence(int item_sequence) {
        this.item_sequence = item_sequence;
    }

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

    public int getLoading() {
        return loading;
    }

    public void setLoading(int loading) {
        this.loading = loading;
    }

    public int getOtherRej() {
        return otherRej;
    }

    public void setOtherRej(int otherRej) {
        this.otherRej = otherRej;
    }

    public int getFreshRej() {
        return freshRej;
    }

    public void setFreshRej(int freshRej) {
        this.freshRej = freshRej;
    }

    public int getSampleQty() {
        return sampleQty;
    }

    public void setSampleQty(int sampleQty) {
        this.sampleQty = sampleQty;
    }

}