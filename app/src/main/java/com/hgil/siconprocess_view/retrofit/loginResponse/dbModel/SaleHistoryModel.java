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
    @SerializedName("items_sold")
    @Expose
    private Integer itemsSold;
    @SerializedName("gross_sale")
    @Expose
    private Double grossSale;
    @SerializedName("net_Sale")
    @Expose
    private Double netSale;

    private double rejPrct;
    private int route_van_stock;
    private int outlet_sale_items;

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

    public Integer getItemsSold() {
        return itemsSold;
    }

    public void setItemsSold(Integer itemsSold) {
        this.itemsSold = itemsSold;
    }

    public Double getGrossSale() {
        return grossSale;
    }

    public void setGrossSale(Double grossSale) {
        this.grossSale = grossSale;
    }

    public Double getNetSale() {
        return netSale;
    }

    public void setNetSale(Double netSale) {
        this.netSale = netSale;
    }

    public int getOutlet_sale_items() {
        return outlet_sale_items;
    }

    public void setOutlet_sale_items(int outlet_sale_items) {
        this.outlet_sale_items = outlet_sale_items;
    }

    public double getRejPrct() {
        return rejPrct;
    }

    public void setRejPrct(double rejPrct) {
        this.rejPrct = rejPrct;
    }

    public int getRoute_van_stock() {
        return route_van_stock;
    }

    public void setRoute_van_stock(int route_van_stock) {
        this.route_van_stock = route_van_stock;
    }
}