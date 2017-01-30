package com.hgil.siconprocess.activity.homeTabs;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hgil.siconprocess.R;

import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomePendingFragment extends Fragment {

    public HomePendingFragment() {
        // Required empty public constructor
    }

    public static HomePendingFragment newInstance() {
        HomePendingFragment fragment = new HomePendingFragment();
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home_pending, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
    }

}