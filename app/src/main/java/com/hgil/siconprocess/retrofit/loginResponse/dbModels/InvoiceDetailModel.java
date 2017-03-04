package com.hgil.siconprocess.retrofit.loginResponse.dbModels;

/**
 * Created by mohan.giri on 24-01-2017.
 */

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class InvoiceDetailModel implements Serializable {

    @SerializedName("Mkey")
    @Expose
    private String mkey;
    @SerializedName("RKey")
    @Expose
    private String rKey;
    @SerializedName("Route_managemnet_Date")
    @Expose
    private String routeManagemnetDate;
    @SerializedName("Invoice_No")
    @Expose
    private String invoiceNo;
    @SerializedName("Invoice_Date")
    @Expose
    private String invoiceDate;
    @SerializedName("Customer_id")
    @Expose
    private String customerId;
    @SerializedName("Route_Id")
    @Expose
    private String routeId;
    @SerializedName("Vehicle_No")
    @Expose
    private String vehicleNo;
    @SerializedName("Cashier_Code")
    @Expose
    private String cashierCode;
    @SerializedName("Supervisor_Paycode")
    @Expose
    private String supervisorPaycode;
    @SerializedName("Item_id")
    @Expose
    private String itemId;
    @SerializedName("Crate_id")
    @Expose
    private String crateId;
    @SerializedName("InvQty_Cr")
    @Expose
    private float invQtyCr;
    @SerializedName("InvQty_ps")
    @Expose
    private float invQtyPs;
    @SerializedName("Group_id")
    @Expose
    private String groupId;
    @SerializedName("Group_Price_Date")
    @Expose
    private String groupPriceDate;
    @SerializedName("Item_Rate")
    @Expose
    private float itemRate;
    @SerializedName("Item_Discount")
    @Expose
    private float itemDiscount;
    @SerializedName("Item_CST")
    @Expose
    private float itemCST;
    @SerializedName("Item_VAT")
    @Expose
    private float itemVAT;
    @SerializedName("Item_Surcharge")
    @Expose
    private float itemSurcharge;
    @SerializedName("Discount_Amount")
    @Expose
    private float discountAmount;
    @SerializedName("CST_Amount")
    @Expose
    private float cSTAmount;
    @SerializedName("VAT_Amount")
    @Expose
    private float vATAmount;
    @SerializedName("Surcharge_Amount")
    @Expose
    private float surchargeAmount;
    @SerializedName("Total_Amount")
    @Expose
    private float totalAmount;

    public String getMkey() {
        return mkey;
    }

    public void setMkey(String mkey) {
        this.mkey = mkey;
    }

    public String getRKey() {
        return rKey;
    }

    public void setRKey(String rKey) {
        this.rKey = rKey;
    }

    public String getRouteManagemnetDate() {
        return routeManagemnetDate;
    }

    public void setRouteManagemnetDate(String routeManagemnetDate) {
        this.routeManagemnetDate = routeManagemnetDate;
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

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getRouteId() {
        return routeId;
    }

    public void setRouteId(String routeId) {
        this.routeId = routeId;
    }

    public String getVehicleNo() {
        return vehicleNo;
    }

    public void setVehicleNo(String vehicleNo) {
        this.vehicleNo = vehicleNo;
    }

    public String getCashierCode() {
        return cashierCode;
    }

    public void setCashierCode(String cashierCode) {
        this.cashierCode = cashierCode;
    }

    public String getSupervisorPaycode() {
        return supervisorPaycode;
    }

    public void setSupervisorPaycode(String supervisorPaycode) {
        this.supervisorPaycode = supervisorPaycode;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getCrateId() {
        return crateId;
    }

    public void setCrateId(String crateId) {
        this.crateId = crateId;
    }

    public float getInvQtyCr() {
        return invQtyCr;
    }

    public void setInvQtyCr(float invQtyCr) {
        this.invQtyCr = invQtyCr;
    }

    public float getInvQtyPs() {
        return invQtyPs;
    }

    public void setInvQtyPs(float invQtyPs) {
        this.invQtyPs = invQtyPs;
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public String getGroupPriceDate() {
        return groupPriceDate;
    }

    public void setGroupPriceDate(String groupPriceDate) {
        this.groupPriceDate = groupPriceDate;
    }

    public float getItemRate() {
        return itemRate;
    }

    public void setItemRate(float itemRate) {
        this.itemRate = itemRate;
    }

    public float getItemDiscount() {
        return itemDiscount;
    }

    public void setItemDiscount(float itemDiscount) {
        this.itemDiscount = itemDiscount;
    }

    public float getItemCST() {
        return itemCST;
    }

    public void setItemCST(float itemCST) {
        this.itemCST = itemCST;
    }

    public float getItemVAT() {
        return itemVAT;
    }

    public void setItemVAT(float itemVAT) {
        this.itemVAT = itemVAT;
    }

    public float getItemSurcharge() {
        return itemSurcharge;
    }

    public void setItemSurcharge(float itemSurcharge) {
        this.itemSurcharge = itemSurcharge;
    }

    public float getDiscountAmount() {
        return discountAmount;
    }

    public void setDiscountAmount(float discountAmount) {
        this.discountAmount = discountAmount;
    }

    public float getCSTAmount() {
        return cSTAmount;
    }

    public void setCSTAmount(float cSTAmount) {
        this.cSTAmount = cSTAmount;
    }

    public float getVATAmount() {
        return vATAmount;
    }

    public void setVATAmount(float vATAmount) {
        this.vATAmount = vATAmount;
    }

    public float getSurchargeAmount() {
        return surchargeAmount;
    }

    public void setSurchargeAmount(float surchargeAmount) {
        this.surchargeAmount = surchargeAmount;
    }

    public float getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(float totalAmount) {
        this.totalAmount = totalAmount;
    }

}