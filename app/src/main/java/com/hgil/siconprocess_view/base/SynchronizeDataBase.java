package com.hgil.siconprocess_view.base;

import android.os.AsyncTask;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.util.Log;

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
import com.hgil.siconprocess_view.database.localDb.RouteRemarkTable;
import com.hgil.siconprocess_view.retrofit.RetrofitService;
import com.hgil.siconprocess_view.retrofit.RetrofitUtil;
import com.hgil.siconprocess_view.retrofit.loginResponse.ObjLoginResponse;
import com.hgil.siconprocess_view.retrofit.loginResponse.SyncData;
import com.hgil.siconprocess_view.retrofit.loginResponse.loginResponse;
import com.hgil.siconprocess_view.utils.Utility;
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
    private RouteRemarkTable dbRouteRemark;
    private OutletRemarkTable dbOutletRemark;
    private PlannerTable dbPlanTable;

    private SHVanLoadingView dbShVanLoadingView;
    private SHOutletSaleView dbShOutletSaleView;

    private String last_sync_date = null;
    private String imei_number = null;
    private Handler updateBarHandler;

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
        last_sync_date = Utility.readPreference(getContext(), Utility.LAST_SYNC_DATE);
        imei_number = Utility.readPreference(getContext(), Utility.DEVICE_IMEI);

        // call sync data here
        new Thread(new Runnable() {
            @Override
            public void run() {
                SyncData syncData = new SyncData();
                syncData.setArrPlan(dbPlanTable.getAllPlan());
                syncData.setArrRouteRemark(dbRouteRemark.getAllRouteRemark());
                syncData.setArrRemark(dbOutletRemark.getAllRemark());

                String json = new Gson().toJson(syncData);
                JSONObject jObj = null;
                try {
                    jObj = new JSONObject(json);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                synchronizedDataResponse(login_id, last_sync_date, imei_number, jObj);
            }
        }).start();
    }

    private void initialiseDBObj() {
        dbZoneView = new ZoneView(getContext());
        dbRouteView = new RouteView(getContext());
        dbOutletView = new OutletView(getContext());
        dbDemandTargetView = new DemandTargetView(getContext());
        dbVanStock = new VanStockView(getContext());
        dbSaleHistory = new SaleHistoryView(getContext());

        // plan and remark update
        dbRouteRemark = new RouteRemarkTable(getContext());
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
        dbRouteRemark.eraseTable();
        dbOutletRemark.eraseTable();
        dbPlanTable.eraseTable();

        dbItemDetail.eraseTable();
        dbTodaySale.eraseTable();

        dbShVanLoadingView.eraseTable();
        dbShOutletSaleView.eraseTable();
    }

    private void clearSyncOnlyData() {
        dbRouteView.eraseTable();
        dbVanStock.eraseTable();
        dbOutletView.eraseTable();
        dbDemandTargetView.eraseTable();
        dbTodaySale.eraseTable();
    }

    /*retrofit call test to fetch data from server*/
    public void synchronizedDataResponse(final String user_id, final String last_sync_date, String imei_number, final JSONObject syncData) {
        updateBarHandler.post(new Runnable() {
            public void run() {
                RetrofitUtil.updateDialogTitle(getString(R.string.str_synchronizing_data));
            }
        });
        RetrofitService service = RetrofitUtil.retrofitClient();
        Call<loginResponse> apiCall = service.syncRemarkPlan(user_id, last_sync_date, imei_number, syncData.toString());
        apiCall.enqueue(new Callback<loginResponse>() {
            @Override
            public void onResponse(Call<loginResponse> call, Response<loginResponse> response) {
                updateBarHandler.post(new Runnable() {
                    public void run() {
                        RetrofitUtil.updateDialogTitle(getString(R.string.str_synchronizing_data_started));
                    }
                });
                try {
                    final loginResponse loginResult = response.body();

                    // rest call to read data from api service
                    if (loginResult.getReturnCode()) {
                        //async process
                        new syncDataToLocalDb(loginResult).execute();
                    } else {
                        updateBarHandler.postDelayed(new Runnable() {
                            @Override
                            public void run() {
                                RetrofitUtil.hideDialog();
                            }
                        }, 500);
                        new SampleDialog("", loginResult.getStrMessage(), getContext());
                    }
                } catch (Exception e) {
                    updateBarHandler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            RetrofitUtil.hideDialog();
                        }
                    }, 500);
                    new SampleDialog("", getString(R.string.str_error_sync_data), getContext());
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

    /*async process*/
    private class syncDataToLocalDb extends AsyncTask<Void, Void, Boolean> {
        loginResponse loginResponse;

        public syncDataToLocalDb(loginResponse loginResponse) {
            this.loginResponse = loginResponse;
        }

        @Override
        protected Boolean doInBackground(Void... params) {
            try {
                ObjLoginResponse objResponse = loginResponse.getObjLoginResponse();

                final long startTime = System.currentTimeMillis();
                if (last_sync_date.matches(Utility.getCurDate())) {
                    clearSyncOnlyData();
                    dbRouteView.insertRoutes(objResponse.getArrRoutes());
                    dbVanStock.insertVanStock(objResponse.getArrVanStock());
                    dbOutletView.insertOutlet(objResponse.getArrOutlets());
                    dbDemandTargetView.insertDemandTarget(objResponse.getArrDemandTarget());
                    dbTodaySale.insertTodaySale(objResponse.getArrTodaySale());
                } else {
                    // erase all masterTables data
                    eraseAllTableData();

                    // sync data to local table and views
                    dbZoneView.insertZone(objResponse.getArrZones());
                    dbRouteView.insertRoutes(objResponse.getArrRoutes());
                    dbItemDetail.insertItemInfo(objResponse.getArrItemDetail());

                    // sync only data
                    dbVanStock.insertVanStock(objResponse.getArrVanStock());
                    dbOutletView.insertOutlet(objResponse.getArrOutlets());
                    dbDemandTargetView.insertDemandTarget(objResponse.getArrDemandTarget());
                    dbTodaySale.insertTodaySale(objResponse.getArrTodaySale());

                    // plans and remarks info
                    dbPlanTable.insertUserPlan(objResponse.getArrPlan());
                    dbRouteRemark.insertRouteRemark(objResponse.getArrRouteRemark());
                    dbOutletRemark.insertOutletRemark(objResponse.getArrRemark());

                    // sale history details
                    dbSaleHistory.insertSaleHistory(objResponse.getArrSaleHistory());
                    dbShVanLoadingView.insertSHRouteVanLoading(objResponse.getArrSHVanLoading());
                    dbShOutletSaleView.insertSHOutletSale(objResponse.getArrSHOutletSale());
                }

                //update sync date to local
                Utility.savePreference(getContext(), Utility.LAST_SYNC_DATE, Utility.getCurDate());

                final long endtime = System.currentTimeMillis();
                Log.i("Total Time: ", String.valueOf(endtime - startTime));
            } catch (Exception e) {
                return false;
            }
            return loginResponse.getReturnCode();
        }

        @Override
        protected void onPostExecute(Boolean status) {
            if (status) {
                updateBarHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        RetrofitUtil.hideDialog();
                    }
                }, 500);
                SnackbarUtil.showSnackbar(getView(), getString(R.string.str_sync_complete));
            } else {
                updateBarHandler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        RetrofitUtil.hideDialog();
                    }
                }, 500);
                new SampleDialog("", getString(R.string.str_error_sync_data), getContext());
            }
        }
    }
}
