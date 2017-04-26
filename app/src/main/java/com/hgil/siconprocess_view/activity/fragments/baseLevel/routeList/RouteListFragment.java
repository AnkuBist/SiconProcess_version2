package com.hgil.siconprocess_view.activity.fragments.baseLevel.routeList;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.hgil.siconprocess_view.R;
import com.hgil.siconprocess_view.adapter.routeList.RouteListAdapter;
import com.hgil.siconprocess_view.adapter.routeList.RouteListModel;
import com.hgil.siconprocess_view.base.Base_Fragment;
import com.hgil.siconprocess_view.database.RouteView;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class RouteListFragment extends Base_Fragment {

    private static String DEPOT_ID = "depot_id";
    private static String DEPOT_NAME = "depot_name";
    @BindView(R.id.tvDepotName)
    TextView tvDepotName;
    @BindView(R.id.rvRouteList)
    RecyclerView rvRouteList;
    @BindView(R.id.tvEmpty)
    TextView tvEmpty;
    private String depot_id, depotName;
    private RouteListAdapter routeListAdapter;
    private RouteView routeView;
    private ArrayList<RouteListModel> arrRoute = new ArrayList<>();

    public RouteListFragment() {
        // Required empty public constructor
    }

    public static RouteListFragment newInstance(String depot_id, String depotName) {
        RouteListFragment fragment = new RouteListFragment();
        Bundle bundle = new Bundle();
        bundle.putString(DEPOT_ID, depot_id);
        bundle.putString(DEPOT_NAME, depotName);
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            depot_id = getArguments().getString(DEPOT_ID);
            depotName = getArguments().getString(DEPOT_NAME);
        }
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_route_list;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        hideSyncButton();
        setTitle("Route List");

        if (depotName != null)
            tvDepotName.setText(depotName);

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
        rvRouteList.setLayoutManager(linearLayoutManager);

        if(arrRoute!=null)
            arrRoute.clear();
        else
            arrRoute = new ArrayList<>();

        routeView = new RouteView(getActivity());
        arrRoute.addAll(routeView.getDepotRouteList(depot_id));
        routeListAdapter = new RouteListAdapter(getActivity(), arrRoute);
        rvRouteList.setAdapter(routeListAdapter);
        onResume();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (arrRoute.size() == 0) {
            tvEmpty.setVisibility(View.VISIBLE);
            rvRouteList.setVisibility(View.GONE);
        } else {
            tvEmpty.setVisibility(View.GONE);
            rvRouteList.setVisibility(View.VISIBLE);
        }
    }
}
