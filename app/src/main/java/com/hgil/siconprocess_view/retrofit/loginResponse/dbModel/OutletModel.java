package com.hgil.siconprocess_view.retrofit.loginResponse.dbModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by mohan.giri on 12-04-2017.
 */

public class OutletModel {

 /*   @SerializedName("Sub_Company_id")
    @Expose
    private String subCompanyId;*/
    /*@SerializedName("Depot")
    @Expose
    private String depot;*/
    @SerializedName("Route_id")
    @Expose
    private String routeId;
    /* @SerializedName("Route_Name")
     @Expose*/
    private String routeName;
  /*  @SerializedName("PSMID")
    @Expose
    private String pSMID;*/
    @SerializedName("Customer_id")
    @Expose
    private String customerId;
    @SerializedName("Customer_Name")
    @Expose
    private String customerName;
/*    @SerializedName("PRICEGROUP")
    @Expose
    private String pRICEGROUP;
    @SerializedName("LINEDISC")
    @Expose
    private String lINEDISC;*/
   /* @SerializedName("C_Type")
    @Expose
    private String cType;*/
    @SerializedName("sale_status")
    @Expose
    private String sale_status;
   /* @SerializedName("ACCOUNTNUM")
    @Expose
    private String aCCOUNTNUM;*/
    @SerializedName("ContactNo")
    @Expose
    private String contactNo;
    @SerializedName("outstanding")
    @Expose
    private double outstanding;
    @SerializedName("inv_amount")
    @Expose
    private double inv_amount;
    @SerializedName("net_amount")
    @Expose
    private double net_amount;
    @SerializedName("cash_payment")
    @Expose
    private double cash_payment;
    @SerializedName("inv_time")
    @Expose
    private String inv_time;

   /* public String getSubCompanyId() {
        return subCompanyId;
    }

    public void setSubCompanyId(String subCompanyId) {
        this.subCompanyId = subCompanyId;
    }*/

/*    public String getDepot() {
        return depot;
    }

    public void setDepot(String depot) {
        this.depot = depot;
    }*/

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

 /*   public String getPSMID() {
        return pSMID;
    }

    public void setPSMID(String pSMID) {
        this.pSMID = pSMID;
    }
*/
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

/*    public String getPRICEGROUP() {
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
    }*/

    public String getSale_status() {
        return sale_status;
    }

    public void setSale_status(String sale_status) {
        this.sale_status = sale_status;
    }

 /*   public String getACCOUNTNUM() {
        return aCCOUNTNUM;
    }

    public void setACCOUNTNUM(String aCCOUNTNUM) {
        this.aCCOUNTNUM = aCCOUNTNUM;
    }*/

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public double getOutstanding() {
        return outstanding;
    }

    public void setOutstanding(double outstanding) {
        this.outstanding = outstanding;
    }

    public String getInv_time() {
        return inv_time;
    }

    public void setInv_time(String inv_time) {
        this.inv_time = inv_time;
    }

    public double getCash_payment() {
        return cash_payment;
    }

    public void setCash_payment(double cash_payment) {
        this.cash_payment = cash_payment;
    }

    public double getInv_amount() {
        return inv_amount;
    }

    public void setInv_amount(double inv_amount) {
        this.inv_amount = inv_amount;
    }

    public double getNet_amount() {
        return net_amount;
    }

    public void setNet_amount(double net_amount) {
        this.net_amount = net_amount;
    }
}