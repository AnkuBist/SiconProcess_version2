package com.hgil.siconprocess.retrofit.loginResponse.dbModels;

/**
 * Created by mohan.giri on 24-01-2017.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DemandTargetModel {

    @SerializedName("Rec_id")
    @Expose
    private Long recId;
    @SerializedName("DDate")
    @Expose
    private String dDate;
    @SerializedName("DDay")
    @Expose
    private String dDay;
    @SerializedName("MMonth")
    @Expose
    private int mMonth;
    @SerializedName("Depot_ID")
    @Expose
    private String depotID;
    @SerializedName("PSM_ID")
    @Expose
    private String pSMID;
    @SerializedName("Route_ID")
    @Expose
    private String routeID;
    @SerializedName("Customer_ID")
    @Expose
    private String customerID;
    @SerializedName("Item_id")
    @Expose
    private String itemId;
    @SerializedName("Target_Qty")
    @Expose
    private float targetQty;
    @SerializedName("Active")
    @Expose
    private String active;
    @SerializedName("updateby_paycode")
    @Expose
    private String updatebyPaycode;
    @SerializedName("updateby_Date")
    @Expose
    private String updatebyDate;
    @SerializedName("updated_ip")
    @Expose
    private String updatedIp;

    public Long getRecId() {
        return recId;
    }

    public void setRecId(Long recId) {
        this.recId = recId;
    }

    public String getDDate() {
        return dDate;
    }

    public void setDDate(String dDate) {
        this.dDate = dDate;
    }

    public String getDDay() {
        return dDay;
    }

    public void setDDay(String dDay) {
        this.dDay = dDay;
    }

    public int getMMonth() {
        return mMonth;
    }

    public void setMMonth(int mMonth) {
        this.mMonth = mMonth;
    }

    public String getDepotID() {
        return depotID;
    }

    public void setDepotID(String depotID) {
        this.depotID = depotID;
    }

    public String getPSMID() {
        return pSMID;
    }

    public void setPSMID(String pSMID) {
        this.pSMID = pSMID;
    }

    public String getRouteID() {
        return routeID;
    }

    public void setRouteID(String routeID) {
        this.routeID = routeID;
    }

    public String getCustomerID() {
        return customerID;
    }

    public void setCustomerID(String customerID) {
        this.customerID = customerID;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public float getTargetQty() {
        return targetQty;
    }

    public void setTargetQty(float targetQty) {
        this.targetQty = targetQty;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getUpdatebyPaycode() {
        return updatebyPaycode;
    }

    public void setUpdatebyPaycode(String updatebyPaycode) {
        this.updatebyPaycode = updatebyPaycode;
    }

    public String getUpdatebyDate() {
        return updatebyDate;
    }

    public void setUpdatebyDate(String updatebyDate) {
        this.updatebyDate = updatebyDate;
    }

    public String getUpdatedIp() {
        return updatedIp;
    }

    public void setUpdatedIp(String updatedIp) {
        this.updatedIp = updatedIp;
    }

}