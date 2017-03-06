/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hgil.siconprocess.database.dbModels_unused;

/**
 * @author mohan.giri
 */
public class DemandTargetMasterModel {

    private long Rec_id;
    private String DDate;
    private String DDay;
    private int MMonth;
    private String Depot_ID;
    private String PSM_ID;
    private String Route_ID;
    private String Customer_ID;     //null
    private String Item_id;
    private float Target_Qty;
    private String Active;      //null
    private String updateby_paycode;
    private String updateby_Date;
    private String updated_ip;

    public long getRec_id() {
        return Rec_id;
    }

    public void setRec_id(long Rec_id) {
        this.Rec_id = Rec_id;
    }

    public String getDDate() {
        return DDate;
    }

    public void setDDate(String DDate) {
        this.DDate = DDate;
    }

    public String getDDay() {
        return DDay;
    }

    public void setDDay(String DDay) {
        this.DDay = DDay;
    }

    public int getMMonth() {
        return MMonth;
    }

    public void setMMonth(int MMonth) {
        this.MMonth = MMonth;
    }

    public String getDepot_ID() {
        return Depot_ID;
    }

    public void setDepot_ID(String Depot_ID) {
        this.Depot_ID = Depot_ID;
    }

    public String getPSM_ID() {
        return PSM_ID;
    }

    public void setPSM_ID(String PSM_ID) {
        this.PSM_ID = PSM_ID;
    }

    public String getRoute_ID() {
        return Route_ID;
    }

    public void setRoute_ID(String Route_ID) {
        this.Route_ID = Route_ID;
    }

    public String getCustomer_ID() {
        return Customer_ID;
    }

    public void setCustomer_ID(String Customer_ID) {
        this.Customer_ID = Customer_ID;
    }

    public String getItem_id() {
        return Item_id;
    }

    public void setItem_id(String Item_id) {
        this.Item_id = Item_id;
    }

    public float getTarget_Qty() {
        return Target_Qty;
    }

    public void setTarget_Qty(float Target_Qty) {
        this.Target_Qty = Target_Qty;
    }

    public String getActive() {
        return Active;
    }

    public void setActive(String Active) {
        this.Active = Active;
    }

    public String getUpdateby_paycode() {
        return updateby_paycode;
    }

    public void setUpdateby_paycode(String updateby_paycode) {
        this.updateby_paycode = updateby_paycode;
    }

    public String getUpdateby_Date() {
        return updateby_Date;
    }

    public void setUpdateby_Date(String updateby_Date) {
        this.updateby_Date = updateby_Date;
    }

    public String getUpdated_ip() {
        return updated_ip;
    }

    public void setUpdated_ip(String updated_ip) {
        this.updated_ip = updated_ip;
    }

}
