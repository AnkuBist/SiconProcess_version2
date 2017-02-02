package com.hgil.siconprocess.activity;

import android.content.Intent;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.hgil.siconprocess.R;
import com.hgil.siconprocess.database.masterTables.CrateCollectionView;
import com.hgil.siconprocess.database.masterTables.CrateOpeningTable;
import com.hgil.siconprocess.database.masterTables.CreditOpeningTable;
import com.hgil.siconprocess.database.masterTables.CustomerRouteMappingView;
import com.hgil.siconprocess.database.masterTables.DemandTargetTable;
import com.hgil.siconprocess.database.masterTables.DepotEmployeeView;
import com.hgil.siconprocess.database.masterTables.DepotInvoiceView;
import com.hgil.siconprocess.database.masterTables.FixedSampleTable;
import com.hgil.siconprocess.database.masterTables.PriceGroupView;
import com.hgil.siconprocess.database.masterTables.RejectionTargetTable;
import com.hgil.siconprocess.database.masterTables.RouteView;
import com.hgil.siconprocess.retrofit.RetrofitService;
import com.hgil.siconprocess.retrofit.RetrofitUtil;
import com.hgil.siconprocess.retrofit.loginResponse.ObjLoginResponse;
import com.hgil.siconprocess.retrofit.loginResponse.dbModels.RouteModel;
import com.hgil.siconprocess.retrofit.loginResponse.loginResponse;
import com.hgil.siconprocess.utils.Utility;

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
    }

    private RouteView dbRouteView;
    private CustomerRouteMappingView dbRouteMapView;
    private PriceGroupView dbPriceGroup;
    private CreditOpeningTable dbCreditOpening;
    private CrateOpeningTable dbCrateOpening;
    private CrateCollectionView dbCrateCollection;
    private DepotInvoiceView dbInvoice;
    private DemandTargetTable dbDemandTarget;
    private FixedSampleTable dbFixedSample;
    private RejectionTargetTable dbRejectionTarget;
    private DepotEmployeeView dbEmployee;

    private void initialiseDBObj() {
        dbRouteView = new RouteView(this);
        dbRouteMapView = new CustomerRouteMappingView(this);
        dbPriceGroup = new PriceGroupView(this);
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
                // check for login
                getUserLogin(username, password);
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
        dbPriceGroup.eraseTable();
        dbCreditOpening.eraseTable();
        dbCrateOpening.eraseTable();
        dbCrateCollection.eraseTable();
        dbInvoice.eraseTable();
        dbDemandTarget.eraseTable();
        dbFixedSample.eraseTable();
        dbRejectionTarget.eraseTable();
        dbEmployee.eraseTable();
    }

    /*retrofit call test example*/
    public void getUserLogin(final String user_id, String password) {

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
                    if (cbSignIn.isChecked()) {
                        // save the password for the next login too
                        Utility.savePreference(LoginActivity.this, Utility.USER_ID, user_id);
                    }

                    // erase all masterTables data
                    eraseAllTableData();

                    ObjLoginResponse objResponse = loginResult.getObjLoginResponse();

                    // sync data to local table and views
                    dbRouteView.insertRoute(objResponse.getRouteDetail());

                    RouteModel routeData = objResponse.getRouteDetail();

                    dbRouteMapView.insertCustomerRouteMap(routeData.getArrCustomerRouteMap());
                    dbPriceGroup.insertPrice(routeData.getArrGroupPrice());
                    dbCreditOpening.insertCreditOpening(routeData.getArrCreditOpening());
                    dbCrateOpening.insertCrateOpening(routeData.getArrCrateOpening());
                    dbCrateCollection.insertCrateCollection(routeData.getArrCrateCollection());
                    dbInvoice.insertDepotInvoice(routeData.getArrInvoiceDetails());
                    dbDemandTarget.insertDemandTarget(routeData.getArrDemandTarget());
                    dbFixedSample.insertFixedSample(routeData.getArrFixedSample());
                    dbRejectionTarget.insertRejectionTarget(routeData.getArrRejectionTarget());
                    dbEmployee.insertDepotEmployee(routeData.getArrEmployees());

                    Utility.savePreference(LoginActivity.this, Utility.LAST_LOGIN_ID, user_id);
                    Utility.savePreference(LoginActivity.this, Utility.LAST_LOGIN_DATE, Utility.getCurDate());

                    // after saving all values to database start new activity
                    startActivity(new Intent(LoginActivity.this, NavBaseActivity.class));
                    finish();
                } else {
                    RetrofitUtil.showToast(LoginActivity.this, loginResult.getStrMessage());
                }
            }

            @Override
            public void onFailure(Call<loginResponse> call, Throwable t) {
                RetrofitUtil.hideDialog();
                // show some error toast or message to display the api call issue
                RetrofitUtil.showToast(LoginActivity.this, "Unable to access API");

            }
        });
    }
}
