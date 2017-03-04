package com.hgil.siconprocess.retrofit.loginResponse.dbModels;

/**
 * Created by mohan.giri on 24-01-2017.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class ProductModel implements Serializable {

    @SerializedName("ITEMSEQUENCE")
    @Expose
    private int iTEMSEQUENCE;
    @SerializedName("PRODUCTRANKING")
    @Expose
    private int pRODUCTRANKING;
    @SerializedName("Item_id")
    @Expose
    private String itemId;
    @SerializedName("Item_Shrt_Name")
    @Expose
    private String itemShrtName;
    @SerializedName("Item_Name")
    @Expose
    private String itemName;
    @SerializedName("Item_Description")
    @Expose
    private String itemDescription;
    @SerializedName("DATAAREAID")
    @Expose
    private String dATAAREAID;
    @SerializedName("NETWEIGHT")
    @Expose
    private float nETWEIGHT;
    @SerializedName("ITEMGROUPID")
    @Expose
    private String iTEMGROUPID;
    @SerializedName("FLAG")
    @Expose
    private int fLAG;

    public int getITEMSEQUENCE() {
        return iTEMSEQUENCE;
    }

    public void setITEMSEQUENCE(int iTEMSEQUENCE) {
        this.iTEMSEQUENCE = iTEMSEQUENCE;
    }

    public int getPRODUCTRANKING() {
        return pRODUCTRANKING;
    }

    public void setPRODUCTRANKING(int pRODUCTRANKING) {
        this.pRODUCTRANKING = pRODUCTRANKING;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getItemShrtName() {
        return itemShrtName;
    }

    public void setItemShrtName(String itemShrtName) {
        this.itemShrtName = itemShrtName;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public String getDATAAREAID() {
        return dATAAREAID;
    }

    public void setDATAAREAID(String dATAAREAID) {
        this.dATAAREAID = dATAAREAID;
    }

    public float getNETWEIGHT() {
        return nETWEIGHT;
    }

    public void setNETWEIGHT(float nETWEIGHT) {
        this.nETWEIGHT = nETWEIGHT;
    }

    public String getITEMGROUPID() {
        return iTEMGROUPID;
    }

    public void setITEMGROUPID(String iTEMGROUPID) {
        this.iTEMGROUPID = iTEMGROUPID;
    }

    public int getFLAG() {
        return fLAG;
    }

    public void setFLAG(int fLAG) {
        this.fLAG = fLAG;
    }

}