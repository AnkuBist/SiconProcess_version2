package com.hgil.siconprocess_view.retrofit.loginResponse.dbModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by mohan.giri on 13-04-2017.
 */

public class SaleHistoryModel {

    @SerializedName("RouteId")
    @Expose
    private String routeId;
    @SerializedName("stock_date")
    @Expose
    private String stockDate;
    @SerializedName("outlet_code")
    @Expose
    private String outletCode;
    @SerializedName("outlet_name")
    @Expose
    private String outletName;
    @SerializedName("Loading")
    @Expose
    private Integer loading;
    @SerializedName("OtherRej")
    @Expose
    private Integer otherRej;
    @SerializedName("FreshRej")
    @Expose
    private Integer freshRej;
    @SerializedName("SampleQty")
    @Expose
    private Integer sampleQty;
    @SerializedName("NetSale")
    @Expose
    private Integer netSale;
    @SerializedName("SALEAMT")
    @Expose
    private Double sALEAMT;

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

    public String getOutletCode() {
        return outletCode;
    }

    public void setOutletCode(String outletCode) {
        this.outletCode = outletCode;
    }

    public String getOutletName() {
        return outletName;
    }

    public void setOutletName(String outletName) {
        this.outletName = outletName;
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

    public Integer getNetSale() {
        return netSale;
    }

    public void setNetSale(Integer netSale) {
        this.netSale = netSale;
    }

    public Double getSALEAMT() {
        return sALEAMT;
    }

    public void setSALEAMT(Double sALEAMT) {
        this.sALEAMT = sALEAMT;
    }

}