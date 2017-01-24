/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hgil.siconprocess.database.dbModels;

/**
 *
 * @author mohan.giri
 */
public class CreditOpeningMasterModel {
    
    private long Rce_id;
    private String Subcompany_id;       //null
    private String Depot_Id;        //null
    private String Subdepot_id;     //null
    private String Route_id;        //null
    private String Customer_id;     //null
    private String DDate;       //null
    private float Opening;      //null
    private float Sale_Amt;     //null
    private float Receive_Amt;  //null
    private float Discount_Amt;  //null
    private float Balance;  //null
    private String updateby_paycode;  
    private String updateby_Date;  
    private String updated_ip;  
    private float ChequeAmt;  //null

    public long getRce_id() {
        return Rce_id;
    }

    public void setRce_id(long Rce_id) {
        this.Rce_id = Rce_id;
    }

    public String getSubcompany_id() {
        return Subcompany_id;
    }

    public void setSubcompany_id(String Subcompany_id) {
        this.Subcompany_id = Subcompany_id;
    }

    public String getDepot_Id() {
        return Depot_Id;
    }

    public void setDepot_Id(String Depot_Id) {
        this.Depot_Id = Depot_Id;
    }

    public String getSubdepot_id() {
        return Subdepot_id;
    }

    public void setSubdepot_id(String Subdepot_id) {
        this.Subdepot_id = Subdepot_id;
    }

    public String getRoute_id() {
        return Route_id;
    }

    public void setRoute_id(String Route_id) {
        this.Route_id = Route_id;
    }

    public String getCustomer_id() {
        return Customer_id;
    }

    public void setCustomer_id(String Customer_id) {
        this.Customer_id = Customer_id;
    }

    public String getDDate() {
        return DDate;
    }

    public void setDDate(String DDate) {
        this.DDate = DDate;
    }

    public float getOpening() {
        return Opening;
    }

    public void setOpening(float Opening) {
        this.Opening = Opening;
    }

    public float getSale_Amt() {
        return Sale_Amt;
    }

    public void setSale_Amt(float Sale_Amt) {
        this.Sale_Amt = Sale_Amt;
    }

    public float getReceive_Amt() {
        return Receive_Amt;
    }

    public void setReceive_Amt(float Receive_Amt) {
        this.Receive_Amt = Receive_Amt;
    }

    public float getDiscount_Amt() {
        return Discount_Amt;
    }

    public void setDiscount_Amt(float Discount_Amt) {
        this.Discount_Amt = Discount_Amt;
    }

    public float getBalance() {
        return Balance;
    }

    public void setBalance(float Balance) {
        this.Balance = Balance;
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

    public float getChequeAmt() {
        return ChequeAmt;
    }

    public void setChequeAmt(float ChequeAmt) {
        this.ChequeAmt = ChequeAmt;
    }
    
}
