package com.hgil.siconprocess.activity;

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

import com.hgil.siconprocess.R;
import com.hgil.siconprocess.database.masterTables.CrateCollectionView;
import com.hgil.siconprocess.database.masterTables.CrateOpeningTable;
import com.hgil.siconprocess.database.masterTables.CreditOpeningTable;
import com.hgil.siconprocess.database.masterTables.CustomerInfoView;
import com.hgil.siconprocess.database.masterTables.CustomerItemPriceTable;
import com.hgil.siconprocess.database.masterTables.CustomerRouteMappingView;
import com.hgil.siconprocess.database.masterTables.DemandTargetTable;
import com.hgil.siconprocess.database.masterTables.DepotEmployeeView;
import com.hgil.siconprocess.database.masterTables.DepotInvoiceView;
import com.hgil.siconprocess.database.masterTables.FixedSampleTable;
import com.hgil.siconprocess.database.masterTables.PriceGroupView;
import com.hgil.siconprocess.database.masterTables.ProductView;
import com.hgil.siconprocess.database.masterTables.RejectionTargetTable;
import com.hgil.siconprocess.database.masterTables.RouteView;
import com.hgil.siconprocess.database.tables.CustomerRejectionTable;
import com.hgil.siconprocess.database.tables.InvoiceOutTable;
import com.hgil.siconprocess.database.tables.NextDayOrderTable;
import com.hgil.siconprocess.database.tables.PaymentTable;
import com.hgil.siconprocess.retrofit.RetrofitService;
import com.hgil.siconprocess.retrofit.RetrofitUtil;
import com.hgil.siconprocess.retrofit.loginResponse.ObjLoginResponse;
import com.hgil.siconprocess.retrofit.loginResponse.dbModels.RouteModel;
import com.hgil.siconprocess.retrofit.loginResponse.loginResponse;
import com.hgil.siconprocess.utils.Utility;
import com.hgil.siconprocess.utils.ui.SampleDialog;

import java.io.Serializable;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity {

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
    private CustomerRouteMappingView dbRouteMapView;
    private CustomerInfoView dbCustomerInfoView;
    private CustomerItemPriceTable dbCustomerItemPrice;
    private PriceGroupView dbPriceGroup;
    private ProductView dbProductView;
    private CreditOpeningTable dbCreditOpening;
    private CrateOpeningTable dbCrateOpening;
    private CrateCollectionView dbCrateCollection;
    private DepotInvoiceView dbInvoice;
    private DemandTargetTable dbDemandTarget;
    private FixedSampleTable dbFixedSample;
    private RejectionTargetTable dbRejectionTarget;
    private DepotEmployeeView dbEmployee;

    // sync table objects
    private InvoiceOutTable invoiceOutTable;
    private CustomerRejectionTable rejectionTable;
    private PaymentTable paymentTable;
    private NextDayOrderTable nextDayOrderTable;

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
        initializeSyncDbObj();

        /*// get user current location
        checkAndroidVersionForLocationAccess(this);

        // get device imei number
        checkAndroidVersionForPhoneState(this);

        // get permission to send sms
        askSmsPermission(this);*/

        // ask all required permission at once only
        askAppPermission();
    }

    private void initialiseDBObj() {
        dbRouteView = new RouteView(this);
        dbRouteMapView = new CustomerRouteMappingView(this);
        dbCustomerInfoView = new CustomerInfoView(this);
        dbCustomerItemPrice = new CustomerItemPriceTable(this);
        dbPriceGroup = new PriceGroupView(this);
        dbProductView = new ProductView(this);
        dbCreditOpening = new CreditOpeningTable(this);
        dbCrateOpening = new CrateOpeningTable(this);
        dbCrateCollection = new CrateCollectionView(this);
        dbInvoice = new DepotInvoiceView(this);
        dbDemandTarget = new DemandTargetTable(this);
        dbFixedSample = new FixedSampleTable(this);
        dbRejectionTarget = new RejectionTargetTable(this);
        dbEmployee = new DepotEmployeeView(this);
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
            if (checkUserId(username)) {
                // if the user is logged in today but somehow logged out then check for the last logged in date
                // and local data in invoice if there exists any data then simply make user in
                if ((Utility.getCurDate()).matches(Utility.readPreference(LoginActivity.this, Utility.LAST_LOGIN_DATE))) {
                    //match here user saved username and password
                    String last_login_id = Utility.readPreference(LoginActivity.this, Utility.LAST_LOGIN_ID);
                    String last_login_password = Utility.readPreference(LoginActivity.this, Utility.LAST_LOGIN_PASSWORD);
                    if (username.matches(last_login_id) &&
                            password.matches(last_login_password)) {
                        startActivity(new Intent(LoginActivity.this, NavBaseActivity.class));
                        finish();
                        overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_left);
                    } else {
                        Snackbar.make(coordinateLayout, "Username and password combination wrong.", Snackbar.LENGTH_LONG).show();
                    }
                } else {
                    // check for login
                    getUserLogin(username, password);
                }
            } else {

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
        dbRouteMapView.eraseTable();
        dbCustomerInfoView.eraseTable();
        dbCustomerItemPrice.eraseTable();
        dbPriceGroup.eraseTable();
        dbProductView.eraseTable();
        dbCreditOpening.eraseTable();
        dbCrateOpening.eraseTable();
        dbCrateCollection.eraseTable();
        dbInvoice.eraseTable();
        dbDemandTarget.eraseTable();
        dbFixedSample.eraseTable();
        dbRejectionTarget.eraseTable();
        dbEmployee.eraseTable();
    }

    public void initializeSyncDbObj() {
        invoiceOutTable = new InvoiceOutTable(this);
        rejectionTable = new CustomerRejectionTable(this);
        paymentTable = new PaymentTable(this);
        nextDayOrderTable = new NextDayOrderTable(this);
    }

    public void eraseAllSyncTables() {
        rejectionTable.eraseTable();
        invoiceOutTable.eraseTable();
        paymentTable.eraseTable();
        nextDayOrderTable.eraseTable();
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

                    // sync data from server to local database using the downloaded data object
                    // syncToLocal(loginResult, user_id);

                    new loginSync(loginResult, user_id).execute();
                    //RetrofitUtil.hideDialog();
                } else {
                    RetrofitUtil.hideDialog();

                    //RetrofitUtil.showToast(LoginActivity.this, loginResult.getStrMessage());
                    new SampleDialog("", loginResult.getStrMessage(), LoginActivity.this);
                }
            }

            @Override
            public void onFailure(Call<loginResponse> call, Throwable t) {
                RetrofitUtil.hideDialog();
                // show some error toast or message to display the api call issue
                //RetrofitUtil.showToast(LoginActivity.this, "Unable to access API");
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

            // erase table to sync
            //do not erase these sync tables if the last login by the user in the same date in case it will erase all data
            if (!Utility.getCurDate().matches(Utility.readPreference(LoginActivity.this, Utility.LAST_LOGIN_DATE)))
                eraseAllSyncTables();

            ObjLoginResponse objResponse = loginResult.getObjLoginResponse();

            // sync data to local table and views
            dbRouteView.insertRoute(objResponse.getRouteDetail());

            RouteModel routeData = objResponse.getRouteDetail();

            dbRouteMapView.insertCustomerRouteMap(routeData.getArrCustomerRouteMap());
            dbCustomerInfoView.insertCustomer(routeData.getArrRouteCustomerInfo());
            dbCustomerItemPrice.insertCustomerItemPrice(routeData.getArrItemDiscountPrice());
            dbPriceGroup.insertPrice(routeData.getArrGroupPrice());
            dbCreditOpening.insertCreditOpening(routeData.getArrCreditOpening());
            dbCrateOpening.insertCrateOpening(routeData.getArrCrateOpening());
            dbCrateCollection.insertCrateCollection(routeData.getArrCrateCollection());
            dbInvoice.insertDepotInvoice(routeData.getArrInvoiceDetails());
            dbDemandTarget.insertDemandTarget(routeData.getArrDemandTarget());
            dbFixedSample.insertFixedSample(routeData.getArrFixedSample());
            dbRejectionTarget.insertRejectionTarget(routeData.getArrRejectionTarget());
            dbEmployee.insertDepotEmployee(routeData.getArrEmployees());

            Utility.saveLoginStatus(LoginActivity.this, Utility.LOGIN_STATUS, true);
            Utility.savePreference(LoginActivity.this, Utility.LAST_LOGIN_ID, user_id);
            Utility.savePreference(LoginActivity.this, Utility.LAST_LOGIN_DATE, Utility.getCurDate());
        }
    }

    // check permission
    private boolean checkIfAlreadyHavePermission() {
        int result_COARSE_LOCATION = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION);
        int result_FINE_LOCATION = ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION);
        if ((result_COARSE_LOCATION == PackageManager.PERMISSION_GRANTED) && (result_FINE_LOCATION == PackageManager.PERMISSION_GRANTED)) {
            return true;
        } else {
            return false;
        }
    }

    private static final int APP_PERMISSION = 105;

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

                    /*if (grantResults[0] != PackageManager.PERMISSION_GRANTED)
                        Toast.makeText(this, "ACCESS_COARSE_LOCATION & ACCESS_FINE_LOCATION Permission denied", Toast.LENGTH_SHORT).show();
                    *//*if (grantResults[1] != PackageManager.PERMISSION_GRANTED)
                        Toast.makeText(this, "ACCESS_FINE_LOCATION Permission denied", Toast.LENGTH_SHORT).show();*//*
                    if (grantResults[1] != PackageManager.PERMISSION_GRANTED)
                        Toast.makeText(this, "READ_PHONE_STATE Permission denied", Toast.LENGTH_SHORT).show();
                    if (grantResults[2] != PackageManager.PERMISSION_GRANTED)
                        Toast.makeText(this, "SEND_SMS Permission denied", Toast.LENGTH_SHORT).show();*/
                }
                return;

            /*case ACCESS_LOCATION:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    UtilNetworkLocation.fetchLocation(this);
                else
                    Toast.makeText(this, "Permission denied to get your location", Toast.LENGTH_SHORT).show();
                return;
            case READ_PHONE_STATE:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
                    UtilNetworkLocation.fetchLocation(this);
                else
                    Toast.makeText(this, "Permission denied to read phone state", Toast.LENGTH_SHORT).show();
                return;
            case SEND_SMS:
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    //UtilNetworkLocation.fetchLocation(this);
                } else
                    Toast.makeText(this, "Permission denied to send SMS", Toast.LENGTH_SHORT).show();
                return;*/
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

   /* @Override
    protected void onResume() {
        super.onResume();
        if (UtilNetworkLocation.canGetLocation(this) == true && checkIfAlreadyHavePermission())

            UtilNetworkLocation.printCoordinates(UtilNetworkLocation.getLocation(this));
    }*/

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
                startActivity(new Intent(LoginActivity.this, NavBaseActivity.class));
                finish();
                overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_left);
            } else {
                new SampleDialog("", "Some error occurred while synchronising data", LoginActivity.this);
            }
        }
    }


    // customized async task with progress dialog
   /* private class loginSync extends AsyncTask<loginResponse, Integer, loginResponse> implements Serializable {
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            // showDialog(progress_bar_type);
            progressDialog = new ProgressDialog(LoginActivity.this);
            progressDialog.setCancelable(false);
            //  dialog.setCanceledOnTouchOutside(false);
            progressDialog.setIndeterminate(false);
            //  progressDialog.setMax(100);
            progressDialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
            progressDialog.setProgress(0);
            progressDialog.setMax(100);
            progressDialog.setMessage("Loading ...");
            progressDialog.show();
            //  ProgressBar progressBar = (ProgressBar)findViewById(R.id.progressBar2);
        }

        @Override
        protected loginResponse doInBackground(loginResponse... params) {
            try {
                int count = 0;
                byte[] payload = params.toString().getBytes("UTF-8");
                int totalSze = payload.length;
                Log.e("Total size ", "" + totalSze);
                int bytesTransferred = 0;
                int chunkSize = (2 * totalSze) / 100;
                boolean last_loop = false;
                publishProgress(0);


                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                ObjectOutputStream oos = new ObjectOutputStream(baos);


                oos.writeObject(params[0]);

                oos.flush();
                oos.close();

                InputStream is = new ByteArrayInputStream(baos.toByteArray());

                byte data[] = new byte[1024];
                long total = 0;
                while ((count = is.read(data)) != -1) {
                    total += count;
                    // publishing the progress....
                    // After this onProgressUpdate will be called
                    publishProgress((int) ((total * 100) / totalSze));
                }

                return params[0];
            } catch (Exception e) {
                Log.e(this.getClass().getName(), "doInBackground: " + e.getMessage());
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            //  Log.e("dfsf",""+values[0]);
            progressDialog.setProgress(values[0]);
            //   progressDialog.setProgress(values[0]);
        }

        @Override
        protected void onPostExecute(loginResponse result) {
            //if (HttpResultimage == 204) {
            //TODO
            //do here the server task
            if (result.getReturnCode()) {
                syncToLocal(result, USER_ID);
            }
            progressDialog.dismiss();
            //}

            if (result.getReturnCode()) {
                // after saving all values to database start new activity
                startActivity(new Intent(LoginActivity.this, NavBaseActivity.class));
                finish();
                overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_left);
            }
        }
    }*/

}
