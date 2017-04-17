package com.hgil.siconprocess_view.activity.base_frame.remarkSummary;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.hgil.siconprocess_view.R;
import com.hgil.siconprocess_view.base.BaseFragment;
import com.hgil.siconprocess_view.database.localDb.OutletRemarkModel;
import com.hgil.siconprocess_view.database.localDb.OutletRemarkTable;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class RemarkRouteOutletListFragment extends BaseFragment {

    @BindView(R.id.rvRemarkRouteOutletList)
    RecyclerView rvRemarkRouteOutletList;
    @BindView(R.id.tvEmpty)
    TextView tvEmpty;

    private OutletRemarkListAdapter outletRemarkListAdapter;
    private OutletRemarkTable outletRemarkTable;
    private ArrayList<OutletRemarkModel> arrOutletRemark;

    public RemarkRouteOutletListFragment() {
        // Required empty public constructor
    }

    public static RemarkRouteOutletListFragment newInstance() {
        RemarkRouteOutletListFragment fragment = new RemarkRouteOutletListFragment();
        return fragment;
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_remark_route_outlet_list;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        hideSaveButton();
        setTitle("Remarks Summary");

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvRemarkRouteOutletList.setLayoutManager(linearLayoutManager);

        arrOutletRemark = new ArrayList<>();
        outletRemarkTable = new OutletRemarkTable(getActivity());
        arrOutletRemark.addAll(outletRemarkTable.getRouteRemarks(getRouteId()));
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
