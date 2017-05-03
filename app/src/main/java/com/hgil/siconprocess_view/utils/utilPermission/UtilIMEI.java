package com.hgil.siconprocess_view.utils.utilPermission;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.telephony.TelephonyManager;

/**
 * Created by mohan.giri on 24-03-2017.
 */

public class UtilIMEI {

    public static final int READ_PHONE_STATE = 103;

    /*check sms permission before sending sms*/
    // simple trick to check and ask permission
    public static void checkAndroidVersionForPhoneState(Context context) {
        if (Build.VERSION.SDK_INT >= 23) {
            int checkPhoneStatePermission = ContextCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE);
            if (checkPhoneStatePermission != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.READ_PHONE_STATE}, READ_PHONE_STATE);
                return;
            } else {
                IMEINumber(context);
            }
        } else {
            IMEINumber(context);
        }
    }

    // simply get imei number if permission granted
    public static String getIMEINumber(Context context) {
        if (Build.VERSION.SDK_INT >= 23) {
            int checkPhoneStatePermission = ContextCompat.checkSelfPermission(context, Manifest.permission.READ_PHONE_STATE);
            if (checkPhoneStatePermission != PackageManager.PERMISSION_GRANTED) {
                return null;
            } else {
                return IMEINumber(context);
            }
        } else {
            return IMEINumber(context);
        }
    }

    public static String IMEINumber(Context context) {
        //Have an  object of TelephonyManager
        TelephonyManager tm = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        //Get IMEI Number of Phone  //////////////// for this example i only need the IMEI
        String IMEINumber = tm.getDeviceId();

        /************************************************
         * **********************************************
         * This is just an icing on the cake
         * the following are other children of TELEPHONY_SERVICE
         *
         //Get Subscriber ID
         String subscriberID=tm.getDeviceId();

         //Get SIM Serial Number
         String SIMSerialNumber=tm.getSimSerialNumber();

         //Get Network Country ISO Code
         String networkCountryISO=tm.getNetworkCountryIso();

         //Get SIM Country ISO Code
         String SIMCountryISO=tm.getSimCountryIso();

         //Get the device software version
         String softwareVersion=tm.getDeviceSoftwareVersion()

         //Get the Voice mail number
         String voiceMailNumber=tm.getVoiceMailNumber();


         //Get the Phone Type CDMA/GSM/NONE
         int phoneType=tm.getPhoneType();

         switch (phoneType)
         {
         case (TelephonyManager.PHONE_TYPE_CDMA):
         // your code
         break;
         case (TelephonyManager.PHONE_TYPE_GSM)
         // your code
         break;
         case (TelephonyManager.PHONE_TYPE_NONE):
         // your code
         break;
         }

         //Find whether the Phone is in Roaming, returns true if in roaming
         boolean isRoaming=tm.isNetworkRoaming();
         if(isRoaming)
         phoneDetails+="\nIs In Roaming : "+"YES";
         else
         phoneDetails+="\nIs In Roaming : "+"NO";


         //Get the SIM state
         int SIMState=tm.getSimState();
         switch(SIMState)
         {
         case TelephonyManager.SIM_STATE_ABSENT :
         // your code
         break;
         case TelephonyManager.SIM_STATE_NETWORK_LOCKED :
         // your code
         break;
         case TelephonyManager.SIM_STATE_PIN_REQUIRED :
         // your code
         break;
         case TelephonyManager.SIM_STATE_PUK_REQUIRED :
         // your code
         break;
         case TelephonyManager.SIM_STATE_READY :
         // your code
         break;
         case TelephonyManager.SIM_STATE_UNKNOWN :
         // your code
         break;

         }
         */
        // Now read the desired content to a textview.
        return IMEINumber;
    }

}
