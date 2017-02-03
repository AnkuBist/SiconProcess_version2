package com.hgil.siconprocess.database.dbModels;

/**
 * Created by mohan.giri on 03-02-2017.
 */

public class InvoiceOutModel {

    private String invoiceNo;
    private String invoiceDate;
    private String customerId;
    private String routeId;
    private String vehicleNo;
    private String itemId;
    private String crateId;
    private float invQtyCr;
    private float invQtyPs;
    private float itemRate;
    private float totalAmount;

    // new added value
    private int fixedSample;
    private float demandTargetQty;
    private double orderAmount;
    private int stockAvail;
    private int tempStock;
    private String itemName;

   /* private int rejectionQty;
    private double rejTotalAmount;*/

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

    public float getItemRate() {
        return itemRate;
    }

    public void setItemRate(float itemRate) {
        this.itemRate = itemRate;
    }

    public float getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(float totalAmount) {
        this.totalAmount = totalAmount;
    }

    public int getFixedSample() {
        return fixedSample;
    }

    public void setFixedSample(int fixedSample) {
        this.fixedSample = fixedSample;
    }

    public float getDemandTargetQty() {
        return demandTargetQty;
    }

    public void setDemandTargetQty(float demandTargetQty) {
        this.demandTargetQty = demandTargetQty;
    }

    public double getOrderAmount() {
        return orderAmount;
    }

    public void setOrderAmount(double orderAmount) {
        this.orderAmount = orderAmount;
    }

    public int getStockAvail() {
        return stockAvail;
    }

    public void setStockAvail(int stockAvail) {
        this.stockAvail = stockAvail;
    }

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
}

  /*  public int getRejectionQty() {
        return rejectionQty;
    }

    public void setRejectionQty(int rejectionQty) {
        this.rejectionQty = rejectionQty;
    }

    public double getRejTotalAmount() {
        return rejTotalAmount;
    }

    public void setRejTotalAmount(double rejTotalAmount) {
        this.rejTotalAmount = rejTotalAmount;
    }
}*/