package com.hgil.siconprocess.activity.navFragments.fragments;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.hgil.siconprocess.R;
import com.hgil.siconprocess.adapter.vanStock.VanStockAdapter;
import com.hgil.siconprocess.adapter.vanStock.VanStockModel;
import com.hgil.siconprocess.base.BaseFragment;
import com.hgil.siconprocess.database.masterTables.CrateCollectionView;
import com.hgil.siconprocess.database.masterTables.ProductView;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class ViewVanStockFragment extends BaseFragment {

    @BindView(R.id.tvCrateLoaded)
    TextView tvCrateLoaded;
    @BindView(R.id.tvCrateOs)
    TextView tvCrateOs;
    @BindView(R.id.tvCrateIssued)
    TextView tvCrateIssued;
    @BindView(R.id.tvCrateReturned)
    TextView tvCrateReturned;
    @BindView(R.id.tvCrateLeftover)
    TextView tvCrateLeftover;
    @BindView(R.id.rvVanStock)
    RecyclerView rvVanStock;
    @BindView(R.id.tvEmpty)
    TextView tvEmpty;

    private VanStockAdapter vanStockAdapter;
    private ProductView productView;
    private ArrayList<VanStockModel> arrVanStock = new ArrayList<>();


    public ViewVanStockFragment() {
        // Required empty public constructor
    }

    public static ViewVanStockFragment newInstance() {
        ViewVanStockFragment fragment = new ViewVanStockFragment();
        return fragment;
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_view_van_stock;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // hide the menu hamburger icon


        // get crate info
        CrateCollectionView crateCollectionView = new CrateCollectionView(getContext());

        int crateLoaded = crateCollectionView.vanTotalCrate();
        int crateOs = 0;
        int crateIssued = 0;
        int crateReturned = 0;
        int crateLefover = 0;

        // display these in UI
        tvCrateLoaded.setText(String.valueOf(crateLoaded));
        tvCrateOs.setText(String.valueOf(crateOs));
        tvCrateIssued.setText(String.valueOf(crateIssued));
        tvCrateReturned.setText(String.valueOf(crateReturned));
        tvCrateLeftover.setText(String.valueOf(crateLefover));

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvVanStock.setLayoutManager(linearLayoutManager);

        productView = new ProductView(getActivity());
        arrVanStock.addAll(productView.getVanStock());
        vanStockAdapter = new VanStockAdapter(getActivity(), arrVanStock);
        rvVanStock.setAdapter(vanStockAdapter);

        setTitle("View Van Stock");
        hideSaveButton();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (arrVanStock.size() == 0) {
            tvEmpty.setVisibility(View.VISIBLE);
            rvVanStock.setVisibility(View.GONE);
        } else {
            tvEmpty.setVisibility(View.GONE);
            rvVanStock.setVisibility(View.VISIBLE);
        }
    }
}
