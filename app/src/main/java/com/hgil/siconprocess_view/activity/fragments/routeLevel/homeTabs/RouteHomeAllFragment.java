package com.hgil.siconprocess_view.activity.fragments.routeLevel.homeTabs;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.hgil.siconprocess_view.R;
import com.hgil.siconprocess_view.adapter.routeMap.RouteCustomerModel;
import com.hgil.siconprocess_view.adapter.routeMap.homeAll.RouteOutletAllAdapter;
import com.hgil.siconprocess_view.base.route_base.Route_Base_Fragment;
import com.hgil.siconprocess_view.database.OutletView;
import com.hgil.siconprocess_view.retrofit.RetrofitUtil;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * A simple {@link Fragment} subclass.
 */
public class RouteHomeAllFragment extends Route_Base_Fragment {

    @BindView(R.id.rvAllRouteMap)
    RecyclerView rvAllRouteMap;
    @BindView(R.id.tvEmpty)
    TextView tvEmpty;

    private RouteOutletAllAdapter mapRAdapter;
    private OutletView outletView;
    private ArrayList<RouteCustomerModel> arrRouteMap = new ArrayList<>();

    public RouteHomeAllFragment() {
        // Required empty public constructor
    }

    public static RouteHomeAllFragment newInstance() {
        RouteHomeAllFragment fragment = new RouteHomeAllFragment();
        return fragment;
    }

    @Override
    protected int getFragmentLayout() {
        return R.layout.fragment_home_all;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity());
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        rvAllRouteMap.setLayoutManager(linearLayoutManager);

        if (arrRouteMap != null)
            arrRouteMap.clear();
        else
            arrRouteMap = new ArrayList<>();

        outletView = new OutletView(getActivity());
        mapRAdapter = new RouteOutletAllAdapter(getActivity(), arrRouteMap);
        rvAllRouteMap.setAdapter(mapRAdapter);
        arrRouteMap.addAll(outletView.getRouteCustomers(routeId));
        //new LongOperation().execute();
        onResume();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (arrRouteMap.size() == 0) {
            tvEmpty.setVisibility(View.VISIBLE);
            rvAllRouteMap.setVisibility(View.GONE);
        } else {
            tvEmpty.setVisibility(View.GONE);
            rvAllRouteMap.setVisibility(View.VISIBLE);
        }
    }

    /*async task to fetch data from local*/
    private class LongOperation extends AsyncTask<String, Void, String> {
        @Override
        protected String doInBackground(String... params) {
            arrRouteMap.clear();
            arrRouteMap = (outletView.getRouteCustomers(routeId));
            return "Executed";
        }

        @Override
        protected void onPostExecute(String result) {
            mapRAdapter.updateData(arrRouteMap);
            onResume();
            RetrofitUtil.hideDialog();
        }

        @Override
        protected void onPreExecute() {
            RetrofitUtil.showDialog(getContext(), "");
        }
    }

}
