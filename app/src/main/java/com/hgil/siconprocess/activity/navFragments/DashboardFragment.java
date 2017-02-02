package com.hgil.siconprocess.activity.navFragments;

import android.os.Bundle;
import android.view.View;

import com.hgil.siconprocess.R;
import com.hgil.siconprocess.base.BaseFragment;

public class DashboardFragment extends BaseFragment {

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
            /*mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);*/
        }
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_dashboard;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        hideSaveButton();
    }
}
