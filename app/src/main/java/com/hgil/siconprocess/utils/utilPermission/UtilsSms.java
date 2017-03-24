package com.hgil.siconprocess.utils.utilPermission;

import android.Manifest;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Build;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.telephony.SmsManager;

import com.hgil.siconprocess.utils.ui.SampleDialog;


/**
 * Created by mohan.giri on 18-03-2017.
 */

public class UtilsSms {

    public static final int SEND_SMS = 102;

    public static void sendSMS(Context context, String phoneNumber, String message) {
        // Intent sendIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("sms:" + phoneNumber));
       /* Intent sendIntent = new Intent(Intent.ACTION_SENDTO, Uri.fromParts("smsto", phoneNumber, null));
        sendIntent.putExtra("sms_body", message);
        context.startActivity(sendIntent);*/
        try {
            PendingIntent pi = PendingIntent.getActivity(context, 0, new Intent(), 0);
            SmsManager sms = SmsManager.getDefault();
            sms.sendTextMessage(phoneNumber, null, message, pi, null);

            new SampleDialog("Message sent to " + phoneNumber, context);
        } catch (Exception e) {
            //Toast.makeText(context, "Message not sent", Toast.LENGTH_LONG).show();
            new SampleDialog("Message not sent", context);
        }
    }

    /*check sms permission before sending sms*/
    // simple trick to check and ask permission
    public static void checkAndroidVersionForSms(Context context, String mobile, String message) {
        if (Build.VERSION.SDK_INT >= 23) {
            int checkSMSPermission = ContextCompat.checkSelfPermission(context, Manifest.permission.SEND_SMS);
            if (checkSMSPermission != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.SEND_SMS}, SEND_SMS);
                return;
            } else {
                sendSMS(context, mobile, message);
            }
        } else {
            sendSMS(context, mobile, message);
        }
    }

    public static void askSmsPermission(Context context) {
        if (Build.VERSION.SDK_INT >= 23) {
            int checkSMSPermission = ContextCompat.checkSelfPermission(context, Manifest.permission.SEND_SMS);
            if (checkSMSPermission != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions((Activity) context, new String[]{Manifest.permission.SEND_SMS}, SEND_SMS);
                return;
            } else {
                //sendSMS(context, mobile, message);
            }
        } else {
            //sendSMS(context, mobile, message);
        }
    }
}
