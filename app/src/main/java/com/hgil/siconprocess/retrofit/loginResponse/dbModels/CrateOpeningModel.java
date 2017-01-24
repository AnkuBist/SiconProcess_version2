package com.hgil.siconprocess.retrofit.loginResponse.dbModels;

/**
 * Created by mohan.giri on 24-01-2017.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CrateOpeningModel {

    @SerializedName("Rce_id")
    @Expose
    private Long rceId;
    @SerializedName("Subcompany_id")
    @Expose
    private String subcompanyId;
    @SerializedName("Depot_Id")
    @Expose
    private String depotId;
    @SerializedName("Route_id")
    @Expose
    private String routeId;
    @SerializedName("Customer_id")
    @Expose
    private String customerId;
    @SerializedName("DDate")
    @Expose
    private String dDate;
    @SerializedName("Crate_id")
    @Expose
    private String crateId;
    @SerializedName("Opening")
    @Expose
    private float opening;
    @SerializedName("Issue")
    @Expose
    private float issue;
    @SerializedName("Receive")
    @Expose
    private float receive;
    @SerializedName("Balance")
    @Expose
    private float balance;
    @SerializedName("updateby_paycode")
    @Expose
    private String updatebyPaycode;
    @SerializedName("updateby_Date")
    @Expose
    private String updatebyDate;
    @SerializedName("updated_ip")
    @Expose
    private String updatedIp;

    public Long getRceId() {
        return rceId;
    }

    public void setRceId(Long rceId) {
        this.rceId = rceId;
    }

    public String getSubcompanyId() {
        return subcompanyId;
    }

    public void setSubcompanyId(String subcompanyId) {
        this.subcompanyId = subcompanyId;
    }

    public String getDepotId() {
        return depotId;
    }

    public void setDepotId(String depotId) {
        this.depotId = depotId;
    }

    public String getRouteId() {
        return routeId;
    }

    public void setRouteId(String routeId) {
        this.routeId = routeId;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getDDate() {
        return dDate;
    }

    public void setDDate(String dDate) {
        this.dDate = dDate;
    }

    public String getCrateId() {
        return crateId;
    }

    public void setCrateId(String crateId) {
        this.crateId = crateId;
    }

    public float getOpening() {
        return opening;
    }

    public void setOpening(float opening) {
        this.opening = opening;
    }

    public float getIssue() {
        return issue;
    }

    public void setIssue(float issue) {
        this.issue = issue;
    }

    public float getReceive() {
        return receive;
    }

    public void setReceive(float receive) {
        this.receive = receive;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
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