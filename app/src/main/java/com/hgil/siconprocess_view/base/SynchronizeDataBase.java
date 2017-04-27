package com.hgil.siconprocess_view.base;

import android.os.Handler;
import android.support.v4.app.Fragment;

import com.google.gson.Gson;
import com.hgil.siconprocess_view.R;
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
import com.hgil.siconprocess_view.retrofit.loginResponse.SyncData;
import com.hgil.siconprocess_view.retrofit.loginResponse.loginResponse;
import com.hgil.siconprocess_view.utils.ui.SampleDialog;
import com.hgil.siconprocess_view.utils.ui.SnackbarUtil;

import org.json.JSONException;
import org.json.JSONObject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * Created by mohan.giri on 25-04-2017.
 */

public class SynchronizeDataBase extends Fragment {

    /*sync process*/
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

    public SynchronizeDataBase() {
    }

    public void startSyncData(final String login_id) {
        initialiseDBObj();
        updateBarHandler = new Handler();
        updateBarHandler.post(new Runnable() {
            public void run() {
                RetrofitUtil.showDialog(getContext(), getString(R.string.str_synchronizing_data));
            }
        });
        // call sync data here
        new Thread(new Runnable() {
            @Override
            public void run() {
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
                synchronizedDataResponse(login_id, jObj);
            }
        }).start();
    }

    private Handler updateBarHandler;

    private void initialiseDBObj() {
        dbZoneView = new ZoneView(getContext());
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

        dbShVanLoadingView = new SHVanLoadingView(getContext());
        dbShOutletSaleView = new SHOutletSaleView(getContext());
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
    public void synchronizedDataResponse(final String user_id, final JSONObject syncData) {
        updateBarHandler.post(new Runnable() {
            public void run() {
                RetrofitUtil.updateDialogTitle(getString(R.string.str_synchronizing_data));
            }
        });
        RetrofitService service = RetrofitUtil.retrofitClient();
        Call<loginResponse> apiCall = service.syncRemarkPlan(user_id, syncData.toString());
        apiCall.enqueue(new Callback<loginResponse>() {
            @Override
            public void onResponse(Call<loginResponse> call, Response<loginResponse> response) {
                updateBarHandler.post(new Runnable() {
                    public void run() {
                        RetrofitUtil.updateDialogTitle(getString(R.string.str_synchronizing_data_started));
                    }
                });

                final loginResponse loginResult = response.body();

                // rest call to read data from api service
                if (loginResult.getReturnCode()) {
                    // erase all masterTables data
                    eraseAllTableData();

                    try {
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                ObjLoginResponse objResponse = loginResult.getObjLoginResponse();

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
                            }
                        }).start();
                        updateBarHandler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                RetrofitUtil.hideDialog();
                            }
                        }, 500);
                        SnackbarUtil.showSnackbar(getView(), getString(R.string.str_sync_complete));
                    } catch (Exception e) {
                        updateBarHandler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                RetrofitUtil.hideDialog();
                            }
                        }, 500);
                        new SampleDialog("", getString(R.string.str_error_sync_data), getContext());
                    }
                } else {
                    updateBarHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            RetrofitUtil.hideDialog();
                        }
                    }, 500);
                    new SampleDialog("", loginResult.getStrMessage(), getContext());
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
                new SampleDialog("", getString(R.string.str_retrofit_failure), getContext());
            }
        });
    }
}
