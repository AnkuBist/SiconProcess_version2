package com.hgil.siconprocess_view.retrofit.loginResponse.dbModel;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by mohan.giri on 11-04-2017.
 */

public class RouteModel {
    @SerializedName("depot_id")
    @Expose
    private String depotId;
    @SerializedName("route_id")
    @Expose
    private String routeId;
    @SerializedName("route_name")
    @Expose
    private String routeName;
    @SerializedName("cashier_name")
    @Expose
    private String cashierName;
    @SerializedName("PSMID")
    @Expose
    private String pSMID;
    @SerializedName("PSM_Name")
    @Expose
    private String pSMName;

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

    public String getRouteName() {
        return routeName;
    }

    public void setRouteName(String routeName) {
        this.routeName = routeName;
    }

    public String getCashierName() {
        return cashierName;
    }

    public void setCashierName(String cashierName) {
        this.cashierName = cashierName;
    }

    public String getPSMID() {
        return pSMID;
    }

    public void setPSMID(String pSMID) {
        this.pSMID = pSMID;
    }

    public String getPSMName() {
        return pSMName;
    }

    public void setPSMName(String pSMName) {
        this.pSMName = pSMName;
    }
}
