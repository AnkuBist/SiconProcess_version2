package com.hgil.siconprocess.activity;


import android.os.Bundle;
import android.support.annotation.Nullable;
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
import com.hgil.siconprocess.database.tables.PaymentTable;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class VanStockFragment extends BaseFragment {

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

    public VanStockFragment() {
        // Required empty public constructor
    }

    public static VanStockFragment newInstance() {
        VanStockFragment fragment = new VanStockFragment();
        return fragment;
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_van_stock;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setTitle("View Van Stock");
        hideSaveButton();

        // get crate info
        CrateCollectionView crateCollectionView = new CrateCollectionView(getContext());
        PaymentTable paymentTable = new PaymentTable(getContext());

        int crateLoaded = crateCollectionView.vanTotalCrate();
        int crateOs = 0;
        int crateIssued = paymentTable.routeIssuedCrate();
        int crateReturned = paymentTable.routeReceivedCrate();
        int crateLeftover = crateLoaded - crateIssued + crateReturned;

        // display these in UI
        tvCrateLoaded.setText(String.valueOf(crateLoaded));
        tvCrateOs.setText(String.valueOf(crateOs));
        tvCrateIssued.setText(String.valueOf(crateIssued));
        tvCrateReturned.setText(String.valueOf(crateReturned));
        tvCrateLeftover.setText(String.valueOf(crateLeftover));

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvVanStock.setLayoutManager(linearLayoutManager);

        productView = new ProductView(getContext());
        arrVanStock.addAll(productView.getVanStock());
        vanStockAdapter = new VanStockAdapter(getContext(), arrVanStock);
        rvVanStock.setAdapter(vanStockAdapter);
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
