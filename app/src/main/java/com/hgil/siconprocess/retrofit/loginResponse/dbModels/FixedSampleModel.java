package com.hgil.siconprocess.retrofit.loginResponse.dbModels;

/**
 * Created by mohan.giri on 24-01-2017.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class FixedSampleModel {

    @SerializedName("ID")
    @Expose
    private int iD;
    @SerializedName("StartDt")
    @Expose
    private String startDt;
    @SerializedName("DDay")
    @Expose
    private String dDay;
    @SerializedName("DepotID")
    @Expose
    private String depotID;
    @SerializedName("Route")
    @Expose
    private String route;
    @SerializedName("Customer_id")
    @Expose
    private String customerId;
    @SerializedName("Item_id")
    @Expose
    private String itemId;
    @SerializedName("SQty")
    @Expose
    private int sQty;
    @SerializedName("updateby_paycode")
    @Expose
    private String updatebyPaycode;
    @SerializedName("updateby_Date")
    @Expose
    private String updatebyDate;
    @SerializedName("updated_ip")
    @Expose
    private String updatedIp;

    public int getID() {
        return iD;
    }

    public void setID(int iD) {
        this.iD = iD;
    }

    public String getStartDt() {
        return startDt;
    }

    public void setStartDt(String startDt) {
        this.startDt = startDt;
    }

    public String getDDay() {
        return dDay;
    }

    public void setDDay(String dDay) {
        this.dDay = dDay;
    }

    public String getDepotID() {
        return depotID;
    }

    public void setDepotID(String depotID) {
        this.depotID = depotID;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public int getSQty() {
        return sQty;
    }

    public void setSQty(int sQty) {
        this.sQty = sQty;
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