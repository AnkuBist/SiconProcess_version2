package com.hgil.siconprocess_view.activity.fragments.baseLevel.zoneList;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.hgil.siconprocess_view.R;
import com.hgil.siconprocess_view.adapter.zoneList.ZoneListAdapter;
import com.hgil.siconprocess_view.base.Base_Fragment;
import com.hgil.siconprocess_view.database.ZoneView;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class ZoneListFragment extends Base_Fragment {

    @BindView(R.id.rvZoneList)
    RecyclerView rvZoneList;
    @BindView(R.id.tvEmpty)
    TextView tvEmpty;

    private ZoneListAdapter zoneListAdapter;
    private ZoneView zoneView;
    private ArrayList<String> arrZones = new ArrayList<>();

    public ZoneListFragment() {
        // Required empty public constructor
    }

    public static ZoneListFragment newInstance() {
        ZoneListFragment fragment = new ZoneListFragment();
        return fragment;
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_zone_list;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        showSyncButton();
        setTitle(getString(R.string.str_nav_zone_list));

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
        rvZoneList.setLayoutManager(linearLayoutManager);

        if (arrZones != null)
            arrZones.clear();
        else
            arrZones = new ArrayList<>();

        zoneView = new ZoneView(getActivity());
        arrZones.addAll(zoneView.getZoneList());
        zoneListAdapter = new ZoneListAdapter(getActivity(), arrZones);
        rvZoneList.setAdapter(zoneListAdapter);
        onResume();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (arrZones.size() == 0) {
            tvEmpty.setVisibility(View.VISIBLE);
            rvZoneList.setVisibility(View.GONE);
        } else {
            tvEmpty.setVisibility(View.GONE);
            rvZoneList.setVisibility(View.VISIBLE);
        }
    }
}
