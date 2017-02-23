package com.hgil.siconprocess.utils.ui;

import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;

import com.hgil.siconprocess.activity.NavBaseActivity;

/**
 * Created by mohan.giri on 23-02-2017.
 */

public class SampleDialog {
    public SampleDialog(String title, String message, Context context) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(context);
        dialog.setCancelable(true);
        dialog.setTitle(title);
        dialog.setMessage(message);
        dialog.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                //Action for "Delete".
                dialog.dismiss();
            }
        });

        //this button is not needed
        /*dialog.setNegativeButton("Cancel ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Action for "Cancel".
            }
        });*/

        final AlertDialog alert = dialog.create();
        alert.show();
        alert.getButton(alert.BUTTON_POSITIVE).setAllCaps(false);
    }

    public SampleDialog(String title, String message, final boolean syncStatus, Context context) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(context);
        dialog.setCancelable(true);
        dialog.setTitle(title);
        dialog.setMessage(message);
        dialog.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                //Action for "Delete".
                if (syncStatus)
                    new NavBaseActivity().firstLaunch();
                dialog.dismiss();
            }
        });

        //this button is not needed
        /*dialog.setNegativeButton("Cancel ", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                //Action for "Cancel".
            }
        });*/

        final AlertDialog alert = dialog.create();
        alert.show();
        alert.getButton(alert.BUTTON_POSITIVE).setAllCaps(false);
    }
}
