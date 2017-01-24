/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hgil.siconprocess.database.dbModels;

/**
 * @author mohan.giri
 */
public class VCustomerRouteMapping {

    private long Rec_id;
    private String Sub_Company_id;  //null
    private String Depot;   //null
    private String Route_id;    //null
    private String Route_Name;  //null
    private String Sale_Date_Parameter;     //null
    private String PSMID;   //null
    private String Customer_id;     //null
    private String Customer_Name;
    private String PRICEGROUP;
    private String LINEDISC;
    private String C_Type;
    private String COMMISSIONGROUP;
    private String SALESGROUP;
    private String SubDepot_id; //null
    private String CUSTCLASSIFICATIONID;    //null
    private int Flag;   //null
    private int RFlag;      //null
    private String ACCOUNTNUM;
    private int Mandt;

    public long getRec_id() {
        return Rec_id;
    }

    public void setRec_id(long Rec_id) {
        this.Rec_id = Rec_id;
    }

    public int getRFlag() {
        return RFlag;
    }

    public void setRFlag(int RFlag) {
        this.RFlag = RFlag;
    }

    /* public long getRec_id() {
        return Rec_id;
    }

    public void setRec_id(long Rec_id) {
        this.Rec_id = Rec_id;
    }*/
    public String getSub_Company_id() {
        return Sub_Company_id;
    }

    public void setSub_Company_id(String Sub_Company_id) {
        this.Sub_Company_id = Sub_Company_id;
    }

    public String getDepot() {
        return Depot;
    }

    public void setDepot(String Depot) {
        this.Depot = Depot;
    }

    public String getRoute_id() {
        return Route_id;
    }

    public void setRoute_id(String Route_id) {
        this.Route_id = Route_id;
    }

    public String getRoute_Name() {
        return Route_Name;
    }

    public void setRoute_Name(String Route_Name) {
        this.Route_Name = Route_Name;
    }

    public String getSale_Date_Parameter() {
        return Sale_Date_Parameter;
    }

    public void setSale_Date_Parameter(String Sale_Date_Parameter) {
        this.Sale_Date_Parameter = Sale_Date_Parameter;
    }

    public String getPSMID() {
        return PSMID;
    }

    public void setPSMID(String PSMID) {
        this.PSMID = PSMID;
    }

    public String getCustomer_id() {
        return Customer_id;
    }

    public void setCustomer_id(String Customer_id) {
        this.Customer_id = Customer_id;
    }

    public String getCustomer_Name() {
        return Customer_Name;
    }

    public void setCustomer_Name(String Customer_Name) {
        this.Customer_Name = Customer_Name;
    }

    public String getPRICEGROUP() {
        return PRICEGROUP;
    }

    public void setPRICEGROUP(String PRICEGROUP) {
        this.PRICEGROUP = PRICEGROUP;
    }

    public String getLINEDISC() {
        return LINEDISC;
    }

    public void setLINEDISC(String LINEDISC) {
        this.LINEDISC = LINEDISC;
    }

    public String getC_Type() {
        return C_Type;
    }

    public void setC_Type(String C_Type) {
        this.C_Type = C_Type;
    }

    public String getCOMMISSIONGROUP() {
        return COMMISSIONGROUP;
    }

    public void setCOMMISSIONGROUP(String COMMISSIONGROUP) {
        this.COMMISSIONGROUP = COMMISSIONGROUP;
    }

    public String getSALESGROUP() {
        return SALESGROUP;
    }

    public void setSALESGROUP(String SALESGROUP) {
        this.SALESGROUP = SALESGROUP;
    }

    public String getSubDepot_id() {
        return SubDepot_id;
    }

    public void setSubDepot_id(String SubDepot_id) {
        this.SubDepot_id = SubDepot_id;
    }

    public String getCUSTCLASSIFICATIONID() {
        return CUSTCLASSIFICATIONID;
    }

    public void setCUSTCLASSIFICATIONID(String CUSTCLASSIFICATIONID) {
        this.CUSTCLASSIFICATIONID = CUSTCLASSIFICATIONID;
    }

    public int getFlag() {
        return Flag;
    }

    public void setFlag(int Flag) {
        this.Flag = Flag;
    }

    public String getACCOUNTNUM() {
        return ACCOUNTNUM;
    }

    public void setACCOUNTNUM(String ACCOUNTNUM) {
        this.ACCOUNTNUM = ACCOUNTNUM;
    }

    public int getMandt() {
        return Mandt;
    }

    public void setMandt(int Mandt) {
        this.Mandt = Mandt;
    }

}
