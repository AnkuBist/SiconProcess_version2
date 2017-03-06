/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hgil.siconprocess.database.dbModels_unused;

/**
 * @author mohan.giri
 */
public class RejectionTargetMasterModel {

    private long Rec_id;
    private String Target_From_Date;        //null
    private String Target_To_Date;        //null
    private String Depot_ID;        //null
    private String Route_ID;        //null
    private String Customer_ID;        //null
    private String PSM_ID;        //null
    private String Target_Type;        //null
    private String DDate;        //null
    private String Item_id;        //null
    private int Target_Qty;        //null
    private float Target_Rej;        //null
    private float Target_Leftover;        //null
    private String Rej_Formula;        //null
    private String Leftover_Formula;        //null
    private String Active;        //null
    private String updateby_paycode;
    private String updateby_Date;
    private String updated_ip;
    private int Item_Active;      //null

    public long getRec_id() {
        return Rec_id;
    }

    public void setRec_id(long Rec_id) {
        this.Rec_id = Rec_id;
    }

    public String getTarget_From_Date() {
        return Target_From_Date;
    }

    public void setTarget_From_Date(String Target_From_Date) {
        this.Target_From_Date = Target_From_Date;
    }

    public String getTarget_To_Date() {
        return Target_To_Date;
    }

    public void setTarget_To_Date(String Target_To_Date) {
        this.Target_To_Date = Target_To_Date;
    }

    public String getDepot_ID() {
        return Depot_ID;
    }

    public void setDepot_ID(String Depot_ID) {
        this.Depot_ID = Depot_ID;
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

    public String getPSM_ID() {
        return PSM_ID;
    }

    public void setPSM_ID(String PSM_ID) {
        this.PSM_ID = PSM_ID;
    }

    public String getTarget_Type() {
        return Target_Type;
    }

    public void setTarget_Type(String Target_Type) {
        this.Target_Type = Target_Type;
    }

    public String getDDate() {
        return DDate;
    }

    public void setDDate(String DDate) {
        this.DDate = DDate;
    }

    public String getItem_id() {
        return Item_id;
    }

    public void setItem_id(String Item_id) {
        this.Item_id = Item_id;
    }

    public int getTarget_Qty() {
        return Target_Qty;
    }

    public void setTarget_Qty(int Target_Qty) {
        this.Target_Qty = Target_Qty;
    }

    public float getTarget_Rej() {
        return Target_Rej;
    }

    public void setTarget_Rej(float Target_Rej) {
        this.Target_Rej = Target_Rej;
    }

    public float getTarget_Leftover() {
        return Target_Leftover;
    }

    public void setTarget_Leftover(float Target_Leftover) {
        this.Target_Leftover = Target_Leftover;
    }

    public String getRej_Formula() {
        return Rej_Formula;
    }

    public void setRej_Formula(String Rej_Formula) {
        this.Rej_Formula = Rej_Formula;
    }

    public String getLeftover_Formula() {
        return Leftover_Formula;
    }

    public void setLeftover_Formula(String Leftover_Formula) {
        this.Leftover_Formula = Leftover_Formula;
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

    public int getItem_Active() {
        return Item_Active;
    }

    public void setItem_Active(int Item_Active) {
        this.Item_Active = Item_Active;
    }


}
