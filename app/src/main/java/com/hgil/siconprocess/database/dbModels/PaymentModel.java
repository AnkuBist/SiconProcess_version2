package com.hgil.siconprocess.database.dbModels;

/**
 * Created by mohan.giri on 08-02-2017.
 */

public class PaymentModel {

    private String customerId;
    private String customerName;
    private double saleAmount;
    // paid amount
    private double cashPaid;
    private UpiPaymentModel upiDetail;
    private ChequeDetailsModel chequeDetail;

    private double totalPaidAmount;

    // crate data
    private CrateDetailModel crateDetail;

    private String imei_no;
    private String lat_lng;
    private String time_stamp;
    private String login_id;

    public UpiPaymentModel getUpiDetail() {
        return upiDetail;
    }

    public void setUpiDetail(UpiPaymentModel upiDetail) {
        this.upiDetail = upiDetail;
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

    public double getSaleAmount() {
        return saleAmount;
    }

    public void setSaleAmount(double saleAmount) {
        this.saleAmount = saleAmount;
    }

    public double getCashPaid() {
        return cashPaid;
    }

    public void setCashPaid(double cashPaid) {
        this.cashPaid = cashPaid;
    }

    public ChequeDetailsModel getChequeDetail() {
        return chequeDetail;
    }

    public void setChequeDetail(ChequeDetailsModel chequeDetail) {
        this.chequeDetail = chequeDetail;
    }

    public double getTotalPaidAmount() {
        return totalPaidAmount;
    }

    public void setTotalPaidAmount(double totalPaidAmount) {
        this.totalPaidAmount = totalPaidAmount;
    }

    public CrateDetailModel getCrateDetail() {
        return crateDetail;
    }

    public void setCrateDetail(CrateDetailModel crateDetail) {
        this.crateDetail = crateDetail;
    }

    public String getImei_no() {
        return imei_no;
    }

    public void setImei_no(String imei_no) {
        this.imei_no = imei_no;
    }

    public String getLat_lng() {
        return lat_lng;
    }

    public void setLat_lng(String lat_lng) {
        this.lat_lng = lat_lng;
    }

    public String getTime_stamp() {
        return time_stamp;
    }

    public void setTime_stamp(String time_stamp) {
        this.time_stamp = time_stamp;
    }

    public String getLogin_id() {
        return login_id;
    }

    public void setLogin_id(String login_id) {
        this.login_id = login_id;
    }
}
