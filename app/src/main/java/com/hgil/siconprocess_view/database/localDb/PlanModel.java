package com.hgil.siconprocess_view.database.localDb;

/**
 * Created by mohan.giri on 15-04-2017.
 */

public class PlanModel {

    private String user_id;
    private String plan;
    private String plan_date;

    public String getUser_id() {
        return user_id;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public String getPlan() {
        return plan;
    }

    public void setPlan(String plan) {
        this.plan = plan;
    }

    public String getPlan_date() {
        return plan_date;
    }

    public void setPlan_date(String plan_date) {
        this.plan_date = plan_date;
    }
}
