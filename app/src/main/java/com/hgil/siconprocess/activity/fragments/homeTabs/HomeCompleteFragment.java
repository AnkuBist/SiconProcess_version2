package com.hgil.siconprocess.activity.fragments.homeTabs;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.View;

import com.hgil.siconprocess.R;
import com.hgil.siconprocess.base.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeCompleteFragment extends BaseFragment {

    public HomeCompleteFragment() {
        // Required empty public constructor
    }

    public static HomeCompleteFragment newInstance() {
        HomeCompleteFragment fragment = new HomeCompleteFragment();
        return fragment;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_home_complete;
    }
}
