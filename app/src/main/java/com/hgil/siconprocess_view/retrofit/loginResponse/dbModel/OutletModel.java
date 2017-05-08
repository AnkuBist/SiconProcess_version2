package com.hgil.siconprocess_view.retrofit.loginResponse.dbModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by mohan.giri on 12-04-2017.
 */

public class OutletModel {
    private String routeName;

    @SerializedName("Route_id")
    @Expose
    private String routeId;
    @SerializedName("Customer_id")
    @Expose
    private String customerId;
    @SerializedName("Customer_Name")
    @Expose
    private String customerName;
    @SerializedName("sale_status")
    @Expose
    private String saleStatus;
    @SerializedName("sms_time")
    @Expose
    private String smsTime;
    @SerializedName("ContactNo")
    @Expose
    private String contactNo;
    @SerializedName("outstanding")
    @Expose
    private double outstanding;
    @SerializedName("inv_amount")
    @Expose
    private double inv_amount;
    @SerializedName("net_amount")
    @Expose
    private double net_amount;
    @SerializedName("rej_amount")
    @Expose
    private double rej_amount;
    @SerializedName("cash_payment")
    @Expose
    private double cash_payment;
    @SerializedName("inv_time")
    @Expose
    private String inv_time;

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

    public String getSaleStatus() {
        return saleStatus;
    }

    public void setSaleStatus(String saleStatus) {
        this.saleStatus = saleStatus;
    }

    public String getSmsTime() {
        return smsTime;
    }

    public void setSmsTime(String smsTime) {
        this.smsTime = smsTime;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public double getOutstanding() {
        return outstanding;
    }

    public void setOutstanding(double outstanding) {
        this.outstanding = outstanding;
    }

    public String getInv_time() {
        return inv_time;
    }

    public void setInv_time(String inv_time) {
        this.inv_time = inv_time;
    }

    public double getCash_payment() {
        return cash_payment;
    }

    public void setCash_payment(double cash_payment) {
        this.cash_payment = cash_payment;
    }

    public double getInv_amount() {
        return inv_amount;
    }

    public void setInv_amount(double inv_amount) {
        this.inv_amount = inv_amount;
    }

    public double getNet_amount() {
        return net_amount;
    }

    public void setNet_amount(double net_amount) {
        this.net_amount = net_amount;
    }

    public double getRej_amount() {
        return rej_amount;
    }

    public void setRej_amount(double rej_amount) {
        this.rej_amount = rej_amount;
    }
}