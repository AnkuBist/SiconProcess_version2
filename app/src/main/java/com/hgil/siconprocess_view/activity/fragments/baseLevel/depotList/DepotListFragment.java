package com.hgil.siconprocess_view.activity.fragments.baseLevel.depotList;

import android.os.AsyncTask;
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
import com.hgil.siconprocess_view.database.ZoneView;
import com.hgil.siconprocess_view.retrofit.RetrofitUtil;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class DepotListFragment extends Base_Fragment {

    private static String ZONE_NAME = "zone_name";
    @BindView(R.id.tvZoneName)
    TextView tvZoneName;
    @BindView(R.id.rvDepotList)
    RecyclerView rvDepotList;
    @BindView(R.id.tvEmpty)
    TextView tvEmpty;

    private DepotListAdapter depotListAdapter;
    private ZoneView zoneView;
    private ArrayList<DepotModel> arrDepot = new ArrayList<>();

    public DepotListFragment() {
        // Required empty public constructor
    }

    public static DepotListFragment newInstance(String zoneName) {
        DepotListFragment fragment = new DepotListFragment();
        Bundle bundle = new Bundle();
        bundle.putString(ZONE_NAME, zoneName);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_depot_list;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            zoneName = getArguments().getString(ZONE_NAME);
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (zoneName != null)
            tvZoneName.setText(zoneName);

        showSyncButton();
        setTitle(getString(R.string.str_nav_depot_list));

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

        zoneView = new ZoneView(getActivity());

        depotListAdapter = new DepotListAdapter(getActivity(), arrDepot);
        rvDepotList.setAdapter(depotListAdapter);
        arrDepot.addAll(zoneView.getDepotList(zoneName));
        // Since reading depots will take more time run in on another thread
        //new LongOperation().execute();
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

    /*async task to fetch data from local*/
    private class LongOperation extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            arrDepot.clear();
            arrDepot = (zoneView.getDepotList(zoneName));
            return "Executed";
        }

        @Override
        protected void onPostExecute(String result) {
            depotListAdapter.updateData(arrDepot);
            onResume();
            RetrofitUtil.hideDialog();
        }

        @Override
        protected void onPreExecute() {
            RetrofitUtil.showDialog(getContext(), "");
        }
    }

}
