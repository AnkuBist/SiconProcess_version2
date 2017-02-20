package com.hgil.siconprocess.activity.navFragments;

import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.google.gson.Gson;
import com.hgil.siconprocess.R;
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

import org.json.JSONException;
import org.json.JSONObject;

import java.lang.reflect.Array;
import java.util.ArrayList;

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
        try {
            JSONObject jObj = new JSONObject(json);
            Log.e(TAG, json);
            Log.e(TAG + "1", jObj.toString());
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }

}
