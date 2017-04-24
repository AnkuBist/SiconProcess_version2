package com.hgil.siconprocess_view.activity.fragments.routeLevel.skuSaleDetail;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.hgil.siconprocess_view.R;
import com.hgil.siconprocess_view.adapter.skuOutletSale.SkuSaleDetailAdapter;
import com.hgil.siconprocess_view.adapter.skuOutletSale.SkuSaleDetailModel;
import com.hgil.siconprocess_view.base.route_base.Route_Base_Fragment;
import com.hgil.siconprocess_view.database.OutletView;
import com.hgil.siconprocess_view.database.VanStockView;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class SkuSaleDetailFragment extends Route_Base_Fragment {

    @BindView(R.id.tvRouteName)
    TextView tvRouteName;
    @BindView(R.id.rvItemSaleVariance)
    RecyclerView rvItemSaleVariance;
    @BindView(R.id.tvEmpty)
    TextView tvEmpty;
    @BindView(R.id.tvProductiveCalls)
    TextView tvProductiveCalls;

    private SkuSaleDetailAdapter skuSaleDetailAdapter;
    private VanStockView vanStockView;
    private ArrayList<SkuSaleDetailModel> arrItemOutletSale;

    public SkuSaleDetailFragment() {
        // Required empty public constructor
    }

    public static SkuSaleDetailFragment newInstance() {
        SkuSaleDetailFragment fragment = new SkuSaleDetailFragment();
        return fragment;
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_sku_sale_detail;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // set route name to the route
        tvRouteName.setText(getRouteName());

        setTitle(getString(R.string.str_nav_sku_sale_detail));
        hideSyncButton();

        OutletView outletView = new OutletView(getContext());

        // route productive calls
        int productive_calls = outletView.routeProductiveCalls(getRouteId());
        tvProductiveCalls.setText("" + productive_calls);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvItemSaleVariance.setLayoutManager(linearLayoutManager);

        arrItemOutletSale = new ArrayList<>();
        vanStockView = new VanStockView(getActivity());
        arrItemOutletSale.addAll(vanStockView.getRouteCustomersItemSale(getRouteId()));
        skuSaleDetailAdapter = new SkuSaleDetailAdapter(getActivity(), arrItemOutletSale);
        rvItemSaleVariance.setAdapter(skuSaleDetailAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (arrItemOutletSale.size() == 0) {
            tvEmpty.setVisibility(View.VISIBLE);
            rvItemSaleVariance.setVisibility(View.GONE);
        } else {
            tvEmpty.setVisibility(View.GONE);
            rvItemSaleVariance.setVisibility(View.VISIBLE);
        }
    }
}
