package com.hgil.siconprocess_view.activity.fragments.routeLevel.homeTabs;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.hgil.siconprocess_view.R;
import com.hgil.siconprocess_view.adapter.routeMap.RouteCustomerModel;
import com.hgil.siconprocess_view.adapter.routeMap.homeCompleted.RouteHomeCompletedTimeDiffAdapter;
import com.hgil.siconprocess_view.base.route_base.Route_Base_Fragment;
import com.hgil.siconprocess_view.database.OutletView;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class RouteHomeCompleteFragment extends Route_Base_Fragment {

    @BindView(R.id.rvCompleteRouteMap)
    RecyclerView rvCompleteRouteMap;
    @BindView(R.id.tvEmpty)
    TextView tvEmpty;

    private RouteHomeCompletedTimeDiffAdapter timeDiffAdapter;
    private OutletView outletView;
    private ArrayList<RouteCustomerModel> arrRouteMap = new ArrayList<>();

    public RouteHomeCompleteFragment() {
        // Required empty public constructor
    }

    public static RouteHomeCompleteFragment newInstance() {
        RouteHomeCompleteFragment fragment = new RouteHomeCompleteFragment();
        return fragment;
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_home_complete;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvCompleteRouteMap.setLayoutManager(linearLayoutManager);

        if (arrRouteMap != null)
            arrRouteMap.clear();
        else
            arrRouteMap = new ArrayList<>();

        outletView = new OutletView(getActivity());
        arrRouteMap.addAll(outletView.getRouteCompletedCustomers(routeId));
        timeDiffAdapter = new RouteHomeCompletedTimeDiffAdapter(getActivity(), arrRouteMap);
        rvCompleteRouteMap.setAdapter(timeDiffAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (arrRouteMap.size() == 0) {
            tvEmpty.setVisibility(View.VISIBLE);
            rvCompleteRouteMap.setVisibility(View.GONE);
        } else {
            tvEmpty.setVisibility(View.GONE);
            rvCompleteRouteMap.setVisibility(View.VISIBLE);
        }
    }
}
