package com.hgil.siconprocess_view.activity.fragments.routeLevel.completeOutlet;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.hgil.siconprocess_view.R;
import com.hgil.siconprocess_view.adapter.routeMap.RouteCustomerModel;
import com.hgil.siconprocess_view.adapter.routeMap.RouteOutletAdapter;
import com.hgil.siconprocess_view.base.route_base.Route_Base_Fragment;
import com.hgil.siconprocess_view.database.OutletView;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class SaleCompleteOutletFragment extends Route_Base_Fragment {

    @BindView(R.id.rvCompleteOutletTime)
    RecyclerView rvCompleteOutletTime;
    @BindView(R.id.tvEmpty)
    TextView tvEmpty;

    private RouteOutletAdapter mapRAdapter;
    private OutletView outletView;
    private ArrayList<RouteCustomerModel> arrRouteMap;

    public SaleCompleteOutletFragment() {
        // Required empty public constructor
    }

    public static SaleCompleteOutletFragment newInstance() {
        SaleCompleteOutletFragment fragment = new SaleCompleteOutletFragment();
        return fragment;
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_sale_complete_outlet_time;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvCompleteOutletTime.setLayoutManager(linearLayoutManager);

        arrRouteMap = new ArrayList<>();
        outletView = new OutletView(getActivity());
        arrRouteMap.addAll(outletView.getSaleTimeCompletedOutlets(routeId));
        mapRAdapter = new RouteOutletAdapter(getActivity(), arrRouteMap);
        rvCompleteOutletTime.setAdapter(mapRAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (arrRouteMap.size() == 0) {
            tvEmpty.setVisibility(View.VISIBLE);
            rvCompleteOutletTime.setVisibility(View.GONE);
        } else {
            tvEmpty.setVisibility(View.GONE);
            rvCompleteOutletTime.setVisibility(View.VISIBLE);
        }
    }
}
