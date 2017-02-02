package com.hgil.siconprocess.base;

import android.app.Application;

import com.hgil.siconprocess.retrofit.loginResponse.dbModels.RouteModel;

/**
 * Created by mohan.giri on 25-01-2017.
 */

public class SiconApp extends Application {

    public static SiconApp appInstance = null;

    public static SiconApp getInstance() {
        if (null == appInstance) {
            appInstance = new SiconApp();
        }
        return appInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    public RouteModel getRouteModel() {
        return routeModel;
    }

    public void setRouteModel(RouteModel routeModel) {
        this.routeModel = routeModel;
    }

    private RouteModel routeModel;
    private String routeName;

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

    private String routeId;

    // initialising table object from this class only


}
