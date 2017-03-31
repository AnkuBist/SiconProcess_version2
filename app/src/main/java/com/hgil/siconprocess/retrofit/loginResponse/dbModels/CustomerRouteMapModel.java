package com.hgil.siconprocess.retrofit.loginResponse.dbModels;

/**
 * Created by mohan.giri on 24-01-2017.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CustomerRouteMapModel implements Serializable {

    @SerializedName("Rec_id")
    @Expose
    private Long recId;
    @SerializedName("Sub_Company_id")
    @Expose
    private String subCompanyId;
    @SerializedName("Depot")
    @Expose
    private String depot;
    @SerializedName("Route_id")
    @Expose
    private String routeId;
    @SerializedName("Route_Name")
    @Expose
    private String routeName;
    @SerializedName("Sale_Date_Parameter")
    @Expose
    private String saleDateParameter;
    @SerializedName("PSMID")
    @Expose
    private String pSMID;
    @SerializedName("Customer_id")
    @Expose
    private String customerId;
    @SerializedName("Customer_Name")
    @Expose
    private String customerName;
    @SerializedName("PRICEGROUP")
    @Expose
    private String pRICEGROUP;
    @SerializedName("LINEDISC")
    @Expose
    private String lINEDISC;
    @SerializedName("C_Type")
    @Expose
    private String cType;
    @SerializedName("COMMISSIONGROUP")
    @Expose
    private String cOMMISSIONGROUP;
    @SerializedName("SALESGROUP")
    @Expose
    private String sALESGROUP;
    @SerializedName("SubDepot_id")
    @Expose
    private String subDepotId;
    @SerializedName("CUSTCLASSIFICATIONID")
    @Expose
    private String cUSTCLASSIFICATIONID;
    @SerializedName("Flag")
    @Expose
    private int flag;
    @SerializedName("RFlag")
    @Expose
    private int rFlag;
    @SerializedName("ACCOUNTNUM")
    @Expose
    private String aCCOUNTNUM;
    @SerializedName("Mandt")
    @Expose
    private int mandt;
    @SerializedName("cust_status")
    @Expose
    private String custStatus;

    public String getCustStatus() {
        return custStatus;
    }

    public void setCustStatus(String custStatus) {
        this.custStatus = custStatus;
    }

    public Long getRecId() {
        return recId;
    }

    public void setRecId(Long recId) {
        this.recId = recId;
    }

    public String getSubCompanyId() {
        return subCompanyId;
    }

    public void setSubCompanyId(String subCompanyId) {
        this.subCompanyId = subCompanyId;
    }

    public String getDepot() {
        return depot;
    }

    public void setDepot(String depot) {
        this.depot = depot;
    }

    public String getRouteId() {
        return routeId;
    }

    public void setRouteId(String routeId) {
        this.routeId = routeId;
    }

    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

    public String getSaleDateParameter() {
        return saleDateParameter;
    }

    public void setSaleDateParameter(String saleDateParameter) {
        this.saleDateParameter = saleDateParameter;
    }

    public String getPSMID() {
        return pSMID;
    }

    public void setPSMID(String pSMID) {
        this.pSMID = pSMID;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getPRICEGROUP() {
        return pRICEGROUP;
    }

    public void setPRICEGROUP(String pRICEGROUP) {
        this.pRICEGROUP = pRICEGROUP;
    }

    public String getLINEDISC() {
        return lINEDISC;
    }

    public void setLINEDISC(String lINEDISC) {
        this.lINEDISC = lINEDISC;
    }

    public String getCType() {
        return cType;
    }

    public void setCType(String cType) {
        this.cType = cType;
    }

    public String getCOMMISSIONGROUP() {
        return cOMMISSIONGROUP;
    }

    public void setCOMMISSIONGROUP(String cOMMISSIONGROUP) {
        this.cOMMISSIONGROUP = cOMMISSIONGROUP;
    }

    public String getSALESGROUP() {
        return sALESGROUP;
    }

    public void setSALESGROUP(String sALESGROUP) {
        this.sALESGROUP = sALESGROUP;
    }

    public String getSubDepotId() {
        return subDepotId;
    }

    public void setSubDepotId(String subDepotId) {
        this.subDepotId = subDepotId;
    }

    public String getCUSTCLASSIFICATIONID() {
        return cUSTCLASSIFICATIONID;
    }

    public void setCUSTCLASSIFICATIONID(String cUSTCLASSIFICATIONID) {
        this.cUSTCLASSIFICATIONID = cUSTCLASSIFICATIONID;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public int getRFlag() {
        return rFlag;
    }

    public void setRFlag(int rFlag) {
        this.rFlag = rFlag;
    }

    public String getACCOUNTNUM() {
        return aCCOUNTNUM;
    }

    public void setACCOUNTNUM(String aCCOUNTNUM) {
        this.aCCOUNTNUM = aCCOUNTNUM;
    }

    public int getMandt() {
        return mandt;
    }

    public void setMandt(int mandt) {
        this.mandt = mandt;
    }

}
