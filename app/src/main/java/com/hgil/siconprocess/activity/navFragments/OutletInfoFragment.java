package com.hgil.siconprocess.activity.navFragments;

import android.os.Bundle;
import android.view.View;

import com.hgil.siconprocess.R;
import com.hgil.siconprocess.base.BaseFragment;

public class OutletInfoFragment extends BaseFragment {

    public OutletInfoFragment() {
        // Required empty public constructor
    }

    public static OutletInfoFragment newInstance() {
        OutletInfoFragment fragment = new OutletInfoFragment();
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
        return R.layout.fragment_outlet_info;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        hideSaveButton();
    }

}
