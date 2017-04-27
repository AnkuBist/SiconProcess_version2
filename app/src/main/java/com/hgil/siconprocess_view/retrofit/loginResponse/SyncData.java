package com.hgil.siconprocess_view.retrofit.loginResponse;

import com.hgil.siconprocess_view.retrofit.loginResponse.dbModel.OutletRemarkModel;
import com.hgil.siconprocess_view.retrofit.loginResponse.dbModel.PlanModel;
import com.hgil.siconprocess_view.retrofit.loginResponse.dbModel.RouteRemarkModel;

import java.util.ArrayList;

/**
 * Created by mohan.giri on 18-04-2017.
 */

public class SyncData {

    private ArrayList<PlanModel> arrPlan;
    private ArrayList<RouteRemarkModel> arrRouteRemark;
    private ArrayList<OutletRemarkModel> arrRemark;

    public ArrayList<PlanModel> getArrPlan() {
        return arrPlan;
    }

    public void setArrPlan(ArrayList<PlanModel> arrPlan) {
        this.arrPlan = arrPlan;
    }

    public ArrayList<RouteRemarkModel> getArrRouteRemark() {
        return arrRouteRemark;
    }

    public void setArrRouteRemark(ArrayList<RouteRemarkModel> arrRouteRemark) {
        this.arrRouteRemark = arrRouteRemark;
    }

    public ArrayList<OutletRemarkModel> getArrRemark() {
        return arrRemark;
    }

    public void setArrRemark(ArrayList<OutletRemarkModel> arrRemark) {
        this.arrRemark = arrRemark;
    }
}
