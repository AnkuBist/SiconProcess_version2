package com.hgil.siconprocess.database.dbModels;

/**
 * Created by mohan.giri on 08-02-2017.
 */

public class CrateDetailModel {

    private String customer_id;
    private int openingCrates;
    private int issuedCrates;
    private int receivedCrates;
    private int balanceCrates;

    private String imei_no;
    private String lat_lng;
    private String time_stamp;
    private String login_id;

    public int getBalanceCrates() {
        return balanceCrates;
    }

    public void setBalanceCrates(int balanceCrates) {
        this.balanceCrates = balanceCrates;
    }

    public int getOpeningCrates() {
        return openingCrates;
    }

    public void setOpeningCrates(int openingCrates) {
        this.openingCrates = openingCrates;
    }

    public int getIssuedCrates() {
        return issuedCrates;
    }

    public void setIssuedCrates(int issuedCrates) {
        this.issuedCrates = issuedCrates;
    }

    public String getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(String customer_id) {
        this.customer_id = customer_id;
    }

    public int getReceivedCrates() {
        return receivedCrates;
    }

    public void setReceivedCrates(int receivedCrates) {
        this.receivedCrates = receivedCrates;
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
