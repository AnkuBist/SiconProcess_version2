package com.hgil.siconprocess.activity.unused;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.Toast;

import com.hgil.siconprocess.R;

/**
 * Created by mohan.giri on 01-03-2017.
 */

public class HelloAndroidGpsActivity extends Activity implements android.content.DialogInterface.OnClickListener {

    //private EditText editTextShowLocation;
    //private Button buttonGetLocation;
    ///private ProgressBar progress;

    private LocationManager locManager;
    private LocationListener locListener = new MyLocationListener();

    private boolean gps_enabled = false;
    private boolean network_enabled = false;

    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        //editTextShowLocation = (EditText) findViewById(R.id.editTextShowLocation);

        //progress = (ProgressBar) findViewById(R.id.progressBar1);
        // progress.setVisibility(View.GONE);

        // buttonGetLocation = (Button) findViewById(R.id.buttonGetLocation);
        // buttonGetLocation.setOnClickListener(this);

        locManager = (LocationManager) this.getSystemService(Context.LOCATION_SERVICE);
        runMethod();
    }

    //@Override
    public void runMethod() {
        //progress.setVisibility(View.VISIBLE);
        // exceptions will be thrown if provider is not permitted.
        try {
            gps_enabled = locManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        } catch (Exception ex) {
        }
        try {
            network_enabled = locManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        } catch (Exception ex) {
        }

        // don't start listeners if no provider is enabled
        if (!gps_enabled && !network_enabled) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Attention!");
            builder.setMessage("Sorry, location is not determined. Please enable location providers");
            builder.setPositiveButton("OK", this);
            builder.setNeutralButton("Cancel", this);
            builder.create().show();
            //progress.setVisibility(View.GONE);
        }

        if (gps_enabled) {
            locManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locListener);
        }
        if (network_enabled) {
            locManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locListener);
        }
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        if (which == DialogInterface.BUTTON_NEUTRAL) {
            Toast.makeText(this, "Sorry, location is not detemined. To fix this please enable location providers.", Toast.LENGTH_SHORT).show();
            //editTextShowLocation.setText("Sorry, location is not determined. To fix this please enable location providers");
        } else if (which == DialogInterface.BUTTON_POSITIVE) {
            startActivity(new Intent(android.provider.Settings.ACTION_LOCATION_SOURCE_SETTINGS));
        }
    }

    class MyLocationListener implements LocationListener {
        @Override
        public void onLocationChanged(Location location) {
            if (location != null) {
                // This needs to stop getting the location data and save the battery power.
                locManager.removeUpdates(locListener);

                String londitude = "Londitude: " + location.getLongitude();
                String latitude = "Latitude: " + location.getLatitude();
                String altitiude = "Altitiude: " + location.getAltitude();
                String accuracy = "Accuracy: " + location.getAccuracy();
                String time = "Time: " + location.getTime();
                Log.e("TAG", "onLocationChanged: " + londitude + latitude + altitiude + accuracy + time);

                //editTextShowLocation.setText(londitude + "\n" + latitude + "\n" + altitiude + "\n" + accuracy + "\n" + time);
                //progress.setVisibility(View.GONE);
            }
        }

        @Override
        public void onProviderDisabled(String provider) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onProviderEnabled(String provider) {
            // TODO Auto-generated method stub

        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            // TODO Auto-generated method stub

        }
    }

}