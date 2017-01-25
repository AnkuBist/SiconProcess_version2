package com.hgil.siconprocess.activity.homeTabs;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hgil.siconprocess.R;
import com.hgil.siconprocess.adapter.RouteMapRAdapter;
import com.hgil.siconprocess.database.tables.CustomerRouteMappingView;
import com.hgil.siconprocess.retrofit.loginResponse.dbModels.CustomerRouteMapModel;

import java.lang.reflect.Array;
import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeAllFragment extends Fragment {

    @BindView(R.id.rvAllRouteMap)
    RecyclerView rvAllRouteMap;
    @BindView(R.id.tvEmpty)
    TextView tvEmpty;

    public HomeAllFragment() {
        // Required empty public constructor
    }

    public static HomeAllFragment newInstance() {
        HomeAllFragment fragment = new HomeAllFragment();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_all, container, false);
    }

    private RouteMapRAdapter mapRAdapter;
    private CustomerRouteMappingView routeMap;
    private ArrayList<CustomerRouteMapModel> arrRouteMap;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        ButterKnife.bind(this, view);

        rvAllRouteMap = (RecyclerView) view.findViewById(R.id.rvAllRouteMap);
        tvEmpty = (TextView) view.findViewById(R.id.tvEmpty);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvAllRouteMap.setLayoutManager(linearLayoutManager);

        routeMap = new CustomerRouteMappingView(getActivity());
    }

    @Override
    public void onResume() {
        super.onResume();
        if (arrRouteMap != null)
            arrRouteMap.clear();
        arrRouteMap = routeMap.getAllCustomerRouteMap();
        mapRAdapter = new RouteMapRAdapter(getActivity(), arrRouteMap);
        rvAllRouteMap.setAdapter(mapRAdapter);
        if (arrRouteMap.size() == 0) {
            tvEmpty.setVisibility(View.VISIBLE);
            rvAllRouteMap.setVisibility(View.GONE);
        } else {
            tvEmpty.setVisibility(View.GONE);
            rvAllRouteMap.setVisibility(View.VISIBLE);
        }
    }
}
