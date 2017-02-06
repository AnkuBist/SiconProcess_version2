package com.hgil.siconprocess.base;

import android.support.v7.app.AppCompatActivity;

import com.hgil.siconprocess.database.masterTables.RouteView;
import com.hgil.siconprocess.retrofit.loginResponse.dbModels.RouteModel;

import butterknife.ButterKnife;

/**
 * Created by mohan.giri on 01-02-2017.
 */

public abstract class BaseActivity extends AppCompatActivity {

    protected String routeId, routeName;

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        bindViews();
        initiateAppInstance();
    }

    private void bindViews() {
        ButterKnife.bind(this);
    }

    private void initiateAppInstance() {
        RouteView routeView = new RouteView(this);
        RouteModel routeModel = routeView.getRoute();
        routeId = routeModel.getRouteId();
        routeName = routeModel.getRouteName();
        SiconApp.getInstance().setRouteModel(routeModel);
        SiconApp.getInstance().setRouteId(routeId);
        SiconApp.getInstance().setRouteName(routeName);
    }

    public String getRouteId() {
        return routeId;
    }

    public String getRouteName() {
        return routeName;
    }
}
