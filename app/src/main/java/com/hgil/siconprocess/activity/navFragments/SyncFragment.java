package com.hgil.siconprocess.activity.navFragments;

import android.os.Bundle;
import android.view.View;

import com.hgil.siconprocess.R;
import com.hgil.siconprocess.base.BaseFragment;

public class SyncFragment extends BaseFragment {

    public SyncFragment() {
        // Required empty public constructor
    }

    public static SyncFragment newInstance() {
        SyncFragment fragment = new SyncFragment();
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
        return R.layout.fragment_sync;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        hideSaveButton();
    }

}
