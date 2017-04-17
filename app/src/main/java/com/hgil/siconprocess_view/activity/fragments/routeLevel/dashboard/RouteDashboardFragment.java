package com.hgil.siconprocess_view.activity.fragments.routeLevel.dashboard;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.hgil.siconprocess_view.R;
import com.hgil.siconprocess_view.base.route_base.Route_Base_Fragment;

import butterknife.BindView;

public class RouteDashboardFragment extends Route_Base_Fragment {

    @BindView(R.id.tvRouteName)
    TextView tvRouteName;
    @BindView(R.id.tvDaySummary)
    TextView tvDaySummary;
    @BindView(R.id.tvTargets)
    TextView tvTargets;

    public RouteDashboardFragment() {
        // Required empty public constructor
    }

    public static RouteDashboardFragment newInstance() {
        RouteDashboardFragment fragment = new RouteDashboardFragment();
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
        return R.layout.fragment_route_dashboard;
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
