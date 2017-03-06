/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hgil.siconprocess.database.dbModels_unused;

/**
 * @author mohan.giri
 */
public class VPriceGroupMasterModel {

    private int ACCOUNTCODE;
    private String ITEMRELATION;
    private String ACCOUNTRELATION;
    private int QUANTITYAMOUNT;
    private String FROMDATE;
    private String TODATE;
    private float AMOUNT;
    private String CURRENCY;
    private Float Discount;
    private Float Discount1;
    private String PriceType;       //null
    private String UNITID;
    private float Surcharge;
    private int MODULE;
    private String Sub_Company_id;      //null

    // get product details
    private VItemMasterModel itemObj;

    public VItemMasterModel getItemObj() {
        return itemObj;
    }

    public void setItemObj(VItemMasterModel itemObj) {
        this.itemObj = itemObj;
    }

    public int getACCOUNTCODE() {
        return ACCOUNTCODE;
    }

    public void setACCOUNTCODE(int ACCOUNTCODE) {
        this.ACCOUNTCODE = ACCOUNTCODE;
    }

    public String getITEMRELATION() {
        return ITEMRELATION;
    }

    public void setITEMRELATION(String ITEMRELATION) {
        this.ITEMRELATION = ITEMRELATION;
    }

    public String getACCOUNTRELATION() {
        return ACCOUNTRELATION;
    }

    public void setACCOUNTRELATION(String ACCOUNTRELATION) {
        this.ACCOUNTRELATION = ACCOUNTRELATION;
    }

    public int getQUANTITYAMOUNT() {
        return QUANTITYAMOUNT;
    }

    public void setQUANTITYAMOUNT(int QUANTITYAMOUNT) {
        this.QUANTITYAMOUNT = QUANTITYAMOUNT;
    }

    public String getFROMDATE() {
        return FROMDATE;
    }

    public void setFROMDATE(String FROMDATE) {
        this.FROMDATE = FROMDATE;
    }

    public String getTODATE() {
        return TODATE;
    }

    public void setTODATE(String TODATE) {
        this.TODATE = TODATE;
    }

    public float getAMOUNT() {
        return AMOUNT;
    }

    public void setAMOUNT(float AMOUNT) {
        this.AMOUNT = AMOUNT;
    }

    public String getCURRENCY() {
        return CURRENCY;
    }

    public void setCURRENCY(String CURRENCY) {
        this.CURRENCY = CURRENCY;
    }

    public Float getDiscount() {
        return Discount;
    }

    public void setDiscount(Float Discount) {
        this.Discount = Discount;
    }

    public Float getDiscount1() {
        return Discount1;
    }

    public void setDiscount1(Float Discount1) {
        this.Discount1 = Discount1;
    }

    public String getPriceType() {
        return PriceType;
    }

    public void setPriceType(String PriceType) {
        this.PriceType = PriceType;
    }

    public String getUNITID() {
        return UNITID;
    }

    public void setUNITID(String UNITID) {
        this.UNITID = UNITID;
    }

    public float getSurcharge() {
        return Surcharge;
    }

    public void setSurcharge(float Surcharge) {
        this.Surcharge = Surcharge;
    }

    public int getMODULE() {
        return MODULE;
    }

    public void setMODULE(int MODULE) {
        this.MODULE = MODULE;
    }

    public String getSub_Company_id() {
        return Sub_Company_id;
    }

    public void setSub_Company_id(String Sub_Company_id) {
        this.Sub_Company_id = Sub_Company_id;
    }

}
