package com.hgil.siconprocess.activity.navFragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hgil.siconprocess.R;

public class OutletInfoFragment extends Fragment {

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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_outlet_info, container, false);
    }

}
