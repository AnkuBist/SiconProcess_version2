package com.hgil.siconprocess.activity.navFragments;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.hgil.siconprocess.R;
import com.hgil.siconprocess.base.BaseFragment;
import com.hgil.siconprocess.database.masterTables.CrateCollectionView;
import com.hgil.siconprocess.database.masterTables.DepotInvoiceView;
import com.hgil.siconprocess.database.tables.CustomerRejectionTable;
import com.hgil.siconprocess.database.tables.InvoiceOutTable;
import com.hgil.siconprocess.database.tables.PaymentTable;

import butterknife.BindView;

public class FinalPaymentFragment extends BaseFragment {

    @BindView(R.id.tvRouteName)
    TextView tvRouteName;
    @BindView(R.id.etCashCollected)
    EditText etCashCollected;
    @BindView(R.id.etCashReceived)
    EditText etCashReceived;
    @BindView(R.id.etCrateLoaded)
    EditText etCrateLoaded;
    @BindView(R.id.etCrateReceived)
    EditText etCrateReceived;
    @BindView(R.id.etItemsLoaded)
    EditText etItemsLoaded;
    @BindView(R.id.etItemsSold)
    EditText etItemsSold;
    @BindView(R.id.etItemsLeftover)
    EditText etItemsLeftover;
    @BindView(R.id.etItemsReceived)
    EditText etItemsReceived;
    @BindView(R.id.etItemsFreshRej)
    EditText etItemsFreshRej;
    @BindView(R.id.etItemsReceivedFreshRej)
    EditText etItemsReceivedFreshRej;
    @BindView(R.id.etItemsMarketRej)
    EditText etItemsMarketRej;
    @BindView(R.id.etItemsReceivedMarketRej)
    EditText etItemsReceivedMarketRej;


    public FinalPaymentFragment() {
        // Required empty public constructor
    }

    public static FinalPaymentFragment newInstance() {
        FinalPaymentFragment fragment = new FinalPaymentFragment();
        return fragment;
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_final_payment;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // set route name to the route
        tvRouteName.setText(getRouteName());
        hideSaveButton();

        CrateCollectionView crateCollectionView = new CrateCollectionView(getContext());
        DepotInvoiceView depot_invoice = new DepotInvoiceView(getContext());
        CustomerRejectionTable rejectionTable = new CustomerRejectionTable(getContext());
        PaymentTable paymentTable = new PaymentTable(getContext());
        InvoiceOutTable invoiceOutTable = new InvoiceOutTable(getContext());

        // cashier total verification
        double amount_collected = paymentTable.routeTotalAmountCollection();
        double amount_delivered_by_cashier = 0; // ui task to be completed
        etCashCollected.setText(String.valueOf(amount_collected));
        etCashReceived.setText(String.valueOf(amount_delivered_by_cashier));

         /*get items loaded and received stock*/
        int crates_loaded_in_van = crateCollectionView.vanTotalCrate();
        int crates_delivered_by_cashier = 0; // ui task
        etCrateLoaded.setText(String.valueOf(crates_loaded_in_van));
        etCrateReceived.setText(String.valueOf(crates_delivered_by_cashier));

        // cross check items left and rejections
        int items_loaded = depot_invoice.totalItemCount();
        int items_sold = invoiceOutTable.soldItemCount();
        int items_leftover = items_loaded - items_sold;
        int items_received = 0; //ui
        int items_fresh_rejection = rejectionTable.routeFreshRejection();
        int items_fresh_rej_received = 0;   //ui
        int items_market_rejection = rejectionTable.routeMarketRejection();
        int items_market_rej_received = 0;  //ui

        etItemsLoaded.setText(String.valueOf(items_loaded));
        etItemsSold.setText(String.valueOf(items_sold));
        etItemsLeftover.setText(String.valueOf(items_leftover));
        etItemsReceived.setText(String.valueOf(items_received));
        etItemsFreshRej.setText(String.valueOf(items_fresh_rejection));
        etItemsReceivedFreshRej.setText(String.valueOf(items_fresh_rej_received));
        etItemsMarketRej.setText(String.valueOf(items_market_rejection));
        etItemsReceivedMarketRej.setText(String.valueOf(items_market_rej_received));


    }

}
