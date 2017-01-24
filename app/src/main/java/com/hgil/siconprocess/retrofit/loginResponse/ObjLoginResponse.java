package com.hgil.siconprocess.retrofit.loginResponse;

/**
 * Created by mohan.giri on 24-01-2017.
 */
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.hgil.siconprocess.retrofit.loginResponse.dbModels.RouteModel;

public class ObjLoginResponse {
    @SerializedName("routeDetail")
    @Expose
    private RouteModel routeDetail;

    public RouteModel getRouteDetail() {
        return routeDetail;
    }

    public void setRouteDetail(RouteModel routeDetail) {
        this.routeDetail = routeDetail;
    }

}