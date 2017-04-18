package com.hgil.siconprocess_view.activity.fragments.baseLevel.planner;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.hgil.siconprocess_view.R;
import com.hgil.siconprocess_view.adapter.planner.PlanListAdapter;
import com.hgil.siconprocess_view.base.Base_Fragment;
import com.hgil.siconprocess_view.database.localDb.PlanModel;
import com.hgil.siconprocess_view.database.localDb.PlannerTable;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class PlanListFragment extends Base_Fragment {

    @BindView(R.id.rvUserPlans)
    RecyclerView rvUserPlans;
    @BindView(R.id.tvEmpty)
    TextView tvEmpty;

    private PlanListAdapter planListAdapter;
    private PlannerTable planTable;
    private ArrayList<PlanModel> arrPlans;

    public PlanListFragment() {
        // Required empty public constructor
    }

    public static PlanListFragment newInstance() {
        PlanListFragment fragment = new PlanListFragment();
        return fragment;
    }


    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_plan_list;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvUserPlans.setLayoutManager(linearLayoutManager);

        hideSaveButton();
        setTitle("User Plan");

        arrPlans = new ArrayList<>();
        planTable = new PlannerTable(getActivity());
        arrPlans.addAll(planTable.getUserPlan(getLoginId()));
        planListAdapter = new PlanListAdapter(getActivity(), arrPlans);
        rvUserPlans.setAdapter(planListAdapter);
    }

    @Override
    public void onResume() {
        super.onResume();
        if (arrPlans.size() == 0) {
            tvEmpty.setVisibility(View.VISIBLE);
            rvUserPlans.setVisibility(View.GONE);
        } else {
            tvEmpty.setVisibility(View.GONE);
            rvUserPlans.setVisibility(View.VISIBLE);
        }
    }

}
