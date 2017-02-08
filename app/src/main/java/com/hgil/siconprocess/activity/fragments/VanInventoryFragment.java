package com.hgil.siconprocess.activity.fragments;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.view.View;
import android.widget.TextView;

import com.hgil.siconprocess.R;
import com.hgil.siconprocess.activity.NavBaseActivity;
import com.hgil.siconprocess.base.BaseFragment;
import com.hgil.siconprocess.base.SiconApp;

import butterknife.BindView;

public class VanInventoryFragment extends BaseFragment {
    @BindView(R.id.tvRouteName)
    TextView tvRouteName;
    @BindView(R.id.tvViewVanStock)
    TextView tvViewVanStock;
    @BindView(R.id.tvVanClosing)
    TextView tvVanClosing;

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
    protected int getFragmentLayout() {
        return R.layout.fragment_van_inventory;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // set route name to the route
        String routeName = SiconApp.getInstance().getRouteName();
        if (routeName != null && !routeName.isEmpty())
            tvRouteName.setText(routeName);

        setTitle("Van Inventory");
        hideSaveButton();

        tvViewVanStock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewVanStockFragment fragment = ViewVanStockFragment.newInstance();
                String fragClassName = fragment.getClass().getName();
                FragmentManager fragmentManager = ((NavBaseActivity) getActivity()).getSupportFragmentManager();
                fragmentManager.beginTransaction().replace(R.id.flContent, fragment).addToBackStack(fragClassName).commit();
            }
        });

        tvVanClosing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

    }
}
