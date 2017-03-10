package com.hgil.siconprocess.activity;

import android.Manifest;
import android.app.ProgressDialog;
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
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.hgil.siconprocess.R;
import com.hgil.siconprocess.database.masterTables.CrateCollectionView;
import com.hgil.siconprocess.database.masterTables.CrateOpeningTable;
import com.hgil.siconprocess.database.masterTables.CreditOpeningTable;
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
import com.hgil.siconprocess.utils.UtilNetworkLocation;
import com.hgil.siconprocess.utils.Utility;
import com.hgil.siconprocess.utils.ui.SampleDialog;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.ObjectOutputStream;
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

        // get user current location
        getUserLocation();
    }

    private void getUserLocation() {
        int MyVersion = Build.VERSION.SDK_INT;
        if (MyVersion > Build.VERSION_CODES.LOLLIPOP_MR1) {
            if (!checkIfAlreadyHavePermission()) {
                requestForSpecificPermission();
            } else {
                UtilNetworkLocation.fetchLocation(this);
            }
        }
        // for pre lolipop devices run this directly
        else {
            UtilNetworkLocation.fetchLocation(this);
        }
    }

    private void initialiseDBObj() {
        dbRouteView = new RouteView(this);
        dbRouteMapView = new CustomerRouteMappingView(this);
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
                Snackbar.make(coordinateLayout, "Please erase app data before login with different user!", Snackbar.LENGTH_LONG).show();
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
                RetrofitUtil.hideDialog();

                loginResponse loginResult = response.body();

                // rest call to read data from api service
                if (loginResult.getReturnCode()) {
                    // save user password for local login purpose
                    Utility.savePreference(LoginActivity.this, Utility.LAST_LOGIN_PASSWORD, password);

                    // sync data from server to local database using the downloaded data object
                    syncToLocal(loginResult, user_id);
                } else {
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
            dbCustomerItemPrice.insertCustomerItemPrice(routeData.getArrCustomerItemPrice());
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

            // start activity here only
            // after saving all values to database start new activity
            startActivity(new Intent(LoginActivity.this, NavBaseActivity.class));
            finish();
            overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_left);
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

    // request permission
    private void requestForSpecificPermission() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION, Manifest.permission.ACCESS_FINE_LOCATION}, 101);
    }

    // request permissions result
    @Override
    public void onRequestPermissionsResult(int requestCode, String permissions[], int[] grantResults) {
        switch (requestCode) {
            case 1: {
                // If request is cancelled, the result arrays are empty.
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted, yay! Do the
                    // contacts-related task you need to do.
                    UtilNetworkLocation.fetchLocation(this);
                } else {
                    // permission denied, boo! Disable the
                    // functionality that depends on this permission.
                    Toast.makeText(this, "Permission denied to get your location", Toast.LENGTH_SHORT).show();
                }
                return;
            }
            default:
                super.onRequestPermissionsResult(requestCode, permissions, grantResults);
                // other 'case' lines to check for other
                // permissions this app might request
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (UtilNetworkLocation.canGetLocation(this) == true && checkIfAlreadyHavePermission())
            UtilNetworkLocation.printCoordinates(UtilNetworkLocation.getLocation(this));
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
