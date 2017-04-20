package com.hgil.siconprocess_view.activity.fragments.baseLevel.routeList;


import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.hgil.siconprocess_view.R;
import com.hgil.siconprocess_view.adapter.routeList.RouteListAdapter;
import com.hgil.siconprocess_view.adapter.routeList.RouteListModel;
import com.hgil.siconprocess_view.base.Base_Fragment;
import com.hgil.siconprocess_view.database.DemandTargetView;
import com.hgil.siconprocess_view.database.OutletView;
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
import java.util.ArrayList;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class RouteListFragment extends Base_Fragment {

    private static String DEPOT_ID = "depot_id";
    private static String DEPOT_NAME = "depot_name";
    private String depot_id, depotName;

    @BindView(R.id.tvDepotName)
    TextView tvDepotName;
    @BindView(R.id.rvRouteList)
    RecyclerView rvRouteList;
    @BindView(R.id.tvEmpty)
    TextView tvEmpty;

    private RouteListAdapter routeListAdapter;
    private RouteView routeView;
    private ArrayList<RouteListModel> arrRoute;

    public RouteListFragment() {
        // Required empty public constructor
    }

    public static RouteListFragment newInstance(String depot_id, String depotName) {
        RouteListFragment fragment = new RouteListFragment();
        Bundle bundle = new Bundle();
        bundle.putString(DEPOT_ID, depot_id);
        bundle.putString(DEPOT_NAME, depotName);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            depot_id = getArguments().getString(DEPOT_ID);
            depotName = getArguments().getString(DEPOT_NAME);
        }
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_route_list;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvRouteList.setLayoutManager(linearLayoutManager);

        hideSyncButton();
        setTitle("Route List");

        if (depotName != null)
            tvDepotName.setText(depotName);

        initialiseDBObj();

        arrRoute = new ArrayList<>();
        routeView = new RouteView(getActivity());
        arrRoute.addAll(routeView.getDepotRouteList(depot_id));
        routeListAdapter = new RouteListAdapter(getActivity(), arrRoute);
        rvRouteList.setAdapter(routeListAdapter);

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
    public void onResume() {
        super.onResume();
        if (arrRoute.size() == 0) {
            tvEmpty.setVisibility(View.VISIBLE);
            rvRouteList.setVisibility(View.GONE);
        } else {
            tvEmpty.setVisibility(View.GONE);
            rvRouteList.setVisibility(View.VISIBLE);
        }
    }

    /*sync process*/
    private RouteView dbRouteView;
    private OutletView dbOutletView;
    private DemandTargetView dbDemandTargetView;
    //private OutletSaleView dbOutletSale;
    private VanStockView dbVanStock;
    //private PaymentView dbPaymentView;
    private SaleHistoryView dbSaleHistory;

    // remark and plan updates
    private OutletRemarkTable dbOutletRemark;
    private PlannerTable dbPlanTable;

    private void initialiseDBObj() {
        dbRouteView = new RouteView(getContext());
        dbOutletView = new OutletView(getContext());
        dbDemandTargetView = new DemandTargetView(getContext());
        dbVanStock = new VanStockView(getContext());
       // dbOutletSale = new OutletSaleView(getContext());
        //dbPaymentView = new PaymentView(getContext());
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
        //dbOutletSale.eraseTable();
        //dbPaymentView.eraseTable();
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
            //dbOutletSale.insertOutletSale(objResponse.getArrOutletSale());
            dbVanStock.insertVanStock(objResponse.getArrVanStock());
            //dbPaymentView.insertPayment(objResponse.getArrPayment());
            dbSaleHistory.insertSaleHistory(objResponse.getArrSaleHistory());
            dbPlanTable.insertUserPlan(objResponse.getArrPlan());
            dbOutletRemark.insertOutletRemark(objResponse.getArrRemark());
        }
    }

}
