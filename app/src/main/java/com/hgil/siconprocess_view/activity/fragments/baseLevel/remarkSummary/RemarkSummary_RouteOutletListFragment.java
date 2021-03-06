package com.hgil.siconprocess_view.activity.fragments.baseLevel.remarkSummary;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.hgil.siconprocess_view.R;
import com.hgil.siconprocess_view.adapter.remarkSummary.OutletRemarkListAdapter;
import com.hgil.siconprocess_view.base.route_base.Route_Base_Fragment;
import com.hgil.siconprocess_view.database.localDb.OutletRemarkTable;
import com.hgil.siconprocess_view.retrofit.loginResponse.dbModel.OutletRemarkModel;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class RemarkSummary_RouteOutletListFragment extends Route_Base_Fragment {

    @BindView(R.id.tvRouteName)
    TextView tvRouteName;
    @BindView(R.id.rvRemarkRouteOutletList)
    RecyclerView rvRemarkRouteOutletList;
    @BindView(R.id.tvEmpty)
    TextView tvEmpty;

    private OutletRemarkListAdapter outletRemarkListAdapter;
    private OutletRemarkTable outletRemarkTable;
    private ArrayList<OutletRemarkModel> arrOutletRemark = new ArrayList<>();

    public RemarkSummary_RouteOutletListFragment() {
        // Required empty public constructor
    }

    public static RemarkSummary_RouteOutletListFragment newInstance() {
        RemarkSummary_RouteOutletListFragment fragment = new RemarkSummary_RouteOutletListFragment();
        return fragment;
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_remark_summary_route_outlet_list;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        hideSyncButton();
        setTitle("Remarks Summary");

        if (getRouteName() != null)
            tvRouteName.setText(getRouteName());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvRemarkRouteOutletList.setLayoutManager(linearLayoutManager);

        if (arrOutletRemark != null)
            arrOutletRemark.clear();
        else
            arrOutletRemark = new ArrayList<>();

        outletRemarkTable = new OutletRemarkTable(getActivity());
        arrOutletRemark.addAll(outletRemarkTable.getRouteRemarks(getLoginId(), getRouteId()));
        outletRemarkListAdapter = new OutletRemarkListAdapter(getActivity(), arrOutletRemark);
        rvRemarkRouteOutletList.setAdapter(outletRemarkListAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (arrOutletRemark.size() == 0) {
            tvEmpty.setVisibility(View.VISIBLE);
            rvRemarkRouteOutletList.setVisibility(View.GONE);
        } else {
            tvEmpty.setVisibility(View.GONE);
            rvRemarkRouteOutletList.setVisibility(View.VISIBLE);
        }
    }
}
