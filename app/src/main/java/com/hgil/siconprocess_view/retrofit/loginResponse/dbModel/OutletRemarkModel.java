package com.hgil.siconprocess_view.retrofit.loginResponse.dbModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by mohan.giri on 17-04-2017.
 */

public class OutletRemarkModel {
    @SerializedName("user_id")
    @Expose
    private String user_id;
    @SerializedName("route_id")
    @Expose
    private String route_id;
    @SerializedName("route_name")
    @Expose
    private String route_name;
    @SerializedName("outlet_id")
    @Expose
    private String outlet_id;
    @SerializedName("outlet_name")
    @Expose
    private String outlet_name;
    @SerializedName("remark")
    @Expose
    private String remark;
    @SerializedName("remark_date")
    @Expose
    private String remark_date;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getRoute_id() {
        return route_id;
    }

    public void setRoute_id(String route_id) {
        this.route_id = route_id;
    }

    public String getRoute_name() {
        return route_name;
    }

    public void setRoute_name(String route_name) {
        this.route_name = route_name;
    }

    public String getOutlet_id() {
        return outlet_id;
    }

    public void setOutlet_id(String outlet_id) {
        this.outlet_id = outlet_id;
    }

    public String getOutlet_name() {
        return outlet_name;
    }

    public void setOutlet_name(String outlet_name) {
        this.outlet_name = outlet_name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getRemark_date() {
        return remark_date;
    }

    public void setRemark_date(String remark_date) {
        this.remark_date = remark_date;
    }
}
