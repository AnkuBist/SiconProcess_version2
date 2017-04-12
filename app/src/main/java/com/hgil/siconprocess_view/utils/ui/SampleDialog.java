package com.hgil.siconprocess_view.utils.ui;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;

import com.hgil.siconprocess_view.R;
import com.hgil.siconprocess_view.activity.OutletHomeActivity;
import com.hgil.siconprocess_view.activity.NavBaseActivity;

/**
 * Created by mohan.giri on 23-02-2017.
 */

public class SampleDialog {

    public SampleDialog() {
    }

    public SampleDialog(String message, Context context) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(context);
        dialog.setCancelable(true);
        //dialog.setTitle(title);
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

    public SampleDialog(String title, String message, final boolean syncStatus, final Context context) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(context);
        dialog.setCancelable(false);
        dialog.setTitle(title);
        dialog.setMessage(message);
        dialog.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                if (syncStatus) {
                    //new NavBaseActivity().firstLaunch();
                    // or start a simple activity to launch the first home fragment with
                    context.startActivity(new Intent(context, NavBaseActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                    ((NavBaseActivity) context).finish();
                    ((NavBaseActivity) context).overridePendingTransition(R.anim.anim_slide_out_right, R.anim.anim_slide_in_right);
                }
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

    public void SampleMessageDialog(String message, final Context context) {
        AlertDialog.Builder dialog = new AlertDialog.Builder(context);
        dialog.setCancelable(false);
        //dialog.setTitle(title);
        dialog.setMessage(message);
        dialog.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int id) {
                context.startActivity(new Intent(context, NavBaseActivity.class).addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK));
                ((OutletHomeActivity) context).finish();
                ((OutletHomeActivity) context).overridePendingTransition(R.anim.anim_slide_out_right, R.anim.anim_slide_in_right);

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
}
