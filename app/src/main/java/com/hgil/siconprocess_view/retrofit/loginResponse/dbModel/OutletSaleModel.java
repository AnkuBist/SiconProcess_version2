package com.hgil.siconprocess_view.retrofit.loginResponse.dbModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by mohan.giri on 12-04-2017.
 */

public class OutletSaleModel {

    @SerializedName("location_code")
    @Expose
    private String locationCode;
    @SerializedName("RouteManagementId")
    @Expose
    private String routeManagementId;
    @SerializedName("stock_date")
    @Expose
    private String stockDate;
    @SerializedName("outlet_code")
    @Expose
    private String outletCode;
    @SerializedName("route_id")
    @Expose
    private String routeId;
    @SerializedName("item_id")
    @Expose
    private String itemId;
    @SerializedName("item_name")
    @Expose
    private String itemName;
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
    @SerializedName("netSale")
    @Expose
    private Integer netSale;

    @SerializedName("invoice_id")
    @Expose
    private String invoiceId;
    @SerializedName("invoice_amt")
    @Expose
    private Double invoiceAmt;

    public String getLocationCode() {
        return locationCode;
    }

    public void setLocationCode(String locationCode) {
        this.locationCode = locationCode;
    }

    public String getRouteManagementId() {
        return routeManagementId;
    }

    public void setRouteManagementId(String routeManagementId) {
        this.routeManagementId = routeManagementId;
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
    public String getItemName() {
        return itemName;
    }

    public void setItemName(String timeStamp) {
        this.itemName = timeStamp;
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

    public String getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(String invoiceId) {
        this.invoiceId = invoiceId;
    }

    public Double getInvoiceAmt() {
        return invoiceAmt;
    }

    public void setInvoiceAmt(Double invoiceAmt) {
        this.invoiceAmt = invoiceAmt;
    }

}