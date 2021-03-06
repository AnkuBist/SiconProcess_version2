package com.hgil.siconprocess_view.base.route_base;

import android.support.v7.app.AppCompatActivity;

import com.hgil.siconprocess_view.base.SiconApp;
import com.hgil.siconprocess_view.retrofit.loginResponse.dbModel.RouteModel;

import butterknife.ButterKnife;

/**
 * Created by mohan.giri on 01-02-2017.
 */

public abstract class Route_Base_Activity extends AppCompatActivity {

    protected RouteModel routeModel;
    protected String depotName, routeId, routeName, loginId, cashierName, cashierContact;

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
/*        RouteView routeView = new RouteView(this);
        routeModel = routeView.getRouteById(routeId);
        routeId = routeModel.getRouteId();
        routeName = routeModel.getRouteName();
        if (routeName != null)
            routeName = routeName.substring(0, 1).toUpperCase() + routeName.substring(1).toLowerCase();
        else
            routeName = "";*/

        routeModel = SiconApp.getInstance().getRouteModel();
        depotName = SiconApp.getInstance().getDepotName();
        routeId = SiconApp.getInstance().getRouteId();
        routeName = SiconApp.getInstance().getRouteName();
        loginId = SiconApp.getInstance().getLoginId();
        cashierName = SiconApp.getInstance().getCashierName();
        cashierContact = SiconApp.getInstance().getCashierContact();

       /* SiconApp.getInstance().setRouteModel(routeModel);
        SiconApp.getInstance().setRouteId(routeId);
        SiconApp.getInstance().setRouteName(routeName);
        SiconApp.getInstance().setLoginId(Utility.readPreference(this, Utility.LAST_LOGIN_ID));*/
    }

    public RouteModel getRouteModel() {
        return routeModel;
    }

    public String getDepotName() {
        return depotName;
    }

    public String getRouteId() {
        return routeId;
    }

    public String getRouteName() {
        return routeName;
    }

    public String getLoginId() {
        return loginId;
    }

    public String getCashierName() {
        return cashierName;
    }

    public String getCashierContact() {
        return cashierContact;
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
