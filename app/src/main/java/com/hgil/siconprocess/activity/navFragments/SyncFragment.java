package com.hgil.siconprocess.activity.navFragments;

import android.os.Bundle;
import android.view.View;

import com.hgil.siconprocess.R;
import com.hgil.siconprocess.activity.navFragments.invoiceSync.CollectionCashModel;
import com.hgil.siconprocess.activity.navFragments.invoiceSync.CollectionCrateModel;
import com.hgil.siconprocess.activity.navFragments.invoiceSync.CrateStockCheck;
import com.hgil.siconprocess.base.BaseFragment;
import com.hgil.siconprocess.database.tables.InvoiceOutTable;
import com.hgil.siconprocess.database.tables.PaymentTable;

import java.util.ArrayList;

public class SyncFragment extends BaseFragment {

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
    }

    /*preparing data to sync whole day process*/
    private void initiateDataSync() {
        /*invoice data preparation*/
        InvoiceOutTable invoiceOutTable = new InvoiceOutTable(getContext());

        PaymentTable paymentTable = new PaymentTable(getContext());
        ArrayList<CollectionCashModel> cashCollection = paymentTable.syncCashDetail();
        ArrayList<CollectionCrateModel> crateCollection = paymentTable.syncCrateDetail();
        CrateStockCheck crateStock = paymentTable.syncCrateStock(getRouteId());

        // get van actual stock
        /*
        * 1. items loaded
        * 2. items sold
        * 3. rejection
        * 4. item_left over
        * 5. total item in van(loaded-sold+rejection)
        * */


    }

}
