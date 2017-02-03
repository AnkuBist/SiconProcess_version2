/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hgil.siconprocess.database.dbModels_unused;

/**
 *
 * @author mohan.giri
 */
public class FixedSampleMasterModel {
    
    private int ID;
    private String StartDt;
    private String DDay;
    private String DepotID;;
    private String Route;
    private String Customer_id;   
    private String Item_id;
    private int SQty;
    private String EndDt;      //null
    private String updateby_paycode;
    private String updateby_Date;
    private String updated_ip;

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getStartDt() {
        return StartDt;
    }

    public void setStartDt(String StartDt) {
        this.StartDt = StartDt;
    }

    public String getDDay() {
        return DDay;
    }

    public void setDDay(String DDay) {
        this.DDay = DDay;
    }

    public String getDepotID() {
        return DepotID;
    }

    public void setDepotID(String DepotID) {
        this.DepotID = DepotID;
    }

    public String getRoute() {
        return Route;
    }

    public void setRoute(String Route) {
        this.Route = Route;
    }

    public String getCustomer_id() {
        return Customer_id;
    }

    public void setCustomer_id(String Customer_id) {
        this.Customer_id = Customer_id;
    }

    public String getItem_id() {
        return Item_id;
    }

    public void setItem_id(String Item_id) {
        this.Item_id = Item_id;
    }

    public int getSQty() {
        return SQty;
    }

    public void setSQty(int SQty) {
        this.SQty = SQty;
    }

    public String getEndDt() {
        return EndDt;
    }

    public void setEndDt(String EndDt) {
        this.EndDt = EndDt;
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
