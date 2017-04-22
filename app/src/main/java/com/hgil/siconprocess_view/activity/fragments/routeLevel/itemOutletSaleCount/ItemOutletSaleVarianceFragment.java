package com.hgil.siconprocess_view.activity.fragments.routeLevel.itemOutletSaleCount;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.hgil.siconprocess_view.R;
import com.hgil.siconprocess_view.adapter.skuOutletSale.ItemOutletSaleVarianceAdapter;
import com.hgil.siconprocess_view.adapter.skuOutletSale.ItemOutletSaleVarianceModel;
import com.hgil.siconprocess_view.base.route_base.Route_Base_Fragment;
import com.hgil.siconprocess_view.database.VanStockView;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class ItemOutletSaleVarianceFragment extends Route_Base_Fragment {

    @BindView(R.id.tvRouteName)
    TextView tvRouteName;
    @BindView(R.id.rvItemSaleVariance)
    RecyclerView rvItemSaleVariance;
    @BindView(R.id.tvEmpty)
    TextView tvEmpty;

    private ItemOutletSaleVarianceAdapter itemSaleVarianceAdapter;
    private VanStockView vanStockView;
    private ArrayList<ItemOutletSaleVarianceModel> arrItemOutletSale;

    public ItemOutletSaleVarianceFragment() {
        // Required empty public constructor
    }

    public static ItemOutletSaleVarianceFragment newInstance() {
        ItemOutletSaleVarianceFragment fragment = new ItemOutletSaleVarianceFragment();
        return fragment;
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_outlet_item_sale_variance;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // set route name to the route
        tvRouteName.setText(getRouteName());

        setTitle(getString(R.string.str_nav_item_sale_variance));
        hideSyncButton();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvItemSaleVariance.setLayoutManager(linearLayoutManager);

        arrItemOutletSale = new ArrayList<>();
        vanStockView = new VanStockView(getActivity());
        arrItemOutletSale.addAll(vanStockView.getRouteCustomersItemSale(getRouteId()));
        itemSaleVarianceAdapter = new ItemOutletSaleVarianceAdapter(getActivity(), arrItemOutletSale);
        rvItemSaleVariance.setAdapter(itemSaleVarianceAdapter);
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
