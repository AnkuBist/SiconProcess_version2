package com.hgil.siconprocess.activity.homeTabs;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.hgil.siconprocess.R;
import com.hgil.siconprocess.adapter.routeMap.RouteCustomerModel;
import com.hgil.siconprocess.base.BaseFragment;
import com.hgil.siconprocess.adapter.routeMap.RouteMapRAdapter;
import com.hgil.siconprocess.database.masterTables.CustomerRouteMappingView;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeAllFragment extends BaseFragment {

    @BindView(R.id.rvAllRouteMap)
    RecyclerView rvAllRouteMap;
    @BindView(R.id.tvEmpty)
    TextView tvEmpty;

    private RouteMapRAdapter mapRAdapter;
    private CustomerRouteMappingView routeMap;
    private ArrayList<RouteCustomerModel> arrRouteMap;

    public HomeAllFragment() {
        // Required empty public constructor
    }

    public static HomeAllFragment newInstance() {
        HomeAllFragment fragment = new HomeAllFragment();
        return fragment;
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_home_all;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvAllRouteMap.setLayoutManager(linearLayoutManager);

        arrRouteMap = new ArrayList<>();
        routeMap = new CustomerRouteMappingView(getActivity());
        arrRouteMap.addAll(routeMap.getRouteCustomers());
        mapRAdapter = new RouteMapRAdapter(getActivity(), arrRouteMap);
        rvAllRouteMap.setAdapter(mapRAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (arrRouteMap.size() == 0) {
            tvEmpty.setVisibility(View.VISIBLE);
            rvAllRouteMap.setVisibility(View.GONE);
        } else {
            tvEmpty.setVisibility(View.GONE);
            rvAllRouteMap.setVisibility(View.VISIBLE);
        }
    }
}
