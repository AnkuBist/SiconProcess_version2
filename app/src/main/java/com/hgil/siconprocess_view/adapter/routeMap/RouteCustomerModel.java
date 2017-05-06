package com.hgil.siconprocess_view.adapter.routeMap;

/**
 * Created by mohan.giri on 07-02-2017.
 */

public class RouteCustomerModel {

    private String routeId;
    private String routeName;
    private String customerId;
    private String customerName;
    private String custStatus;
    private String sms_time;
    private double saleAmount;
    private double cash_received;
    private String sale_time;
    private String time_diff;

    private double rejPrct;
    private long avgSHSale;
    private long avgSaleRejPrct;
    private int van_total_sku;
    private int outlet_purchased_sku;

    public double getSaleAmount() {
        return saleAmount;
    }

    public void setSaleAmount(double saleAmount) {
        this.saleAmount = saleAmount;
    }

    public String getRouteId() {
        return routeId;
    }

    public void setRouteId(String routeId) {
        this.routeId = routeId;
    }

    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getCustStatus() {
        return custStatus;
    }

    public void setCustStatus(String custStatus) {
        this.custStatus = custStatus;
    }

    public String getSms_time() {
        return sms_time;
    }

    public void setSms_time(String sms_time) {
        this.sms_time = sms_time;
    }

    public String getSale_time() {
        return sale_time;
    }

    public void setSale_time(String sale_time) {
        this.sale_time = sale_time;
    }

    public String getTime_diff() {
        return time_diff;
    }

    public void setTime_diff(String time_diff) {
        this.time_diff = time_diff;
    }

    public double getCash_received() {
        return cash_received;
    }

    public void setCash_received(double cash_received) {
        this.cash_received = cash_received;
    }

    public int getVan_total_sku() {
        return van_total_sku;
    }

    public void setVan_total_sku(int van_total_sku) {
        this.van_total_sku = van_total_sku;
    }

    public int getOutlet_purchased_sku() {
        return outlet_purchased_sku;
    }

    public void setOutlet_purchased_sku(int outlet_purchased_sku) {
        this.outlet_purchased_sku = outlet_purchased_sku;
    }

    public double getRejPrct() {
        return rejPrct;
    }

    public void setRejPrct(double rejPrct) {
        this.rejPrct = rejPrct;
    }

    public long getAvgSHSale() {
        return avgSHSale;
    }

    public void setAvgSHSale(long avgSHSale) {
        this.avgSHSale = avgSHSale;
    }

    public long getAvgSaleRejPrct() {
        return avgSaleRejPrct;
    }

    public void setAvgSaleRejPrct(long avgSaleRejPrct) {
        this.avgSaleRejPrct = avgSaleRejPrct;
    }
}
