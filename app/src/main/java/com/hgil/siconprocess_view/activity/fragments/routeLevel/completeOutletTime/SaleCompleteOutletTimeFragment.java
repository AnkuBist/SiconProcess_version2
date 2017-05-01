package com.hgil.siconprocess_view.activity.fragments.routeLevel.completeOutletTime;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.hgil.siconprocess_view.R;
import com.hgil.siconprocess_view.adapter.outletSaleTimeDiff.RouteOutletSaleTimeDiffAdapter;
import com.hgil.siconprocess_view.adapter.routeMap.RouteCustomerModel;
import com.hgil.siconprocess_view.base.route_base.Route_Base_Fragment;
import com.hgil.siconprocess_view.database.OutletView;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class SaleCompleteOutletTimeFragment extends Route_Base_Fragment {

    @BindView(R.id.tvRouteName)
    TextView tvRouteName;
    @BindView(R.id.rvCompleteOutletTime)
    RecyclerView rvCompleteOutletTime;
    @BindView(R.id.tvEmpty)
    TextView tvEmpty;

    private RouteOutletSaleTimeDiffAdapter outletSaleTimeDiffAdapter;
    private OutletView outletView;
    private ArrayList<RouteCustomerModel> arrSaleTimeDiff = new ArrayList<>();

    public SaleCompleteOutletTimeFragment() {
        // Required empty public constructor
    }

    public static SaleCompleteOutletTimeFragment newInstance() {
        SaleCompleteOutletTimeFragment fragment = new SaleCompleteOutletTimeFragment();
        return fragment;
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_sale_complete_outlet_time;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // set route name to the route
        tvRouteName.setText(getRouteName());

        setTitle(getString(R.string.str_outlet_sale_inv_time_diff));
        hideSyncButton();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvCompleteOutletTime.setLayoutManager(linearLayoutManager);

        if (arrSaleTimeDiff != null)
            arrSaleTimeDiff.clear();
        else
            arrSaleTimeDiff = new ArrayList<>();

        outletView = new OutletView(getActivity());
        arrSaleTimeDiff.addAll(outletView.getSaleTimeCompletedOutlets(routeId));
        outletSaleTimeDiffAdapter = new RouteOutletSaleTimeDiffAdapter(getActivity(), arrSaleTimeDiff);
        rvCompleteOutletTime.setAdapter(outletSaleTimeDiffAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (arrSaleTimeDiff.size() == 0) {
            tvEmpty.setVisibility(View.VISIBLE);
            rvCompleteOutletTime.setVisibility(View.GONE);
        } else {
            tvEmpty.setVisibility(View.GONE);
            rvCompleteOutletTime.setVisibility(View.VISIBLE);
        }
    }
}
