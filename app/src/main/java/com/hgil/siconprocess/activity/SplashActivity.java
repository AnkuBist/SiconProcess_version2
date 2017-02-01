package com.hgil.siconprocess.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

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

                // direct pass user to home if user has already logged same day with any of the last saved id.
                if ((Utility.getCurDate()).matches(lastLoginDate))
                    startActivity(new Intent(SplashActivity.this, HomeActivity.class));
                else
                    startActivity(new Intent(SplashActivity.this, LoginActivity.class));

                // close this activity
                finish();
            }
        }, SPLASH_TIME_OUT);

    }
}
