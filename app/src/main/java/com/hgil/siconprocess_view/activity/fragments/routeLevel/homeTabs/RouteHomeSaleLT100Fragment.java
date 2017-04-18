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
import com.hgil.siconprocess_view.adapter.routeMap.RouteOutletAdapter;
import com.hgil.siconprocess_view.base.route_base.Route_Base_Fragment;
import com.hgil.siconprocess_view.database.OutletView;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class RouteHomeSaleLT100Fragment extends Route_Base_Fragment {

    @BindView(R.id.rvSaleLT100)
    RecyclerView rvSaleLT100;
    @BindView(R.id.tvEmpty)
    TextView tvEmpty;

    private RouteOutletAdapter mapRAdapter;
    private OutletView outletView;
    private ArrayList<RouteCustomerModel> arrRouteMap;

    public RouteHomeSaleLT100Fragment() {
        // Required empty public constructor
    }

    public static RouteHomeSaleLT100Fragment newInstance() {
        RouteHomeSaleLT100Fragment fragment = new RouteHomeSaleLT100Fragment();
        return fragment;
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_home_sale_lt_100;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvSaleLT100.setLayoutManager(linearLayoutManager);

        arrRouteMap = new ArrayList<>();
        outletView = new OutletView(getActivity());
        arrRouteMap.addAll(outletView.getCustomerSaleLT100(routeId));
        mapRAdapter = new RouteOutletAdapter(getActivity(), arrRouteMap);
        rvSaleLT100.setAdapter(mapRAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (arrRouteMap.size() == 0) {
            tvEmpty.setVisibility(View.VISIBLE);
            rvSaleLT100.setVisibility(View.GONE);
        } else {
            tvEmpty.setVisibility(View.GONE);
            rvSaleLT100.setVisibility(View.VISIBLE);
        }
    }
}
