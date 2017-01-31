package com.hgil.siconprocess.adapter.invoice;

/**
 * Created by mohan.giri on 30-01-2017.
 */

public class InvoiceModel {

    private String mkey;
    private String rKey;
    private String routeManagemnetDate;
    private String invoiceNo;
    private String invoiceDate;
    private String customerId;
    private String routeId;
    private String vehicleNo;
    private String cashierCode;
    private String supervisorPaycode;
    private String itemId;
    private String crateId;
    private float invQtyCr;
    private float invQtyPs;
    private String groupId;
    private String groupPriceDate;
    private float itemRate;
    private float itemDiscount;
    private float itemCST;
    private float itemVAT;
    private float itemSurcharge;
    private float discountAmount;
    private float cSTAmount;
    private float vATAmount;
    private float surchargeAmount;
    private float totalAmount;

    // new added value
    private int fixedSample;
    private float demandTargetQty;
    private double orderAmount;
    private int stockAvail;
    private int tempStock;
    private String itemName;

    public int getTempStock() {
        return tempStock;
    }

    public void setTempStock(int tempStock) {
        this.tempStock = tempStock;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public int getStockAvail() {
        return stockAvail;
    }

    public void setStockAvail(int stockAvail) {
        this.stockAvail = stockAvail;
    }

    public int getFixedSample() {
        return fixedSample;
    }

    public void setFixedSample(int fixedSample) {
        this.fixedSample = fixedSample;
    }

    public double getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(double orderAmount) {
        this.orderAmount = orderAmount;
    }

    public float getDemandTargetQty() {
        return demandTargetQty;
    }

    public void setDemandTargetQty(float demandTargetQty) {
        this.demandTargetQty = demandTargetQty;
    }

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
