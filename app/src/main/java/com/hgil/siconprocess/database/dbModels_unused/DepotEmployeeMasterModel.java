/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hgil.siconprocess.database.dbModels_unused;

/**
 * @author mohan.giri
 */
public class DepotEmployeeMasterModel {

    private String Paycode;
    private String Name;
    private String Etype;       //null
    private String Designation;
    private String Sub_Company_id;      //null
    private String Depot_id;
    private String NewPaycode;
    private String Flag;

    public String getPaycode() {
        return Paycode;
    }

    public void setPaycode(String Paycode) {
        this.Paycode = Paycode;
    }

    public String getName() {
        return Name;
    }

    public void setName(String Name) {
        this.Name = Name;
    }

    public String getEtype() {
        return Etype;
    }

    public void setEtype(String Etype) {
        this.Etype = Etype;
    }

    public String getDesignation() {
        return Designation;
    }

    public void setDesignation(String Designation) {
        this.Designation = Designation;
    }

    public String getSub_Company_id() {
        return Sub_Company_id;
    }

    public void setSub_Company_id(String Sub_Company_id) {
        this.Sub_Company_id = Sub_Company_id;
    }

    public String getDepot_id() {
        return Depot_id;
    }

    public void setDepot_id(String Depot_id) {
        this.Depot_id = Depot_id;
    }

    public String getNewPaycode() {
        return NewPaycode;
    }

    public void setNewPaycode(String NewPaycode) {
        this.NewPaycode = NewPaycode;
    }

    public String getFlag() {
        return Flag;
    }

    public void setFlag(String Flag) {
        this.Flag = Flag;
    }

}
