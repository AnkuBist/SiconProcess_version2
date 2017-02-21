package com.hgil.siconprocess.activity.navFragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.hgil.siconprocess.R;
import com.hgil.siconprocess.activity.LoginActivity;
import com.hgil.siconprocess.activity.NavBaseActivity;
import com.hgil.siconprocess.activity.navFragments.invoiceSync.CashCheck;
import com.hgil.siconprocess.activity.navFragments.invoiceSync.CollectionCashModel;
import com.hgil.siconprocess.activity.navFragments.invoiceSync.CollectionCrateModel;
import com.hgil.siconprocess.activity.navFragments.invoiceSync.CrateCheck;
import com.hgil.siconprocess.activity.navFragments.invoiceSync.CrateStockCheck;
import com.hgil.siconprocess.activity.navFragments.invoiceSync.SyncData;
import com.hgil.siconprocess.activity.navFragments.invoiceSync.SyncInvoiceDetailModel;
import com.hgil.siconprocess.activity.navFragments.invoiceSync.VanStockCheck;
import com.hgil.siconprocess.base.BaseFragment;
import com.hgil.siconprocess.database.masterTables.CrateCollectionView;
import com.hgil.siconprocess.database.masterTables.DepotInvoiceView;
import com.hgil.siconprocess.database.tables.CustomerRejectionTable;
import com.hgil.siconprocess.database.tables.InvoiceOutTable;
import com.hgil.siconprocess.database.tables.PaymentTable;
import com.hgil.siconprocess.retrofit.RetrofitService;
import com.hgil.siconprocess.retrofit.RetrofitUtil;
import com.hgil.siconprocess.retrofit.loginResponse.ObjLoginResponse;
import com.hgil.siconprocess.retrofit.loginResponse.dbModels.RouteModel;
import com.hgil.siconprocess.retrofit.loginResponse.loginResponse;
import com.hgil.siconprocess.retrofit.loginResponse.syncResponse;
import com.hgil.siconprocess.utils.Utility;

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SyncFragment extends BaseFragment {

    private String TAG = this.getClass().getName();

    public SyncFragment() {
        // Required empty public constructor
    }

    public static SyncFragment newInstance() {
        SyncFragment fragment = new SyncFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
        }
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_sync;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        hideSaveButton();
        initiateDataSync();
    }

    /*preparing data to sync whole day process*/
    private void initiateDataSync() {
        /*invoice data preparation*/
        CrateCollectionView crateCollectionView = new CrateCollectionView(getContext());
        DepotInvoiceView depot_invoice = new DepotInvoiceView(getContext());
        InvoiceOutTable invoiceOutTable = new InvoiceOutTable(getContext());
        CustomerRejectionTable rejectionTable = new CustomerRejectionTable(getContext());

        // invoice sync
        //TODO
        ArrayList<SyncInvoiceDetailModel> syncInvoice = invoiceOutTable.syncInvoice();

        // get rejections details
        ArrayList<SyncInvoiceDetailModel> syncInvoiceRejection = rejectionTable.syncRejection(getRouteId());


        PaymentTable paymentTable = new PaymentTable(getContext());
        ArrayList<CollectionCashModel> cashCollection = paymentTable.syncCashDetail();
        ArrayList<CollectionCrateModel> crateCollection = paymentTable.syncCrateDetail();
        CrateStockCheck crateStock = paymentTable.syncCrateStock(getRouteId());

        // cashier total verification
        double amount_collected = paymentTable.routeTotalAmountCollection();
        double amount_delivered_by_cashier = 0; // ui task to be completed

        CashCheck cashCheck = new CashCheck();
        cashCheck.setAmount_collected(amount_collected);
        cashCheck.setAmount_delivered(amount_delivered_by_cashier);
        
        /*get items loaded and received stock*/
        int crates_loaded_in_van = crateCollectionView.vanTotalCrate();
        int crates_delivered_by_cashier = 0; // ui task

        CrateCheck crateCheck = new CrateCheck();
        crateCheck.setCrates_loaded(crates_loaded_in_van);
        crateCheck.setCrate_delivered(crates_delivered_by_cashier);

        // cross check items left and rejections
        int items_loaded = depot_invoice.totalItemCount();
        int items_fresh_rejection = rejectionTable.routeFreshRejection();
        int items_market_rejection = rejectionTable.routeMarketRejection();
        int items_delivered_by_cashier = 0; // ui task

        VanStockCheck vanStockCheck = new VanStockCheck();
        vanStockCheck.setItems_loaded(items_loaded);
        vanStockCheck.setFresh_rejections(items_fresh_rejection);
        vanStockCheck.setMarket_rejection(items_market_rejection);
        vanStockCheck.setItem_delivered(items_delivered_by_cashier);


        // get van actual stock
        /*
        * 1. items loaded
        * 2. items sold
        * 3. rejection
        * 4. item_left over
        * 5. total item in van(loaded-sold+rejection)
        * */

        // finally convert all object and array data into jsonobject and send as object data to server side api;
        SyncData syncData = new SyncData();

        syncData.setSyncInvoice(syncInvoice);
        syncData.setSyncInvoiceRejection(syncInvoiceRejection);
        syncData.setCashCollection(cashCollection);
        syncData.setCrateCollection(crateCollection);
        syncData.setCrateStock(crateStock);
        syncData.setCashCheck(cashCheck);
        syncData.setCrateCheck(crateCheck);
        syncData.setVanStockCheck(vanStockCheck);


        String json = new Gson().toJson(syncData);
        JSONObject jObj = null;
        try {
            jObj = new JSONObject(json);
            Log.e(TAG, json);
            Log.e(TAG + "1", jObj.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

        // make retrofit request call
        syncRouteInvoice(getRouteId(), jObj);
    }

    // sync process retrofit call
    /*retrofit call test example*/
    public void syncRouteInvoice(final String route_id, JSONObject route_data) {
        RetrofitUtil.showDialog(getContext());
        RetrofitService service = RetrofitUtil.retrofitClient();
        Call<syncResponse> apiCall = service.syncRouteData(route_id, route_data.toString());
        apiCall.enqueue(new Callback<syncResponse>() {
            @Override
            public void onResponse(Call<syncResponse> call, Response<syncResponse> response) {
                RetrofitUtil.hideDialog();
                syncResponse syncResponse = response.body();
                // rest call to read data from api service
                if (syncResponse.getReturnCode()) {
                    //check if call completed or not
                    RetrofitUtil.showToast(getContext(), syncResponse.getStrMessage());

                    //erase all masterTables data
                    //eraseAllSyncTableData();

                    //after saving all values to database start new activity
                    //startActivity(new Intent(LoginActivity.this, NavBaseActivity.class));
                    // finish();
                    //overridePendingTransition(R.anim.anim_slide_in_left, R.anim.anim_slide_out_left);
                } else {
                    RetrofitUtil.showToast(getContext(), syncResponse.getStrMessage());
                }
            }

            @Override
            public void onFailure(Call<syncResponse> call, Throwable t) {
                RetrofitUtil.hideDialog();
                // show some error toast or message to display the api call issue
                RetrofitUtil.showToast(getContext(), "Unable to access API");
            }
        });
    }

    //TODO
    public void eraseAllSyncTables() {
    }

}
