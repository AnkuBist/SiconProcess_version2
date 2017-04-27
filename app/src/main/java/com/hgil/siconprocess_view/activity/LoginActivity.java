package com.hgil.siconprocess_view.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.design.widget.CoordinatorLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.hgil.siconprocess_view.R;
import com.hgil.siconprocess_view.activity.fragments.baseLevel.RouteListActivity;
import com.hgil.siconprocess_view.database.DemandTargetView;
import com.hgil.siconprocess_view.database.ItemDetailView;
import com.hgil.siconprocess_view.database.OutletView;
import com.hgil.siconprocess_view.database.RouteView;
import com.hgil.siconprocess_view.database.SHOutletSaleView;
import com.hgil.siconprocess_view.database.SHVanLoadingView;
import com.hgil.siconprocess_view.database.SaleHistoryView;
import com.hgil.siconprocess_view.database.TodaySaleView;
import com.hgil.siconprocess_view.database.VanStockView;
import com.hgil.siconprocess_view.database.ZoneView;
import com.hgil.siconprocess_view.database.localDb.OutletRemarkTable;
import com.hgil.siconprocess_view.database.localDb.PlannerTable;
import com.hgil.siconprocess_view.retrofit.RetrofitService;
import com.hgil.siconprocess_view.retrofit.RetrofitUtil;
import com.hgil.siconprocess_view.retrofit.loginResponse.ObjLoginResponse;
import com.hgil.siconprocess_view.retrofit.loginResponse.loginResponse;
import com.hgil.siconprocess_view.utils.Utility;
import com.hgil.siconprocess_view.utils.ui.SampleDialog;

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

    @BindView(R.id.tvAppVersion)
    TextView tvAppVersion;

    private ZoneView dbZoneView;
    private RouteView dbRouteView;
    private OutletView dbOutletView;
    private DemandTargetView dbDemandTargetView;
    private VanStockView dbVanStock;
    private SaleHistoryView dbSaleHistory;
    private TodaySaleView dbTodaySale;
    private ItemDetailView dbItemDetail;

    // remark and plan updates
    private OutletRemarkTable dbOutletRemark;
    private PlannerTable dbPlanTable;

    private SHVanLoadingView dbShVanLoadingView;
    private SHOutletSaleView dbShOutletSaleView;

    private String existing_id = "", saved_id = "";

    private Handler updateBarHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);

        // read shared preference if any
        saved_id = Utility.readPreference(this, Utility.USER_ID);
        existing_id = Utility.readPreference(this, Utility.LAST_LOGIN_ID);

        etUserId.setText(saved_id);

        tvAppVersion.setText("App Version: " + Utility.getAppVersion(this));

        /// initialise database objects
        initialiseDBObj();
        updateBarHandler = new Handler();

        //askAppPermission();
    }

    private void initialiseDBObj() {
        dbZoneView = new ZoneView(this);
        dbRouteView = new RouteView(this);
        dbOutletView = new OutletView(this);
        dbDemandTargetView = new DemandTargetView(this);
        dbVanStock = new VanStockView(this);
        dbSaleHistory = new SaleHistoryView(this);

        // plan and remark update
        dbOutletRemark = new OutletRemarkTable(this);
        dbPlanTable = new PlannerTable(this);

        dbTodaySale = new TodaySaleView(this);
        dbItemDetail = new ItemDetailView(this);

        dbShVanLoadingView = new SHVanLoadingView(this);
        dbShOutletSaleView = new SHOutletSaleView(this);
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
          /*  if (checkUserId(username)) {
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
            } else*/
            {

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
        dbZoneView.eraseTable();
        dbRouteView.eraseTable();
        dbOutletView.eraseTable();
        dbDemandTargetView.eraseTable();
        dbVanStock.eraseTable();
        dbSaleHistory.eraseTable();

        /*plan and remark table erase on login*/
        dbOutletRemark.eraseTable();
        dbPlanTable.eraseTable();

        dbItemDetail.eraseTable();
        dbTodaySale.eraseTable();

        dbShVanLoadingView.eraseTable();
        dbShOutletSaleView.eraseTable();
    }

    /*retrofit call test to fetch data from server*/
    public void getUserLogin(final String user_id, final String password) {
        updateBarHandler.post(new Runnable() {
            public void run() {
                RetrofitUtil.showDialog(LoginActivity.this, getString(R.string.str_login));
            }
        });

        RetrofitService service = RetrofitUtil.retrofitClient();
        Call<loginResponse> apiCall = service.postUserLogin(user_id, password);
        apiCall.enqueue(new Callback<loginResponse>() {
            @Override
            public void onResponse(Call<loginResponse> call, Response<loginResponse> response) {
                updateBarHandler.post(new Runnable() {
                    public void run() {
                        RetrofitUtil.updateDialogTitle(getString(R.string.str_login_detail_fetch));
                    }
                });

                final loginResponse loginResult = response.body();

                // rest call to read data from api service
                if (loginResult.getReturnCode()) {
                    // save user password for local login purpose
                    Utility.savePreference(LoginActivity.this, Utility.LAST_LOGIN_PASSWORD, password);
                    if (cbSignIn.isChecked()) {
                        // save the password for the next login too
                        Utility.savePreference(LoginActivity.this, Utility.USER_ID, user_id);
                    }

                    // erase all masterTables data
                    eraseAllTableData();

                    try {
                        new Thread(new Runnable() {
                            @Override
                            public void run() {

                        /*updateBarHandler.post(new Runnable() {
                            public void run() {
                                RetrofitUtil.updateDialogTitle("Writing Data To Local");//getString(R.string.str_login_detail_fetch));
                                */
                                ObjLoginResponse objResponse = loginResult.getObjLoginResponse();
                                final long startTime = System.currentTimeMillis();

                                // sync data to local table and views
                                dbZoneView.insertZone(objResponse.getArrZones());
                                dbRouteView.insertRoutes(objResponse.getArrRoutes());
                                dbOutletView.insertOutlet(objResponse.getArrOutlets());
                                dbDemandTargetView.insertDemandTarget(objResponse.getArrDemandTarget());
                                dbVanStock.insertVanStock(objResponse.getArrVanStock());
                                dbSaleHistory.insertSaleHistory(objResponse.getArrSaleHistory());
                                dbPlanTable.insertUserPlan(objResponse.getArrPlan());
                                dbOutletRemark.insertOutletRemark(objResponse.getArrRemark());

                                dbTodaySale.insertTodaySale(objResponse.getArrTodaySale());
                                dbItemDetail.insertItemInfo(objResponse.getArrItemDetail());

                                dbShVanLoadingView.insertSHRouteVanLoading(objResponse.getArrSHVanLoading());
                                dbShOutletSaleView.insertSHOutletSale(objResponse.getArrSHOutletSale());

                                final long endtime = System.currentTimeMillis();
                                Log.i("Total Time: ", String.valueOf(endtime - startTime));

                                Utility.saveLoginStatus(LoginActivity.this, Utility.LOGIN_STATUS, true);
                                Utility.savePreference(LoginActivity.this, Utility.LAST_LOGIN_ID, user_id);
                                Utility.savePreference(LoginActivity.this, Utility.LAST_LOGIN_DATE, Utility.getCurDate());
                            }
                        }).start();
                        updateBarHandler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                RetrofitUtil.hideDialog();
                            }
                        }, 500);
                        startActivity(new Intent(LoginActivity.this, RouteListActivity.class));
                        finish();
                        overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_left);
                    } catch (
                            Exception e
                            )

                    {
                        updateBarHandler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                RetrofitUtil.hideDialog();
                            }
                        }, 500);
                        new SampleDialog("", getString(R.string.str_error_login), LoginActivity.this);
                    }
                } else {
                    updateBarHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            RetrofitUtil.hideDialog();
                        }
                    }, 500);
                    new SampleDialog("", loginResult.getStrMessage(), LoginActivity.this);
                }
            }

            @Override
            public void onFailure(Call<loginResponse> call, Throwable t) {
                updateBarHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        RetrofitUtil.hideDialog();
                    }
                }, 500);
                // show some error toast or message to display the api call issue
                new SampleDialog("", getString(R.string.str_retrofit_failure), LoginActivity.this);
            }
        });
    }

   /* private void askAppPermission() {
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
    }*/

    // request permissions result
   /* @Override
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
    }*/

}
