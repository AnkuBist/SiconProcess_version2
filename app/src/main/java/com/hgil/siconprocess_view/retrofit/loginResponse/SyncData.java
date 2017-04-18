package com.hgil.siconprocess_view.retrofit.loginResponse;

import com.hgil.siconprocess_view.retrofit.loginResponse.dbModel.OutletRemarkModel;
import com.hgil.siconprocess_view.retrofit.loginResponse.dbModel.PlanModel;

import java.util.ArrayList;

/**
 * Created by mohan.giri on 18-04-2017.
 */

public class SyncData {

    private ArrayList<PlanModel> arrPlan;
    private ArrayList<OutletRemarkModel> arrRemark;

    public ArrayList<PlanModel> getArrPlan() {
        return arrPlan;
    }

    public void setArrPlan(ArrayList<PlanModel> arrPlan) {
        this.arrPlan = arrPlan;
    }

    public ArrayList<OutletRemarkModel> getArrRemark() {
        return arrRemark;
    }

    public void setArrRemark(ArrayList<OutletRemarkModel> arrRemark) {
        this.arrRemark = arrRemark;
    }
}
