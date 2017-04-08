package com.hgil.siconprocess.activity.temp_activities;

/**
 * Created by mohan.giri on 07-04-2017.
 */

public class SaleRejModel {

    private String route_id;
    private String route_name;
    private String str_date;
    private double sale_prct;
    private double rej_prct;

    public double getRej_prct() {
        return rej_prct;
    }

    public void setRej_prct(double rej_prct) {
        this.rej_prct = rej_prct;
    }

    public String getRoute_name() {
        return route_name;
    }

    public void setRoute_name(String route_name) {
        this.route_name = route_name;
    }

    public String getStr_date() {
        return str_date;
    }

    public void setStr_date(String str_date) {
        this.str_date = str_date;
    }

    public double getSale_prct() {
        return sale_prct;
    }

    public void setSale_prct(double sale_prct) {
        this.sale_prct = sale_prct;
    }

    public String getRoute_id() {
        return route_id;
    }

    public void setRoute_id(String route_id) {
        this.route_id = route_id;
    }
}
