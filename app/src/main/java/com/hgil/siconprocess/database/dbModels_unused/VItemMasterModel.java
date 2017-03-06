/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hgil.siconprocess.database.dbModels_unused;

/**
 * @author mohan.giri
 */
public class VItemMasterModel {

    private int ITEMSEQUENCE;
    private int PRODUCTRANKING;     //null
    private String Item_id;     //null
    private String Item_Shrt_Name;      //null
    private String Item_Name;       //null
    private String Item_Description;        //null
    private String DATAAREAID;      //null
    private float NETWEIGHT;        //null
    private String ITEMGROUPID;     //null
    private int FLAG;       //null

    public int getITEMSEQUENCE() {
        return ITEMSEQUENCE;
    }

    public void setITEMSEQUENCE(int ITEMSEQUENCE) {
        this.ITEMSEQUENCE = ITEMSEQUENCE;
    }

    public int getPRODUCTRANKING() {
        return PRODUCTRANKING;
    }

    public void setPRODUCTRANKING(int PRODUCTRANKING) {
        this.PRODUCTRANKING = PRODUCTRANKING;
    }

    public String getItem_id() {
        return Item_id;
    }

    public void setItem_id(String Item_id) {
        this.Item_id = Item_id;
    }

    public String getItem_Shrt_Name() {
        return Item_Shrt_Name;
    }

    public void setItem_Shrt_Name(String Item_Shrt_Name) {
        this.Item_Shrt_Name = Item_Shrt_Name;
    }

    public String getItem_Name() {
        return Item_Name;
    }

    public void setItem_Name(String Item_Name) {
        this.Item_Name = Item_Name;
    }

    public String getItem_Description() {
        return Item_Description;
    }

    public void setItem_Description(String Item_Description) {
        this.Item_Description = Item_Description;
    }

    public String getDATAAREAID() {
        return DATAAREAID;
    }

    public void setDATAAREAID(String DATAAREAID) {
        this.DATAAREAID = DATAAREAID;
    }

    public float getNETWEIGHT() {
        return NETWEIGHT;
    }

    public void setNETWEIGHT(float NETWEIGHT) {
        this.NETWEIGHT = NETWEIGHT;
    }

    public String getITEMGROUPID() {
        return ITEMGROUPID;
    }

    public void setITEMGROUPID(String ITEMGROUPID) {
        this.ITEMGROUPID = ITEMGROUPID;
    }

    public int getFLAG() {
        return FLAG;
    }

    public void setFLAG(int FLAG) {
        this.FLAG = FLAG;
    }

}
