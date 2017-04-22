package com.hgil.siconprocess_view.base;

import android.support.v7.app.AppCompatActivity;

import com.hgil.siconprocess_view.retrofit.loginResponse.dbModel.RouteModel;
import com.hgil.siconprocess_view.utils.Utility;

import butterknife.ButterKnife;

/**
 * Created by mohan.giri on 10-04-2017.
 */

public abstract class Base_Activity extends AppCompatActivity {

    protected RouteModel routeModel;
    protected String depotName, routeId, routeName, loginId;

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
        SiconApp.getInstance().setLoginId(Utility.readPreference(this, Utility.LAST_LOGIN_ID));
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