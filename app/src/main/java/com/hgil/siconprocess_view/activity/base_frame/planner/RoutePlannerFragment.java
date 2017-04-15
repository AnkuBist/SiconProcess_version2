package com.hgil.siconprocess_view.activity.base_frame.planner;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.hgil.siconprocess_view.R;
import com.hgil.siconprocess_view.activity.base_frame.RouteBaseFragment;
import com.hgil.siconprocess_view.activity.base_frame.RouteListModel;
import com.hgil.siconprocess_view.database.RouteView;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class RoutePlannerFragment extends RouteBaseFragment {

    @BindView(R.id.rvPlannerRouteList)
    RecyclerView rvPlannerRouteList;
    @BindView(R.id.tvEmpty)
    TextView tvEmpty;

    private PlannerRouteListAdapter plannerRouteListAdapter;
    private RouteView routeView;
    private ArrayList<RouteListModel> arrRoute;

    public RoutePlannerFragment() {
        // Required empty public constructor
    }

    public static RoutePlannerFragment newInstance() {
        RoutePlannerFragment fragment = new RoutePlannerFragment();
        return fragment;
    }


    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_route_planner;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvPlannerRouteList.setLayoutManager(linearLayoutManager);

        hideSaveButton();
        setTitle("Route Plan");

        arrRoute = new ArrayList<>();
        routeView = new RouteView(getActivity());
        arrRoute.addAll(routeView.getRouteList());
        plannerRouteListAdapter = new PlannerRouteListAdapter(getActivity(), arrRoute);
        rvPlannerRouteList.setAdapter(plannerRouteListAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (arrRoute.size() == 0) {
            tvEmpty.setVisibility(View.VISIBLE);
            rvPlannerRouteList.setVisibility(View.GONE);
        } else {
            tvEmpty.setVisibility(View.GONE);
            rvPlannerRouteList.setVisibility(View.VISIBLE);
        }
    }
}