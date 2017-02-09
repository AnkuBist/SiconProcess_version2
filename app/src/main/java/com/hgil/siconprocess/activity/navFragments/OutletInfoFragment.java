package com.hgil.siconprocess.activity.navFragments;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.hgil.siconprocess.R;
import com.hgil.siconprocess.adapter.OutletListAdapter;
import com.hgil.siconprocess.adapter.routeMap.RouteCustomerModel;
import com.hgil.siconprocess.adapter.routeMap.RouteMapRAdapter;
import com.hgil.siconprocess.base.BaseFragment;
import com.hgil.siconprocess.database.masterTables.CustomerRouteMappingView;

import java.util.ArrayList;

import butterknife.BindView;

public class OutletInfoFragment extends BaseFragment {
    @BindView(R.id.rvOutlets)
    RecyclerView rvOutlets;
    @BindView(R.id.tvEmpty)
    TextView tvEmpty;

    private OutletListAdapter outletAdapter;
    private CustomerRouteMappingView routeMap;
    private ArrayList<RouteCustomerModel> arrOutlets;

    public OutletInfoFragment() {
        // Required empty public constructor
    }

    public static OutletInfoFragment newInstance() {
        OutletInfoFragment fragment = new OutletInfoFragment();
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
        return R.layout.fragment_outlet_info;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setTitle("Outlet Info");
        hideSaveButton();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvOutlets.setLayoutManager(linearLayoutManager);

        arrOutlets = new ArrayList<>();
        routeMap = new CustomerRouteMappingView(getActivity());
        arrOutlets.addAll(routeMap.getRouteCustomers());
        outletAdapter = new OutletListAdapter(getActivity(), arrOutlets);
        rvOutlets.setAdapter(outletAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (arrOutlets.size() == 0) {
            tvEmpty.setVisibility(View.VISIBLE);
            rvOutlets.setVisibility(View.GONE);
        } else {
            tvEmpty.setVisibility(View.GONE);
            rvOutlets.setVisibility(View.VISIBLE);
        }
    }

}
