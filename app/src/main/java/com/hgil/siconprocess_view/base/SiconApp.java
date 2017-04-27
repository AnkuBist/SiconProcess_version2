package com.hgil.siconprocess_view.base;

import android.app.Activity;
import android.app.Application;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

import com.hgil.siconprocess_view.retrofit.loginResponse.dbModel.RouteModel;

/**
 * Created by mohan.giri on 25-01-2017.
 */

public class SiconApp extends Application {

    public static SiconApp appInstance = null;
    private RouteModel routeModel;
    private String zoneName;
    private String depotName;
    private String routeName;
    private String routeId;
    private String loginId;
    private String cashierName;
    private String cashierContact;

    public static SiconApp getInstance() {
        if (null == appInstance) {
            appInstance = new SiconApp();
        }
        return appInstance;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        // fix the project screenOrientation to portrait
        // register to be informed of activities starting up
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

                // new activity created; force its orientation to portrait
                activity.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
                //Utility.closeKeyboard(activity);
            }

            @Override
            public void onActivityStarted(Activity activity) {

            }

            @Override
            public void onActivityResumed(Activity activity) {

            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {

            }
        });
    }

    public RouteModel getRouteModel() {
        return routeModel;
    }

    public void setRouteModel(RouteModel routeModel) {
        this.routeModel = routeModel;
    }

    public String getZoneName() {
        return zoneName;
    }

    public void setZoneName(String zoneName) {
        this.zoneName = zoneName;
    }

    public String getDepotName() {
        return depotName;
    }

    public void setDepotName(String depotName) {
        this.depotName = depotName;
    }

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

    public String getLoginId() {
        return loginId;
    }

    public void setLoginId(String loginId) {
        this.loginId = loginId;
    }

    public String getCashierName() {
        return cashierName;
    }

    public void setCashierName(String cashierName) {
        this.cashierName = cashierName;
    }

    public String getCashierContact() {
        return cashierContact;
    }

    public void setCashierContact(String cashierContact) {
        this.cashierContact = cashierContact;
    }
}
