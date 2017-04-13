package com.hgil.siconprocess_view.activity;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.hgil.siconprocess_view.R;
import com.hgil.siconprocess_view.activity.base_frame.RouteListActivity;
import com.hgil.siconprocess_view.database.DemandTargetView;
import com.hgil.siconprocess_view.database.OutletSaleView;
import com.hgil.siconprocess_view.database.OutletView;
import com.hgil.siconprocess_view.database.RouteView;
import com.hgil.siconprocess_view.database.VanStockView;
import com.hgil.siconprocess_view.retrofit.RetrofitService;
import com.hgil.siconprocess_view.retrofit.RetrofitUtil;
import com.hgil.siconprocess_view.retrofit.loginResponse.ObjLoginResponse;
import com.hgil.siconprocess_view.retrofit.loginResponse.loginResponse;
import com.hgil.siconprocess_view.utils.Utility;
import com.hgil.siconprocess_view.utils.ui.SampleDialog;

import java.io.Serializable;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

    private static final int APP_PERMISSION = 105;

    @BindView(R.id.etUserId)
    EditText etUserId;
    @BindView(R.id.etPassword)
    EditText etPassword;
    @BindView(R.id.cbSignIn)
    CheckBox cbSignIn;
    @BindView(R.id.btnSubmit)
    Button btnSubmit;
    @BindView(R.id.coordinateLayout)
    CoordinatorLayout coordinateLayout;

    private RouteView dbRouteView;
    private OutletView dbOutletView;
    private DemandTargetView dbDemandTargetView;
    private OutletSaleView dbOutletSale;
    private VanStockView dbVanStock;


    private String existing_id = "", saved_id = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        // read shared preference if any
        saved_id = Utility.readPreference(this, Utility.USER_ID);
        existing_id = Utility.readPreference(this, Utility.LAST_LOGIN_ID);

        etUserId.setText(saved_id);

        /// initialise database objects
        initialiseDBObj();

        askAppPermission();
    }

    private void initialiseDBObj() {
        dbRouteView = new RouteView(this);
        dbOutletView = new OutletView(this);
        dbDemandTargetView = new DemandTargetView(this);
        dbVanStock = new VanStockView(this);
        dbOutletSale = new OutletSaleView(this);
    }

    public void onSubmit(View view) {
        String username = etUserId.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        if (username.isEmpty()) {
            etUserId.requestFocus();
            Toast.makeText(this, "Enter valid user ID", Toast.LENGTH_SHORT).show();
        } else if (password.isEmpty()) {
            etPassword.requestFocus();
            Toast.makeText(this, "Enter a valid password", Toast.LENGTH_SHORT).show();
        } else {
            // first check user login with the same existing id or not
           /* if (checkUserId(username)) {
                // if the user is logged in today but somehow logged out then check for the last logged in date
                // and local data in invoice if there exists any data then simply make user in
                if ((Utility.getCurDate()).matches(Utility.readPreference(LoginActivity.this, Utility.LAST_LOGIN_DATE))) {
                    //match here user saved username and password
                    String last_login_id = Utility.readPreference(LoginActivity.this, Utility.LAST_LOGIN_ID);
                    String last_login_password = Utility.readPreference(LoginActivity.this, Utility.LAST_LOGIN_PASSWORD);
                    if (username.matches(last_login_id) &&
                            password.matches(last_login_password)) {
                        startActivity(new Intent(LoginActivity.this, RouteListActivity.class));
                        finish();
                        overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_left);
                    } else {
                        Snackbar.make(coordinateLayout, "Username and password combination wrong.", Snackbar.LENGTH_LONG).show();
                    }
                } else {
                    // check for login
                    getUserLogin(username, password);
                }
            } else*/ {

                //TODO--- this is a test code remove these lines and uncomment the below code after this
                // check for login
                getUserLogin(username, password);

                //Snackbar.make(coordinateLayout, "Please erase app data before login with different user!", Snackbar.LENGTH_LONG).show();
            }
        }
    }

    private boolean checkUserId(String user_id) {
        if (existing_id.matches("")) {
            return true;
        } else if (!existing_id.matches("")) {
            if (existing_id.matches(user_id))
                return true;
            else
                return false;
        } else {
            return false;
        }
    }

    private void eraseAllTableData() {
        dbRouteView.eraseTable();
        dbOutletView.eraseTable();
        dbDemandTargetView.eraseTable();
        dbVanStock.eraseTable();
        dbOutletSale.eraseTable();
    }

    /*retrofit call test to fetch data from server*/
    public void getUserLogin(final String user_id, final String password) {

        RetrofitUtil.showDialog(this);
        RetrofitService service = RetrofitUtil.retrofitClient();
        Call<loginResponse> apiCall = service.postUserLogin(user_id, password);
        apiCall.enqueue(new Callback<loginResponse>() {
            @Override
            public void onResponse(Call<loginResponse> call, Response<loginResponse> response) {

                loginResponse loginResult = response.body();

                // rest call to read data from api service
                if (loginResult.getReturnCode()) {
                    // save user password for local login purpose
                    Utility.savePreference(LoginActivity.this, Utility.LAST_LOGIN_PASSWORD, password);
                    new loginSync(loginResult, user_id).execute();
                } else {
                    RetrofitUtil.hideDialog();
                    new SampleDialog("", loginResult.getStrMessage(), LoginActivity.this);
                }
            }

            @Override
            public void onFailure(Call<loginResponse> call, Throwable t) {
                RetrofitUtil.hideDialog();
                // show some error toast or message to display the api call issue
                new SampleDialog("", "Unable to access API", LoginActivity.this);
            }
        });
    }

    // data processing and local db update
    private void syncToLocal(loginResponse loginResult, String user_id) {
        // rest call to read data from api service
        if (loginResult.getReturnCode()) {
            if (cbSignIn.isChecked()) {
                // save the password for the next login too
                Utility.savePreference(LoginActivity.this, Utility.USER_ID, user_id);
            }

            // erase all masterTables data
            eraseAllTableData();

            ObjLoginResponse objResponse = loginResult.getObjLoginResponse();

            // sync data to local table and views
            dbRouteView.insertRoutes(objResponse.getArrRoutes());
            dbOutletView.insertOutlet(objResponse.getArrOutlets());
            dbDemandTargetView.insertDemandTarget(objResponse.getArrDemandTarget());
            dbOutletSale.insertOutletSale(objResponse.getArrOutletSale());
            dbVanStock.insertVanStock(objResponse.getArrVanStock());

            Utility.saveLoginStatus(LoginActivity.this, Utility.LOGIN_STATUS, true);
            Utility.savePreference(LoginActivity.this, Utility.LAST_LOGIN_ID, user_id);
            Utility.savePreference(LoginActivity.this, Utility.LAST_LOGIN_DATE, Utility.getCurDate());
        }
    }

    private void askAppPermission() {
        if (Build.VERSION.SDK_INT >= 23) {
            int check_COARSE_LOCATION = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION);
            int check_FINE_LOCATION = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
            int check_READ_PHONE_STATE = ContextCompat.checkSelfPermission(this, Manifest.permission.READ_PHONE_STATE);
            int check_SEND_SMS = ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS);

            if (check_COARSE_LOCATION != PackageManager.PERMISSION_GRANTED ||
                    check_FINE_LOCATION != PackageManager.PERMISSION_GRANTED ||
                    check_READ_PHONE_STATE != PackageManager.PERMISSION_GRANTED ||
                    check_SEND_SMS != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, new String[]{
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.READ_PHONE_STATE,
                        Manifest.permission.SEND_SMS}, APP_PERMISSION);
                return;
            }
        }
    }

    // request permissions result
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case APP_PERMISSION:
                if (grantResults.length > 0) {
                    for (int i = 0; i < grantResults.length; i++) {
                        if (grantResults[i] != PackageManager.PERMISSION_GRANTED)
                            Toast.makeText(this, permissions[i] + " Permission denied", Toast.LENGTH_SHORT).show();
                    }
                }
                return;
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    // AsyncTask copy one
    private class loginSync extends AsyncTask<Void, Void, Boolean> implements Serializable {

        loginResponse loginResponse;
        String user_id;

        public loginSync(loginResponse loginResponse, String user_id) {
            this.loginResponse = loginResponse;
            this.user_id = user_id;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            syncToLocal(loginResponse, user_id);
            return loginResponse.getReturnCode();
        }

        @Override
        protected void onPostExecute(Boolean status) {
            RetrofitUtil.hideDialog();
            if (status) {
                startActivity(new Intent(LoginActivity.this, RouteListActivity.class));
                finish();
                overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_left);
            } else {
                new SampleDialog("", "Some error occurred while synchronising data", LoginActivity.this);
            }
        }
    }

}
