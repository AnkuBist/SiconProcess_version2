package com.hgil.siconprocess_view.activity.fragments.routeLevel.homeTabs;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.hgil.siconprocess_view.R;
import com.hgil.siconprocess_view.adapter.TabPagerAdapter;
import com.hgil.siconprocess_view.base.route_base.Route_Base_Fragment;
import com.hgil.siconprocess_view.database.DemandTargetView;
import com.hgil.siconprocess_view.database.OutletSaleView;
import com.hgil.siconprocess_view.database.OutletView;
import com.hgil.siconprocess_view.database.PaymentView;
import com.hgil.siconprocess_view.database.RouteView;
import com.hgil.siconprocess_view.database.SaleHistoryView;
import com.hgil.siconprocess_view.database.VanStockView;
import com.hgil.siconprocess_view.database.localDb.OutletRemarkTable;
import com.hgil.siconprocess_view.database.localDb.PlannerTable;
import com.hgil.siconprocess_view.retrofit.RetrofitService;
import com.hgil.siconprocess_view.retrofit.RetrofitUtil;
import com.hgil.siconprocess_view.retrofit.loginResponse.ObjLoginResponse;
import com.hgil.siconprocess_view.retrofit.loginResponse.SyncData;
import com.hgil.siconprocess_view.retrofit.loginResponse.loginResponse;
import com.hgil.siconprocess_view.utils.ui.SampleDialog;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class RouteHomeFragment extends Route_Base_Fragment implements TabLayout.OnTabSelectedListener {

    @BindView(R.id.tvRouteName)
    TextView tvRouteName;
    @BindView(R.id.pager)
    ViewPager viewPager;
    @BindView(R.id.tabLayout)
    TabLayout tabLayout;
    private TabPagerAdapter adapter;

    public RouteHomeFragment() {
        // Required empty public constructor
    }

    public static RouteHomeFragment newInstance() {
        RouteHomeFragment fragment = new RouteHomeFragment();
        return fragment;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Adding the tabs using addTab() method
        tabLayout.addTab(tabLayout.newTab().setText("All"));
        tabLayout.addTab(tabLayout.newTab().setText("Pending"));
        tabLayout.addTab(tabLayout.newTab().setText("Complete"));
        tabLayout.addTab(tabLayout.newTab().setText("Sale < 100"));
        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);

        //Creating our pager adapter
        adapter = new TabPagerAdapter(getChildFragmentManager(), tabLayout.getTabCount());

        //Adding adapter to pager
        viewPager.setAdapter(adapter);

        tabLayout.setupWithViewPager(viewPager);

        // set route name to the route
        tvRouteName.setText(getRouteName());

        setTitle("Route");
        showSyncButton();

        initialiseDBObj();

        imgSync.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // call sync data here
                SyncData syncData = new SyncData();
                syncData.setArrPlan(dbPlanTable.getAllPlan());
                syncData.setArrRemark(dbOutletRemark.getAllRemark());

                String json = new Gson().toJson(syncData);
                JSONObject jObj = null;
                try {
                    jObj = new JSONObject(json);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

                getUserLogin(getLoginId(), jObj);
            }
        });
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_route_home;
    }

    @Override
    public void onTabSelected(TabLayout.Tab tab) {
        //viewPager.setCurrentItem(tab.getPosition());
    }

    @Override
    public void onTabUnselected(TabLayout.Tab tab) {
    }

    @Override
    public void onTabReselected(TabLayout.Tab tab) {
    }

    /* sync process*/
    private RouteView dbRouteView;
    private OutletView dbOutletView;
    private DemandTargetView dbDemandTargetView;
    private OutletSaleView dbOutletSale;
    private VanStockView dbVanStock;
    private PaymentView dbPaymentView;
    private SaleHistoryView dbSaleHistory;

    // remark and plan updates
    private OutletRemarkTable dbOutletRemark;
    private PlannerTable dbPlanTable;

    /*sync process*/
    private void initialiseDBObj() {
        dbRouteView = new RouteView(getContext());
        dbOutletView = new OutletView(getContext());
        dbDemandTargetView = new DemandTargetView(getContext());
        dbVanStock = new VanStockView(getContext());
        dbOutletSale = new OutletSaleView(getContext());
        dbPaymentView = new PaymentView(getContext());
        dbSaleHistory = new SaleHistoryView(getContext());

        // plan and remark update
        dbOutletRemark = new OutletRemarkTable(getContext());
        dbPlanTable = new PlannerTable(getContext());
    }

    private void eraseAllTableData() {
        dbRouteView.eraseTable();
        dbOutletView.eraseTable();
        dbDemandTargetView.eraseTable();
        dbVanStock.eraseTable();
        dbOutletSale.eraseTable();
        dbPaymentView.eraseTable();
        dbSaleHistory.eraseTable();

        /*plan and remark table erase on login*/
        dbOutletRemark.eraseTable();
        dbPlanTable.eraseTable();
    }


    /*retrofit call test to fetch data from server*/
    public void getUserLogin(final String user_id, final JSONObject syncData) {

        RetrofitUtil.showDialog(getContext());
        RetrofitService service = RetrofitUtil.retrofitClient();
        Call<loginResponse> apiCall = service.syncRemarkPlan(user_id, syncData.toString());
        apiCall.enqueue(new Callback<loginResponse>() {
            @Override
            public void onResponse(Call<loginResponse> call, Response<loginResponse> response) {

                loginResponse loginResult = response.body();

                // rest call to read data from api service
                if (loginResult.getReturnCode()) {
                    new loginSync(loginResult, user_id).execute();
                } else {
                    RetrofitUtil.hideDialog();
                    new SampleDialog("", loginResult.getStrMessage(), getContext());
                }
            }

            @Override
            public void onFailure(Call<loginResponse> call, Throwable t) {
                RetrofitUtil.hideDialog();
                // show some error toast or message to display the api call issue
                new SampleDialog("", "Unable to access API", getContext());
            }
        });
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
            syncToLocal(loginResponse);
            return loginResponse.getReturnCode();
        }

        @Override
        protected void onPostExecute(Boolean status) {
            RetrofitUtil.hideDialog();
            if (status) {
                showSnackbar(getView(), "Data Sync Successfully");
            }

           /* if (false) {
                startActivity(new Intent(getContext(), LoginActivity.class));
                getActivity().finish();
                getActivity().overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_left);
            } else {
                new SampleDialog("", "Some error occurred while synchronising data", getContext());
            }*/
        }
    }

    // data processing and local db update
    private void syncToLocal(loginResponse loginResult) {
        // rest call to read data from api service
        if (loginResult.getReturnCode()) {
            // erase all masterTables data
            eraseAllTableData();

            ObjLoginResponse objResponse = loginResult.getObjLoginResponse();

            // sync data to local table and views
            dbRouteView.insertRoutes(objResponse.getArrRoutes());
            dbOutletView.insertOutlet(objResponse.getArrOutlets());
            dbDemandTargetView.insertDemandTarget(objResponse.getArrDemandTarget());
            dbOutletSale.insertOutletSale(objResponse.getArrOutletSale());
            dbVanStock.insertVanStock(objResponse.getArrVanStock());
            dbPaymentView.insertPayment(objResponse.getArrPayment());
            dbSaleHistory.insertSaleHistory(objResponse.getArrSaleHistory());
            dbPlanTable.insertUserPlan(objResponse.getArrPlan());
            dbOutletRemark.insertOutletRemark(objResponse.getArrRemark());
        }
    }
}
