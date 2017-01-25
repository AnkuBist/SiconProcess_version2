package com.hgil.siconprocess.activity.navFragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hgil.siconprocess.R;

public class VanInventoryFragment extends Fragment {

    public VanInventoryFragment() {
        // Required empty public constructor
    }

    public static VanInventoryFragment newInstance() {
        VanInventoryFragment fragment = new VanInventoryFragment();
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
        return inflater.inflate(R.layout.fragment_van_inventory, container, false);
    }
}
