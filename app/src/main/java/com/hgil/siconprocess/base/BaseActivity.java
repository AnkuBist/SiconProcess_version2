package com.hgil.siconprocess.base;

import android.support.v7.app.AppCompatActivity;

import com.hgil.siconprocess.database.masterTables.RouteView;
import com.hgil.siconprocess.retrofit.loginResponse.dbModels.RouteModel;

import butterknife.ButterKnife;

/**
 * Created by mohan.giri on 01-02-2017.
 */

public abstract class BaseActivity extends AppCompatActivity {

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
        SiconApp.getInstance().setRouteModel(routeModel);
        SiconApp.getInstance().setRouteId(routeModel.getRouteId());
        SiconApp.getInstance().setRouteName(routeModel.getRouteName());
    }
}
