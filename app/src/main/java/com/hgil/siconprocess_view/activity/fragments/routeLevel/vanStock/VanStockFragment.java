package com.hgil.siconprocess_view.activity.fragments.routeLevel.vanStock;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.hgil.siconprocess_view.R;
import com.hgil.siconprocess_view.adapter.vanStock.VanStkModel;
import com.hgil.siconprocess_view.adapter.vanStock.VanStockAdapter;
import com.hgil.siconprocess_view.base.route_base.Route_Base_Fragment;
import com.hgil.siconprocess_view.database.VanStockView;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class VanStockFragment extends Route_Base_Fragment {

    /* @BindView(R.id.tvCrateLoaded)
     TextView tvCrateLoaded;
     @BindView(R.id.tvCrateOs)
     TextView tvCrateOs;
     @BindView(R.id.tvCrateIssued)
     TextView tvCrateIssued;
     @BindView(R.id.tvCrateReturned)
     TextView tvCrateReturned;
     @BindView(R.id.tvCrateLeftover)
     TextView tvCrateLeftover;*/
    @BindView(R.id.rvVanStock)
    RecyclerView rvVanStock;
    @BindView(R.id.tvEmpty)
    TextView tvEmpty;

    private VanStockView vanStockView;
    private VanStockAdapter vanStockAdapter;
    private ArrayList<VanStkModel> arrVanStock = new ArrayList<>();

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
        setTitle("Van Stock");
        hideSyncButton();

        // get crate info
        //CrateCollectionView crateCollectionView = new CrateCollectionView(getContext());
        //PaymentTable paymentTable = new PaymentTable(getContext());

        //int crateLoaded = crateCollectionView.vanTotalCrate();
        //int crateOs = 0;
        //int crateIssued = paymentTable.routeIssuedCrate();
        //int crateReturned = paymentTable.routeReceivedCrate();
        //int crateLeftover = crateLoaded - crateIssued + crateReturned;

        // display these in UI
        //tvCrateLoaded.setText(String.valueOf(crateLoaded));
        //tvCrateOs.setText(String.valueOf(crateOs));
        // tvCrateIssued.setText(String.valueOf(crateIssued));
        //tvCrateReturned.setText(String.valueOf(crateReturned));
        //tvCrateLeftover.setText(String.valueOf(crateLeftover));

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvVanStock.setLayoutManager(linearLayoutManager);

        vanStockView = new VanStockView(getContext());
        arrVanStock.addAll(vanStockView.getVanStockByRoute(routeId));
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
