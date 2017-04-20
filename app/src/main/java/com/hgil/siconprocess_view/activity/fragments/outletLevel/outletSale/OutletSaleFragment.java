package com.hgil.siconprocess_view.activity.fragments.outletLevel.outletSale;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.hgil.siconprocess_view.R;
import com.hgil.siconprocess_view.adapter.outletSale.OutletSaleAdapter;
import com.hgil.siconprocess_view.base.route_base.Route_Base_Fragment;
import com.hgil.siconprocess_view.database.TodaySaleView;
import com.hgil.siconprocess_view.retrofit.loginResponse.dbModel.TodaySaleModel;
import com.hgil.siconprocess_view.utils.Utility;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class OutletSaleFragment extends Route_Base_Fragment {
    public double grandTotal = 0.00;
    @BindView(R.id.tvInvoiceTotal)
    TextView tvInvoiceTotal;
    @BindView(R.id.tvCustomerName)
    TextView tvCustomerName;
    @BindView(R.id.rvCustomerInvoice)
    RecyclerView rvCustomerInvoice;
    @BindView(R.id.tvEmpty)
    TextView tvEmpty;

    private OutletSaleAdapter invoiceAdapter;
    //private OutletSaleView outletSaleView;
    private TodaySaleView todaySaleView;
    private ArrayList<TodaySaleModel> arrSaleItems = new ArrayList<>();

    public OutletSaleFragment() {
        // Required empty public constructor
    }

    public static OutletSaleFragment newInstance(String customer_id, String customer_name) {
        Log.e("TAG", "newInstance");
        OutletSaleFragment fragment = new OutletSaleFragment();
        Bundle bundle = new Bundle();
        bundle.putString(CUSTOMER_ID, customer_id);
        bundle.putString(CUSTOMER_NAME, customer_name);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            customer_id = getArguments().getString(CUSTOMER_ID);
            customer_name = getArguments().getString(CUSTOMER_NAME);
        }

        // initialise the values at first time
        //outletSaleView = new OutletSaleView(getContext());
        todaySaleView = new TodaySaleView(getContext());

        invoiceAdapter = new OutletSaleAdapter(getActivity(), arrSaleItems);

        if (arrSaleItems != null)
            arrSaleItems.clear();
        arrSaleItems.addAll(todaySaleView.getRouteSaleByOutlet(customer_id));

        // grandTotal = outletSaleView.outletSaleAmount(customer_id);
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_outlet_sale;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (customer_name != null)
            tvCustomerName.setText(customer_name);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvCustomerInvoice.setLayoutManager(linearLayoutManager);

        rvCustomerInvoice.setAdapter(invoiceAdapter);

        setTitle("Today's Sale");
        hideSyncButton();
    }

    @Override
    public void onResume() {
        super.onResume();
        tvInvoiceTotal.setText(strRupee + String.valueOf(Utility.roundTwoDecimals(grandTotal)));
        if (arrSaleItems.size() == 0) {
            tvEmpty.setVisibility(View.VISIBLE);
            rvCustomerInvoice.setVisibility(View.GONE);
        } else {
            tvEmpty.setVisibility(View.GONE);
            rvCustomerInvoice.setVisibility(View.VISIBLE);
        }
    }
}
