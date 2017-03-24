package com.hgil.siconprocess.utils;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;

import com.hgil.siconprocess.activity.LoginActivity;

/**
 * Created by mohan.giri on 01-03-2017.
 */

public class UtilNetworkLocation {

    private static final String TAG = LoginActivity.class.getName();

    public static void fetchLocation(Context mContext) {
        if (canGetLocation(mContext) == true) {
            printCoordinates(getLocation(mContext));
            //DO SOMETHING USEFUL HERE. ALL GPS PROVIDERS ARE CURRENTLY ENABLED
        } else {
            //SHOW OUR SETTINGS ALERT, AND LET THE USE TURN ON ALL THE GPS PROVIDERS
            showSettingsAlert(mContext);
        }
    }

    public static boolean canGetLocation(Context mContext) {
        boolean result = true;
        LocationManager lm = null;
        boolean gps_enabled = false;
        boolean network_enabled = false;
        if (lm == null)
            lm = (LocationManager) mContext.getSystemService(Context.LOCATION_SERVICE);

        // exceptions will be thrown if provider is not permitted.
        try {
            network_enabled = lm
                    .isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        } catch (Exception ex) {
        }
        try {
            gps_enabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
        } catch (Exception ex) {

        }
        if (network_enabled == true || gps_enabled == true) {
            result = true;
        } else {
            result = false;
        }
        return result;
    }

    public static void showSettingsAlert(final Context mContext) {
        final AlertDialog.Builder alertDialog = new AlertDialog.Builder(mContext);

        // Setting Dialog Title
        //alertDialog.setTitle("Error!");

        // Setting Dialog Message
        alertDialog.setMessage("Enable Location Services");

        // On pressing Settings button
        alertDialog.setPositiveButton("Ok",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(
                                Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                        mContext.startActivity(intent);
                        dialog.dismiss();
                    }
                });

        final AlertDialog alert = alertDialog.create();
        alert.show();
        alert.getButton(alert.BUTTON_POSITIVE).setAllCaps(false);
    }

    public static Location getLocation(Context mContext) {
        boolean gps_enabled = false;
        boolean network_enabled = false;

        LocationManager lm = (LocationManager) mContext
                .getSystemService(Context.LOCATION_SERVICE);

        //getCoordinates(lm);

        gps_enabled = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
        network_enabled = lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER);

        Location net_loc = null, gps_loc = null, finalLoc = null;
        if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            if (gps_enabled)
                gps_loc = lm.getLastKnownLocation(LocationManager.GPS_PROVIDER);
            if (network_enabled)
                net_loc = lm.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
        }
        if (gps_loc != null && net_loc != null) {

            //smaller the number more accurate result will
            if (gps_loc.getAccuracy() > net_loc.getAccuracy())
                finalLoc = net_loc;
            else
                finalLoc = gps_loc;

            // I used this just to get an idea (if both avail, its upto you which you want to take as I've taken location with more accuracy)
        } else {

            if (gps_loc != null) {
                finalLoc = gps_loc;
            } else if (net_loc != null) {
                finalLoc = net_loc;
            }
        }
        return finalLoc;
    }

    // print coordinates
    public static void printCoordinates(Location location) {
        if (location != null) {
            double lat = location.getLatitude();
            double lng = location.getLongitude();
            Log.e(TAG, "getCoordinates: " + lat + "," + lng);
        } else {
            Log.e(TAG, "getCoordinates: location object is nullHEHE");
        }
    }

    public static String getLatLng(Location location) {
        if (location != null) {
            double lat = location.getLatitude();
            double lng = location.getLongitude();
            return (lat + "," + lng);
        }
        return null;
    }

    // test methods
    // get coordinates
    private static void getCoordinates(LocationManager locationManager) {
        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE);
        criteria.setCostAllowed(false);
        String providerName = locationManager.getBestProvider(criteria, true);
        Location location = locationManager.getLastKnownLocation(providerName);
        if (location != null) {
            double lat = location.getLatitude();
            double lng = location.getLongitude();
            Log.e(TAG, "getCoordinates: " + lat + "," + lng);
        } else {
            Log.e(TAG, "getCoordinates: location object is null123");
        }
    }
}
