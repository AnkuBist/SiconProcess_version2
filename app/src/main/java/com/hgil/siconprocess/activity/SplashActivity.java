package com.hgil.siconprocess.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.hgil.siconprocess.R;
import com.hgil.siconprocess.database.tables.CustomerRejectionTable;
import com.hgil.siconprocess.database.tables.InvoiceOutTable;
import com.hgil.siconprocess.database.tables.NextDayOrderTable;
import com.hgil.siconprocess.database.tables.PaymentTable;
import com.hgil.siconprocess.utils.Utility;

public class SplashActivity extends Activity {

    // Splash screen timer
    private static int SPLASH_TIME_OUT = 2000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity
                String lastLoginDate = Utility.readPreference(SplashActivity.this, Utility.LAST_LOGIN_DATE);
                boolean loginStatus = Utility.readLoginStatus(SplashActivity.this, Utility.LOGIN_STATUS);

                // check if there exists any relevant data to sync to server
                if (checkSyncData())
                    // sync data here before login
                    startActivity(new Intent(SplashActivity.this, LoginActivity.class));

                    // direct pass user to home if user has already logged same day with any of the last saved id.
                else if ((Utility.getCurDate()).matches(lastLoginDate) && loginStatus)
                    startActivity(new Intent(SplashActivity.this, NavBaseActivity.class));
                else
                    startActivity(new Intent(SplashActivity.this, LoginActivity.class));

                // close this activity
                finish();
                overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_left);

            }
        }, SPLASH_TIME_OUT);
    }

    private InvoiceOutTable invoiceOutTable;
    private CustomerRejectionTable rejectionTable;
    private PaymentTable paymentTable;
    private NextDayOrderTable nextDayOrderTable;

    public void initializeTableObjects() {
        invoiceOutTable = new InvoiceOutTable(this);
        rejectionTable = new CustomerRejectionTable(this);
        paymentTable = new PaymentTable(this);
        nextDayOrderTable = new NextDayOrderTable(this);
    }

    private boolean checkSyncData() {
        initializeTableObjects();
        if (invoiceOutTable.numberOfRows() > 0
                || rejectionTable.numberOfRows() > 0
                || paymentTable.numberOfRows() > 0
                || nextDayOrderTable.numberOfRows() > 0)
            return true;
        else return false;
    }
}
