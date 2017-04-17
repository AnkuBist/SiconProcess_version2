package com.hgil.siconprocess_view.activity.base_frame.remarkSummary;


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
public class RemarkRouteListFragment extends RouteBaseFragment {

    @BindView(R.id.rvRemarkRouteList)
    RecyclerView rvRemarkRouteList;
    @BindView(R.id.tvEmpty)
    TextView tvEmpty;

    private RemarkRouteListAdapter remarkRouteListAdapter;
    private RouteView routeView;
    private ArrayList<RouteListModel> arrRoute;

    public RemarkRouteListFragment() {
        // Required empty public constructor
    }

    public static RemarkRouteListFragment newInstance() {
        RemarkRouteListFragment fragment = new RemarkRouteListFragment();
        return fragment;
    }


    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_remark_route_list;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvRemarkRouteList.setLayoutManager(linearLayoutManager);

        hideSaveButton();
        setTitle("Remarks Summary");

        arrRoute = new ArrayList<>();
        routeView = new RouteView(getActivity());
        arrRoute.addAll(routeView.getRouteList());
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