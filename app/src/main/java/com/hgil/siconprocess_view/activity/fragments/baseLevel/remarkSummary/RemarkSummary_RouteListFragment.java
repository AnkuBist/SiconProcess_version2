package com.hgil.siconprocess_view.activity.fragments.baseLevel.remarkSummary;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.hgil.siconprocess_view.R;
import com.hgil.siconprocess_view.adapter.remarkSummary.RemarkRouteListAdapter;
import com.hgil.siconprocess_view.adapter.routeList.RouteListModel;
import com.hgil.siconprocess_view.base.Base_Fragment;
import com.hgil.siconprocess_view.database.RouteView;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class RemarkSummary_RouteListFragment extends Base_Fragment {

    @BindView(R.id.rvRemarkRouteList)
    RecyclerView rvRemarkRouteList;
    @BindView(R.id.tvEmpty)
    TextView tvEmpty;

    private RemarkRouteListAdapter remarkRouteListAdapter;
    private RouteView routeView;
    private ArrayList<RouteListModel> arrRoute = new ArrayList<>();

    public RemarkSummary_RouteListFragment() {
        // Required empty public constructor
    }

    public static RemarkSummary_RouteListFragment newInstance() {
        RemarkSummary_RouteListFragment fragment = new RemarkSummary_RouteListFragment();
        return fragment;
    }


    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_remark_summary_route_list;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvRemarkRouteList.setLayoutManager(linearLayoutManager);

        hideSyncButton();
        setTitle(getString(R.string.str_nav_remarks_summary));

        if (arrRoute != null)
            arrRoute.clear();
        else
            arrRoute = new ArrayList<>();

        routeView = new RouteView(getActivity());
        arrRoute.addAll(routeView.getRemarkRouteList());
        remarkRouteListAdapter = new RemarkRouteListAdapter(getActivity(), arrRoute);
        rvRemarkRouteList.setAdapter(remarkRouteListAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (arrRoute.size() == 0) {
            tvEmpty.setVisibility(View.VISIBLE);
            rvRemarkRouteList.setVisibility(View.GONE);
        } else {
            tvEmpty.setVisibility(View.GONE);
            rvRemarkRouteList.setVisibility(View.VISIBLE);
        }
    }
}