package com.hgil.siconprocess_view.activity.fragments.baseLevel.depotList;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.hgil.siconprocess_view.R;
import com.hgil.siconprocess_view.adapter.depotList.DepotListAdapter;
import com.hgil.siconprocess_view.adapter.depotList.DepotModel;
import com.hgil.siconprocess_view.base.Base_Fragment;
import com.hgil.siconprocess_view.database.DemandTargetView;
import com.hgil.siconprocess_view.database.ItemDetailView;
import com.hgil.siconprocess_view.database.OutletView;
import com.hgil.siconprocess_view.database.RouteView;
import com.hgil.siconprocess_view.database.SaleHistoryView;
import com.hgil.siconprocess_view.database.TodaySaleView;
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

import java.util.ArrayList;

import butterknife.BindView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class DepotListFragment extends Base_Fragment {

    @BindView(R.id.rvDepotList)
    RecyclerView rvDepotList;
    @BindView(R.id.tvEmpty)
    TextView tvEmpty;

    private DepotListAdapter depotListAdapter;
    private RouteView routeView;
    private ArrayList<DepotModel> arrDepot = new ArrayList<>();
    /*sync process*/
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
    public DepotListFragment() {
        // Required empty public constructor
    }

    public static DepotListFragment newInstance() {
        DepotListFragment fragment = new DepotListFragment();
        return fragment;
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_depot_list;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        showSyncButton();
        setTitle("Depot List");

        initialiseDBObj();
        initializeListData();

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

    public void initializeListData() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvDepotList.setLayoutManager(linearLayoutManager);
        if (arrDepot != null)
            arrDepot.clear();
        routeView = new RouteView(getActivity());
        arrDepot.addAll(routeView.getDepotList());
        depotListAdapter = new DepotListAdapter(getActivity(), arrDepot);
        rvDepotList.setAdapter(depotListAdapter);
        onResume();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (arrDepot.size() == 0) {
            tvEmpty.setVisibility(View.VISIBLE);
            rvDepotList.setVisibility(View.GONE);
        } else {
            tvEmpty.setVisibility(View.GONE);
            rvDepotList.setVisibility(View.VISIBLE);
        }
    }

    private void initialiseDBObj() {
        dbRouteView = new RouteView(getContext());
        dbOutletView = new OutletView(getContext());
        dbDemandTargetView = new DemandTargetView(getContext());
        dbVanStock = new VanStockView(getContext());
        dbSaleHistory = new SaleHistoryView(getContext());

        // plan and remark update
        dbOutletRemark = new OutletRemarkTable(getContext());
        dbPlanTable = new PlannerTable(getContext());

        dbTodaySale = new TodaySaleView(getContext());
        dbItemDetail = new ItemDetailView(getContext());
    }

    private void eraseAllTableData() {
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
                    // erase all masterTables data
                    eraseAllTableData();

                    ObjLoginResponse objResponse = loginResult.getObjLoginResponse();

                    try {
                        // sync data to local table and views
                        dbRouteView.insertRoutes(objResponse.getArrRoutes());
                        dbOutletView.insertOutlet(objResponse.getArrOutlets());
                        dbDemandTargetView.insertDemandTarget(objResponse.getArrDemandTarget());
                        dbVanStock.insertVanStock(objResponse.getArrVanStock());
                        dbSaleHistory.insertSaleHistory(objResponse.getArrSaleHistory());
                        dbPlanTable.insertUserPlan(objResponse.getArrPlan());
                        dbOutletRemark.insertOutletRemark(objResponse.getArrRemark());

                        dbTodaySale.insertTodaySale(objResponse.getArrTodaySale());
                        dbItemDetail.insertItemInfo(objResponse.getArrItemDetail());
                        RetrofitUtil.hideDialog();
                        showSnackbar(getView(), getString(R.string.str_sync_complete));
                        initializeListData();
                    } catch (Exception e) {
                        RetrofitUtil.hideDialog();
                        new SampleDialog("", getString(R.string.str_error_sync_data), getContext());
                        initializeListData();
                    }
                } else {
                    RetrofitUtil.hideDialog();
                    new SampleDialog("", loginResult.getStrMessage(), getContext());
                }
            }

            @Override
            public void onFailure(Call<loginResponse> call, Throwable t) {
                RetrofitUtil.hideDialog();
                // show some error toast or message to display the api call issue
                new SampleDialog("", getString(R.string.str_retrofit_failure), getContext());
            }
        });
    }

}
