package com.hgil.siconprocess.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.preference.PreferenceManager;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by mohan.giri on 04-01-2017.
 */

public class Utility {

    /*check internet connection*/
    private static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    public static final String USER_ID = "user_id";
    public static final String LAST_LOGIN_ID = "last_login_id";

    /*save shared preferences*/
    public static void savePreference(Activity activity, String key, String value) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(activity);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    /*read shared preferences*/
    public static String readPreference(Activity activity, String key) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(activity);
        String restoredText = preferences.getString(key, "");
        return restoredText;
    }

    // get current date util
    public static String getCurDate() throws ParseException {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date today = new Date();
        /*Date todayWithZeroTime = formatter.parse(formatter.format(today));
        return todayWithZeroTime.toString();*/
        String date = formatter.format(today);
        // Date todayWithZeroTime = formatter.parse(formatter.format(today));
        return date;
    }

    // get day
    public static String getDay() {
        Date now = new Date();
        SimpleDateFormat simpleDateformat = new SimpleDateFormat("E"); // the day of the week abbreviated
        return simpleDateformat.format(now);
    }


}
