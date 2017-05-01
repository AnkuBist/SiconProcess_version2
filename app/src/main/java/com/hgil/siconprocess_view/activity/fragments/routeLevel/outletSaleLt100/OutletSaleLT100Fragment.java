package com.hgil.siconprocess_view.activity.fragments.routeLevel.outletSaleLt100;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.hgil.siconprocess_view.R;
import com.hgil.siconprocess_view.adapter.routeMap.RouteCustomerModel;
import com.hgil.siconprocess_view.adapter.saleLt100.SaleLT100Adapter;
import com.hgil.siconprocess_view.base.route_base.Route_Base_Fragment;
import com.hgil.siconprocess_view.database.OutletView;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class OutletSaleLT100Fragment extends Route_Base_Fragment {

    @BindView(R.id.tvRouteName)
    TextView tvRouteName;
    @BindView(R.id.rvSaleLT100)
    RecyclerView rvSaleLT100;
    @BindView(R.id.tvEmpty)
    TextView tvEmpty;

    private SaleLT100Adapter saleLt100Adapter;
    private OutletView outletView;
    private ArrayList<RouteCustomerModel> arrRouteMap = new ArrayList<>();

    public OutletSaleLT100Fragment() {
        // Required empty public constructor
    }

    public static OutletSaleLT100Fragment newInstance() {
        OutletSaleLT100Fragment fragment = new OutletSaleLT100Fragment();
        return fragment;
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_outlet_sale_lt_100;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        tvRouteName.setText(getRouteName());

        setTitle(getString(R.string.str_outlet_sale_lt_100));
        hideSyncButton();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvSaleLT100.setLayoutManager(linearLayoutManager);

        if (arrRouteMap != null)
            arrRouteMap.clear();
        else
            arrRouteMap = new ArrayList<>();

        outletView = new OutletView(getActivity());
        arrRouteMap.addAll(outletView.getCustomerSaleLT100(routeId));
        saleLt100Adapter = new SaleLT100Adapter(getActivity(), arrRouteMap);
        rvSaleLT100.setAdapter(saleLt100Adapter);
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
