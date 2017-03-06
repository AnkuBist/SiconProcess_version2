package com.hgil.siconprocess.retrofit.loginResponse.dbModels;

/**
 * Created by mohan.giri on 24-01-2017.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class CrateCollectionModel implements Serializable {

    /*  @SerializedName("Rec_id")
      @Expose
      private Long recId;*/
    @SerializedName("DDate")
    @Expose
    private String dDate;
    @SerializedName("Sub_Company_id")
    @Expose
    private String subCompanyId;
    @SerializedName("Depot_id")
    @Expose
    private String depotId;
    @SerializedName("Route_id")
    @Expose
    private String routeId;
    @SerializedName("Route_Management_id")
    @Expose
    private String routeManagementId;
    @SerializedName("Route_Management_Date")
    @Expose
    private String routeManagementDate;
    @SerializedName("Customer_id")
    @Expose
    private String customerId;
    @SerializedName("Invoice_No")
    @Expose
    private String invoiceNo;
    @SerializedName("Invoice_date")
    @Expose
    private String invoiceDate;
    @SerializedName("Crate_Id")
    @Expose
    private String crateId;
    @SerializedName("Crate_Qty")
    @Expose
    private float crateQty;
    @SerializedName("Receive_Qty")
    @Expose
    private float receiveQty;
    @SerializedName("Balance_Qty")
    @Expose
    private float balanceQty;
    @SerializedName("Cashier_paycode")
    @Expose
    private String cashierPaycode;
    @SerializedName("Loading_paycode")
    @Expose
    private String loadingPaycode;
    @SerializedName("Active")
    @Expose
    private String active;
    @SerializedName("updateby_paycode")
    @Expose
    private String updatebyPaycode;
    @SerializedName("updateby_Date")
    @Expose
    private String updatebyDate;
    @SerializedName("updated_ip")
    @Expose
    private String updatedIp;

    /*public Long getRecId() {
        return recId;
    }

    public void setRecId(Long recId) {
        this.recId = recId;
    }*/

    public String getDDate() {
        return dDate;
    }

    public void setDDate(String dDate) {
        this.dDate = dDate;
    }

    public String getSubCompanyId() {
        return subCompanyId;
    }

    public void setSubCompanyId(String subCompanyId) {
        this.subCompanyId = subCompanyId;
    }

    public String getDepotId() {
        return depotId;
    }

    public void setDepotId(String depotId) {
        this.depotId = depotId;
    }

    public String getRouteId() {
        return routeId;
    }

    public void setRouteId(String routeId) {
        this.routeId = routeId;
    }

    public String getRouteManagementId() {
        return routeManagementId;
    }

    public void setRouteManagementId(String routeManagementId) {
        this.routeManagementId = routeManagementId;
    }

    public String getRouteManagementDate() {
        return routeManagementDate;
    }

    public void setRouteManagementDate(String routeManagementDate) {
        this.routeManagementDate = routeManagementDate;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getInvoiceNo() {
        return invoiceNo;
    }

    public void setInvoiceNo(String invoiceNo) {
        this.invoiceNo = invoiceNo;
    }

    public String getInvoiceDate() {
        return invoiceDate;
    }

    public void setInvoiceDate(String invoiceDate) {
        this.invoiceDate = invoiceDate;
    }

    public String getCrateId() {
        return crateId;
    }

    public void setCrateId(String crateId) {
        this.crateId = crateId;
    }

    public float getCrateQty() {
        return crateQty;
    }

    public void setCrateQty(float crateQty) {
        this.crateQty = crateQty;
    }

    public float getReceiveQty() {
        return receiveQty;
    }

    public void setReceiveQty(float receiveQty) {
        this.receiveQty = receiveQty;
    }

    public float getBalanceQty() {
        return balanceQty;
    }

    public void setBalanceQty(float balanceQty) {
        this.balanceQty = balanceQty;
    }

    public String getCashierPaycode() {
        return cashierPaycode;
    }

    public void setCashierPaycode(String cashierPaycode) {
        this.cashierPaycode = cashierPaycode;
    }

    public String getLoadingPaycode() {
        return loadingPaycode;
    }

    public void setLoadingPaycode(String loadingPaycode) {
        this.loadingPaycode = loadingPaycode;
    }

    public String getActive() {
        return active;
    }

    public void setActive(String active) {
        this.active = active;
    }

    public String getUpdatebyPaycode() {
        return updatebyPaycode;
    }

    public void setUpdatebyPaycode(String updatebyPaycode) {
        this.updatebyPaycode = updatebyPaycode;
    }

    public String getUpdatebyDate() {
        return updatebyDate;
    }

    public void setUpdatebyDate(String updatebyDate) {
        this.updatebyDate = updatebyDate;
    }

    public String getUpdatedIp() {
        return updatedIp;
    }

    public void setUpdatedIp(String updatedIp) {
        this.updatedIp = updatedIp;
    }

}
