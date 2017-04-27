package com.hgil.siconprocess_view.activity.fragments.routeLevel.routeRemark;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.hgil.siconprocess_view.R;
import com.hgil.siconprocess_view.adapter.routeRemark.RouteRemarkListAdapter;
import com.hgil.siconprocess_view.base.route_base.Route_Base_Fragment;
import com.hgil.siconprocess_view.database.localDb.RouteRemarkTable;
import com.hgil.siconprocess_view.retrofit.loginResponse.dbModel.RouteRemarkModel;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class Route_RemarkListFragment extends Route_Base_Fragment {

    @BindView(R.id.tvRouteName)
    TextView tvRouteName;
    @BindView(R.id.rvRouteRemarkList)
    RecyclerView rvRouteRemarkList;
    @BindView(R.id.tvEmpty)
    TextView tvEmpty;
    @BindView(R.id.fabAddRemark)
    FloatingActionButton fabAddRemark;

    private RouteRemarkListAdapter routeRemarkListAdapter;
    private RouteRemarkTable routeRemarkTable;
    private ArrayList<RouteRemarkModel> arrRouteRemark = new ArrayList<>();

    public Route_RemarkListFragment() {
        // Required empty public constructor
    }

    public static Route_RemarkListFragment newInstance() {
        Route_RemarkListFragment fragment = new Route_RemarkListFragment();
        return fragment;
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_route_remark_list;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        hideSyncButton();
        setTitle(getString(R.string.str_route_remark));

        if (getRouteName() != null)
            tvRouteName.setText(getRouteName());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvRouteRemarkList.setLayoutManager(linearLayoutManager);

        if (arrRouteRemark != null)
            arrRouteRemark.clear();
        else
            arrRouteRemark = new ArrayList<>();

        routeRemarkTable = new RouteRemarkTable(getActivity());
        arrRouteRemark.addAll(routeRemarkTable.getRouteRemarks(getLoginId(), getRouteId()));
        routeRemarkListAdapter = new RouteRemarkListAdapter(getActivity(), arrRouteRemark);
        rvRouteRemarkList.setAdapter(routeRemarkListAdapter);


        fabAddRemark.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AddRouteRemarkFragment routeRemarkFragment = AddRouteRemarkFragment.newInstance();
                launchNavFragment(routeRemarkFragment);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        if (arrRouteRemark.size() == 0) {
            tvEmpty.setVisibility(View.VISIBLE);
            rvRouteRemarkList.setVisibility(View.GONE);
        } else {
            tvEmpty.setVisibility(View.GONE);
            rvRouteRemarkList.setVisibility(View.VISIBLE);
        }
    }
}
