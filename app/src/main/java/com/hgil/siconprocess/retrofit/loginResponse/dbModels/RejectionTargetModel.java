package com.hgil.siconprocess.retrofit.loginResponse.dbModels;

/**
 * Created by mohan.giri on 24-01-2017.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RejectionTargetModel {

    @SerializedName("Rec_id")
    @Expose
    private Long recId;
    @SerializedName("Target_From_Date")
    @Expose
    private String targetFromDate;
    @SerializedName("Depot_ID")
    @Expose
    private String depotID;
    @SerializedName("DDate")
    @Expose
    private String dDate;
    @SerializedName("Item_id")
    @Expose
    private String itemId;
    @SerializedName("Target_Qty")
    @Expose
    private int targetQty;
    @SerializedName("Target_Rej")
    @Expose
    private float targetRej;
    @SerializedName("Target_Leftover")
    @Expose
    private float targetLeftover;
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
    @SerializedName("Item_Active")
    @Expose
    private int itemActive;

    public Long getRecId() {
        return recId;
    }

    public void setRecId(Long recId) {
        this.recId = recId;
    }

    public String getTargetFromDate() {
        return targetFromDate;
    }

    public void setTargetFromDate(String targetFromDate) {
        this.targetFromDate = targetFromDate;
    }

    public String getDepotID() {
        return depotID;
    }

    public void setDepotID(String depotID) {
        this.depotID = depotID;
    }

    public String getDDate() {
        return dDate;
    }

    public void setDDate(String dDate) {
        this.dDate = dDate;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public int getTargetQty() {
        return targetQty;
    }

    public void setTargetQty(int targetQty) {
        this.targetQty = targetQty;
    }

    public float getTargetRej() {
        return targetRej;
    }

    public void setTargetRej(float targetRej) {
        this.targetRej = targetRej;
    }

    public float getTargetLeftover() {
        return targetLeftover;
    }

    public void setTargetLeftover(float targetLeftover) {
        this.targetLeftover = targetLeftover;
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

    public int getItemActive() {
        return itemActive;
    }

    public void setItemActive(int itemActive) {
        this.itemActive = itemActive;
    }

}