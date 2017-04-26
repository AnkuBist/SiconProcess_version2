package com.hgil.siconprocess_view.activity.fragments.baseLevel.depotList;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.hgil.siconprocess_view.R;
import com.hgil.siconprocess_view.adapter.depotList.DepotListAdapter;
import com.hgil.siconprocess_view.adapter.depotList.DepotModel;
import com.hgil.siconprocess_view.base.Base_Fragment;
import com.hgil.siconprocess_view.database.RouteView;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class DepotListFragment extends Base_Fragment {

    @BindView(R.id.rvDepotList)
    RecyclerView rvDepotList;
    @BindView(R.id.tvEmpty)
    TextView tvEmpty;

    private DepotListAdapter depotListAdapter;
    private RouteView routeView;
    private ArrayList<DepotModel> arrDepot = new ArrayList<>();

    public DepotListFragment() {
        // Required empty public constructor
    }

    public static DepotListFragment newInstance() {
        DepotListFragment fragment = new DepotListFragment();
        return fragment;
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_depot_list;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        showSyncButton();
        setTitle("Depot List");

        initializeListData();

        imgSync.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startSyncData(getLoginId());
                initializeListData();
            }
        });
    }

    public void initializeListData() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvDepotList.setLayoutManager(linearLayoutManager);
        
        if (arrDepot != null)
            arrDepot.clear();
        else
            arrDepot = new ArrayList<>();

        routeView = new RouteView(getActivity());
        arrDepot.addAll(routeView.getDepotList());
        depotListAdapter = new DepotListAdapter(getActivity(), arrDepot);
        rvDepotList.setAdapter(depotListAdapter);
        onResume();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (arrDepot.size() == 0) {
            tvEmpty.setVisibility(View.VISIBLE);
            rvDepotList.setVisibility(View.GONE);
        } else {
            tvEmpty.setVisibility(View.GONE);
            rvDepotList.setVisibility(View.VISIBLE);
        }
    }

}
