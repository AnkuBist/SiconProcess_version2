package com.hgil.siconprocess.activity;

import android.support.v7.app.AppCompatActivity;

import com.hgil.siconprocess.SiconApp;
import com.hgil.siconprocess.database.tables.RouteView;
import com.hgil.siconprocess.retrofit.loginResponse.dbModels.RouteModel;

import butterknife.ButterKnife;

/**
 * Created by mohan.giri on 01-02-2017.
 */

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
        initiateAppInstance();
    }

    private void initiateAppInstance() {
        RouteView routeView = new RouteView(this);
        RouteModel routeModel = routeView.getRoute();
        SiconApp.getInstance().setRouteModel(routeModel);
        SiconApp.getInstance().setRouteId(routeModel.getRouteId());
        SiconApp.getInstance().setRouteName(routeModel.getRouteName());
    }
}
