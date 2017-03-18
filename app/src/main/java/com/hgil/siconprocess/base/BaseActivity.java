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

    /*runtime permission result handling*/
   /* @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        switch (requestCode) {
            case SEND_SMS:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted, yay! Do the
                    UtilsSms.sendSms(mobile);
                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Toast.makeText(RegistrationActivity.this, "SEND_SMS Denied", Toast.LENGTH_SHORT)
                            .show();
                }
                break;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }*/

}
