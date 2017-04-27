package com.hgil.siconprocess_view.retrofit.loginResponse.dbModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by mohan.giri on 17-04-2017.
 */

public class RouteRemarkModel {
    @SerializedName("user_id")
    @Expose
    private String user_id;
    @SerializedName("route_id")
    @Expose
    private String route_id;
    @SerializedName("route_name")
    @Expose
    private String route_name;
    @SerializedName("route_remark")
    @Expose
    private String route_remark;
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

    public String getRoute_remark() {
        return route_remark;
    }

    public void setRoute_remark(String route_remark) {
        this.route_remark = route_remark;
    }

    public String getRemark_date() {
        return remark_date;
    }

    public void setRemark_date(String remark_date) {
        this.remark_date = remark_date;
    }
}
