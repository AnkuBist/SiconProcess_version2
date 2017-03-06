package com.hgil.siconprocess.activity.navFragments;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.hgil.siconprocess.R;
import com.hgil.siconprocess.activity.fragments.dashboard.DaySummaryFragment;
import com.hgil.siconprocess.activity.fragments.dashboard.TargetsFragment;
import com.hgil.siconprocess.base.BaseFragment;

import butterknife.BindView;

public class DashboardFragment extends BaseFragment {

    @BindView(R.id.tvRouteName)
    TextView tvRouteName;
    @BindView(R.id.tvDaySummary)
    TextView tvDaySummary;
    @BindView(R.id.tvTargets)
    TextView tvTargets;

    public DashboardFragment() {
        // Required empty public constructor
    }

    public static DashboardFragment newInstance() {
        DashboardFragment fragment = new DashboardFragment();
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
        return R.layout.fragment_dashboard;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // set route name to the route
        String routeName = getRouteName();
        if (routeName != null && !routeName.isEmpty())
            tvRouteName.setText(routeName);

        setTitle("Dashboard");
        hideSaveButton();

        tvDaySummary.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DaySummaryFragment fragment = DaySummaryFragment.newInstance();
                launchNavFragment(fragment);
            }
        });

        tvTargets.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TargetsFragment fragment = TargetsFragment.newInstance();
                launchNavFragment(fragment);
            }
        });
    }
}
