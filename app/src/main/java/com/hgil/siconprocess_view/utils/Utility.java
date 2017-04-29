package com.hgil.siconprocess_view.utils;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.preference.PreferenceManager;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by mohan.giri on 04-01-2017.
 */

public class Utility {

    public static final String USER_ID = "user_id";
    public static final String LAST_LOGIN_ID = "last_login_id";
    public static final String LAST_LOGIN_PASSWORD = "last_login_password";
    public static final String LOGIN_STATUS = "login_status";
    public static final String LAST_LOGIN_DATE = "last_login_date";
    public static final String LAST_SYNC_DATE = "last_sync_date";

    /*check internet connection*/
    private static boolean isNetworkAvailable(Context context) {
        ConnectivityManager connectivityManager = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    /*save shared preferences*/
    public static void savePreference(Context context, String key, String value) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    /*read shared preferences*/
    public static String readPreference(Context context, String key) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        String restoredText = preferences.getString(key, "");
        return restoredText;
    }

    /*save boolean shared preferences*/
    public static void saveLoginStatus(Context context, String key, boolean value) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putBoolean(key, value);
        editor.apply();
    }

    /*read boolean shared preferences*/
    public static boolean readLoginStatus(Context context, String key) {
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(context);
        boolean restoredStatus = preferences.getBoolean(key, false);
        return restoredStatus;
    }


    /*clear shared preference data*/
    public static void clearPreference(Context context) {
        PreferenceManager.getDefaultSharedPreferences(context).edit().clear().apply();
    }

    // get timestamp
    public static String timeStamp() {
        String timeStamp = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss").format(new Timestamp(System.currentTimeMillis()));
        return timeStamp;
    }

    // get current date util
    public static String getCurDate() {
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date today = new Date();
        /*Date todayWithZeroTime = formatter.parse(formatter.format(today));
        return todayWithZeroTime.toString();*/
        String date = formatter.format(today);
        // Date todayWithZeroTime = formatter.parse(formatter.format(today));
        return date;
    }

    public static String getDate() {
        DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
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

    // get day month and day abbrevation
    public static String getDayMonthAbbrev(String strDate) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        Date startDate = null;
        try {
            startDate = df.parse(strDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        SimpleDateFormat simpleDateformat = new SimpleDateFormat("E, dd MMMM"); // the day of the week abbreviated
        return simpleDateformat.format(startDate);
    }

    /*date month and year*/
    public static String getDateMonth() {
        Format formatter = new SimpleDateFormat("dd, MMMM yyyy");
        String today = formatter.format(new Date());
        return today;
    }

    /*result to two decimal places*/
    public static String decimal2Place(float floatVal) {
        String formattedString = String.format("%.02f", floatVal);
        return formattedString;
    }

    public static double roundZeroDecimals(double d) {
        DecimalFormat twoDForm = new DecimalFormat("#");
        return Double.valueOf(twoDForm.format(d));
    }

    public static double roundTwoDecimals(double d) {
        DecimalFormat twoDForm = new DecimalFormat("#.##");
        return Double.valueOf(twoDForm.format(d));
    }

    public static double roundOff(double val) {
        BigDecimal a = new BigDecimal(val);
        BigDecimal roundOff = a.setScale(0, BigDecimal.ROUND_HALF_EVEN);
        return roundOff.doubleValue();
    }

    public static void closeKeyboard(Context c, View view) {
        InputMethodManager mgr = (InputMethodManager) c.getSystemService(Context.INPUT_METHOD_SERVICE);

        // check if no view has focus:
        View v = ((Activity) c).getCurrentFocus();
        if (v == null)
            return;

        mgr.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    public static Integer getInteger(String str) {
        if (str == null || str.length() == 0) {
            return new Integer(0);
        } else {
            return Integer.parseInt(str);
        }
    }

    public static double getDouble(String str) {
        if (str == null || str.length() == 0) {
            return new Double(0);
        } else {
            return Double.parseDouble(str);
        }
    }

    public static String getString(String str) {
        if (str == null || str.length() == 0) {
            return "";
        } else {
            return str;
        }
    }

    /*calculate time difference*/
    public static String timeVariance(String date1, String date2) {

        SimpleDateFormat format = new SimpleDateFormat("hh:mm");
        Date Date1 = null, Date2 = null;
        try {
            Date1 = format.parse(date1);
            Date2 = format.parse(date2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        long mills = Date2.getTime() - Date1.getTime();
        Log.v("Data1", "" + Date1.getTime());
        Log.v("Data2", "" + Date2.getTime());
        int Hours = (int) (mills / (1000 * 60 * 60));
        int Mins = (int) (mills / (1000 * 60)) % 60;

        //String diff = Hours + ":" + Mins; // updated value every1 second

        String diff = "";
        if (Hours > 0)
            diff = Hours + "hr, " + Mins + "min(s)";
        else
            diff = Mins + "min(s)";

        return diff;
    }


    public static String getAppVersion(Context context) {
        String appVersion = "";
        try {
            appVersion = context.getPackageManager().getPackageInfo(context.getPackageName(), 0).versionName;
            return appVersion;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            appVersion = "";
        }
        return appVersion;
    }

}
