package com.hgil.siconprocess.retrofit.loginResponse.dbModels;

/**
 * Created by mohan.giri on 24-01-2017.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GroupPriceModel {

    @SerializedName("ACCOUNTCODE")
    @Expose
    private int aCCOUNTCODE;
    @SerializedName("ITEMRELATION")
    @Expose
    private String iTEMRELATION;
    @SerializedName("ACCOUNTRELATION")
    @Expose
    private String aCCOUNTRELATION;
    @SerializedName("QUANTITYAMOUNT")
    @Expose
    private int qUANTITYAMOUNT;
    @SerializedName("FROMDATE")
    @Expose
    private String fROMDATE;
    @SerializedName("TODATE")
    @Expose
    private String tODATE;
    @SerializedName("AMOUNT")
    @Expose
    private Double aMOUNT;
    @SerializedName("CURRENCY")
    @Expose
    private String cURRENCY;
    @SerializedName("Discount")
    @Expose
    private int discount;
    @SerializedName("Discount1")
    @Expose
    private int discount1;
    @SerializedName("PriceType")
    @Expose
    private String priceType;
    @SerializedName("UNITID")
    @Expose
    private String uNITID;
    @SerializedName("Surcharge")
    @Expose
    private float surcharge;
    @SerializedName("MODULE")
    @Expose
    private int mODULE;
    @SerializedName("Sub_Company_id")
    @Expose
    private String subCompanyId;
    @SerializedName("itemObj")
    @Expose
    private ProductModel itemObj;

    public int getACCOUNTCODE() {
        return aCCOUNTCODE;
    }

    public void setACCOUNTCODE(int aCCOUNTCODE) {
        this.aCCOUNTCODE = aCCOUNTCODE;
    }

    public String getITEMRELATION() {
        return iTEMRELATION;
    }

    public void setITEMRELATION(String iTEMRELATION) {
        this.iTEMRELATION = iTEMRELATION;
    }

    public String getACCOUNTRELATION() {
        return aCCOUNTRELATION;
    }

    public void setACCOUNTRELATION(String aCCOUNTRELATION) {
        this.aCCOUNTRELATION = aCCOUNTRELATION;
    }

    public int getQUANTITYAMOUNT() {
        return qUANTITYAMOUNT;
    }

    public void setQUANTITYAMOUNT(int qUANTITYAMOUNT) {
        this.qUANTITYAMOUNT = qUANTITYAMOUNT;
    }

    public String getFROMDATE() {
        return fROMDATE;
    }

    public void setFROMDATE(String fROMDATE) {
        this.fROMDATE = fROMDATE;
    }

    public String getTODATE() {
        return tODATE;
    }

    public void setTODATE(String tODATE) {
        this.tODATE = tODATE;
    }

    public Double getAMOUNT() {
        return aMOUNT;
    }

    public void setAMOUNT(Double aMOUNT) {
        this.aMOUNT = aMOUNT;
    }

    public String getCURRENCY() {
        return cURRENCY;
    }

    public void setCURRENCY(String cURRENCY) {
        this.cURRENCY = cURRENCY;
    }

    public int getDiscount() {
        return discount;
    }

    public void setDiscount(int discount) {
        this.discount = discount;
    }

    public int getDiscount1() {
        return discount1;
    }

    public void setDiscount1(int discount1) {
        this.discount1 = discount1;
    }

    public String getPriceType() {
        return priceType;
    }

    public void setPriceType(String priceType) {
        this.priceType = priceType;
    }

    public String getUNITID() {
        return uNITID;
    }

    public void setUNITID(String uNITID) {
        this.uNITID = uNITID;
    }

    public float getSurcharge() {
        return surcharge;
    }

    public void setSurcharge(float surcharge) {
        this.surcharge = surcharge;
    }

    public int getMODULE() {
        return mODULE;
    }

    public void setMODULE(int mODULE) {
        this.mODULE = mODULE;
    }

    public String getSubCompanyId() {
        return subCompanyId;
    }

    public void setSubCompanyId(String subCompanyId) {
        this.subCompanyId = subCompanyId;
    }

    public ProductModel getItemObj() {
        return itemObj;
    }

    public void setItemObj(ProductModel itemObj) {
        this.itemObj = itemObj;
    }

}
